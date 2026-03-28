// LA RUTA NO ESTA PROTEGIDA POR EL ROUTER DOOM

import {
    useReactTable,
    getCoreRowModel,
    getSortedRowModel
} from "@tanstack/react-table";
import { useState, useMemo, useEffect } from "react";
import { useQuery } from "@tanstack/react-query";
import { getBandejas, getPropuestas } from "../api";
import { PrincipalTable } from "../components/PrincipalTable";
import * as XLSX from "xlsx";
import { saveAs } from "file-saver";
import { useNavigate } from "react-router-dom";

function formatDate(date) {
    if (!date) return "-";
    return new Date(date).toLocaleString();
}

function Dashboard() {
    const username = localStorage.getItem("username");
    const [bandejaSelected, setBandejaSelected] = useState(20528);
    const [bandejas, setBandejas] = useState([]);
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");

    const { data = [], isLoading, isError, error } = useQuery({
        queryKey: ["propuestas", bandejaSelected],
        queryFn: () => getPropuestas(bandejaSelected),
        staleTime: 1000 * 60 * 5
    });

    const filteredData = useMemo(() => {
        if (!startDate && !endDate) return data;

        return data.filter(row => {
            if (!row.fecha_cancelacion) return false;

            const cancelDate = new Date(row.fecha_cancelacion);
            const start = startDate ? new Date(startDate) : null;
            const end = endDate ? new Date(endDate) : null;

            if (start && cancelDate < start) return false;
            if (end && cancelDate > end) return false;

            return true;
        });
    }, [data, startDate, endDate]);

    useEffect(() => {
        const fetchBandejas = async () => {
            const data = await getBandejas()
            setBandejas(data);
        }

        fetchBandejas();
    }, []);

    const [sorting, setSorting] = useState([]);

    const columns = useMemo(() => [
        { accessorKey: "propuestaId", header: "ID" },

        {
            accessorKey: "inicio",
            header: "Inicio",
            cell: info => formatDate(info.getValue())
        },
        {
            accessorKey: "auditoria_fecha",
            header: "Auditoría",
            cell: info => formatDate(info.getValue())
        },
        {
            accessorKey: "riesgos_fecha",
            header: "Riesgos",
            cell: info => formatDate(info.getValue())
        },
        {
            accessorKey: "fecha_desembolso",
            header: "Desembolso",
            cell: info => formatDate(info.getValue())
        },
        {
            accessorKey: "fecha_cancelacion",
            header: "Cancelación",
            cell: info => formatDate(info.getValue())
        },

        {
            accessorKey: "minutosCancelacion",
            header: "Min Cancelación"
        },
        {
            accessorKey: "minutosLiquidacion",
            header: "Min Liquidación"
        },

        {
            accessorKey: "cumpleSLA",
            header: "SLA",
            cell: info => {
                const value = info.getValue();
                return (
                    <span
                        className={`px-2 py-1 rounded text-xs font-semibold ${value
                            ? "bg-green-100 text-green-700"
                            : "bg-red-100 text-red-700"
                            }`}
                    >
                        {value ? "Cumple" : "No cumple"}
                    </span>
                );
            }
        }
    ], []);

    const navigate = useNavigate();

    const exportToExcel = () => {
        const exportData = data.map(row => ({
            ID: row.propuestaId,
            Inicio: formatDate(row.inicio),
            Auditoria: formatDate(row.auditoria_fecha),
            Riesgos: formatDate(row.riesgos_fecha),
            Desembolso: formatDate(row.fecha_desembolso),
            Cancelacion: formatDate(row.fecha_cancelacion),
            "Min Cancelacion": row.minutosCancelacion,
            "Min Liquidacion": row.minutosLiquidacion,
            SLA: row.cumpleSLA ? "Cumple" : "No cumple"
        }));

        const worksheet = XLSX.utils.json_to_sheet(exportData);
        const workbook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(workbook, worksheet, "Propuestas");

        const excelBuffer = XLSX.write(workbook, { bookType: "xlsx", type: "array" });
        const blob = new Blob([excelBuffer], { type: "application/octet-stream" });
        saveAs(blob, `Propuestas_${bandejaSelected}.xlsx`);
    }

    const closeSession = () => {
        localStorage.setItem("token", "");
        localStorage.setItem("username", "");

        navigate("/");
    }

    const table = useReactTable({
        data: filteredData,
        columns,
        state: { sorting },
        onSortingChange: setSorting,
        getCoreRowModel: getCoreRowModel(),
        getSortedRowModel: getSortedRowModel()
    });

    return (
        <div className="p-6 bg-gray-50 min-h-screen">
            <div className="mb-6 flex justify-between items-center">
                <div>
                    <h1 className="text-2xl font-bold text-gray-800">
                        Bienvenido {username}
                    </h1>
                    <p className="text-sm text-gray-500">
                        Panel de propuestas
                    </p>
                </div>

                <button
                    onClick={closeSession}
                    className="px-4 py-2 bg-red-600 text-white rounded-xl hover:bg-red-700 transition"
                >
                    Cerrar session
                </button>
            </div>

            <div className="mb-4">
                <label className="block text-sm font-medium text-gray-700 mb-1">
                    Seleccionar bandeja
                </label>

                <div className="flex flex-wrap gap-4 items-end">
                    <div>
                        <select
                            value={bandejaSelected}
                            onChange={(e) => setBandejaSelected(Number(e.target.value))}
                            className="border border-gray-300 rounded-lg px-3 py-2 bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 text-sm"
                        >
                            {bandejas.map((b) => (
                                <option key={b} value={b}>
                                    Bandeja nro {b}
                                </option>
                            ))}
                        </select>
                    </div>

                    <button
                        onClick={exportToExcel}
                        className="mt-5 px-3 py-1.5 bg-blue-600 text-white text-sm rounded-lg hover:bg-blue-700 transition"
                    >
                        Exportar Excel
                    </button>

                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Fecha inicio
                        </label>
                        <input
                            type="date"
                            value={startDate}
                            onChange={(e) => setStartDate(e.target.value)}
                            className="border border-gray-300 rounded-lg px-3 py-1.5 bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 text-sm"
                        />
                    </div>

                    <div>
                        <label className="block text-sm font-medium text-gray-700 mb-1">
                            Fecha fin
                        </label>
                        <input
                            type="date"
                            value={endDate}
                            onChange={(e) => setEndDate(e.target.value)}
                            className="border border-gray-300 rounded-lg px-3 py-1.5 bg-white shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500 text-sm"
                        />
                    </div>

                    <button
                        onClick={() => { setStartDate(""); setEndDate(""); }}
                        className="mt-5 px-3 py-1.5 bg-gray-300 text-gray-700 text-sm rounded-lg hover:bg-gray-400 transition"
                    >
                        Limpiar
                    </button>
                </div>
            </div>
            <div className="mb-4 text-gray-700">
                Codigo de colores: rojo si es mayor a 4h, amber si es mayor a 2h
            </div>
            <PrincipalTable isLoading={isLoading} isError={isError} data={data} table={table} />
        </div>
    );
}

export default Dashboard;