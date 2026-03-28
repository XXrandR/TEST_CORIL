import { flexRender } from "@tanstack/react-table";

export function PrincipalTable({ isLoading, isError, data, table }) {
    return (
        <div>
            {
                isLoading && (
                    <div className="text-gray-500">Cargando propuestas...</div>
                )
            }

            {
                isError && (
                    <div className="text-red-500">{error.message}</div>
                )
            }

            {
                !isLoading && data.length > 0 && table && (
                    <div className="bg-white shadow-md rounded-lg overflow-hidden">
                        <table className="min-w-full text-sm text-left">
                            <thead className="bg-gray-100 text-gray-600 uppercase text-xs">
                                {table.getHeaderGroups().map(headerGroup => (
                                    <tr key={headerGroup.id}>
                                        {headerGroup.headers.map(header => (
                                            <th
                                                key={header.id}
                                                onClick={header.column.getToggleSortingHandler()}
                                                className="px-4 py-3 cursor-pointer select-none hover:bg-gray-200 transition"
                                            >
                                                <div className="flex items-center gap-1">
                                                    {flexRender(
                                                        header.column.columnDef.header,
                                                        header.getContext()
                                                    )}
                                                    {{
                                                        asc: "  ▲",
                                                        desc: "  ▼"
                                                    }[header.column.getIsSorted()] ?? null}
                                                </div>
                                            </th>
                                        ))}
                                    </tr>
                                ))}
                            </thead>

                            <tbody>
                                {table.getRowModel().rows.map((row, i) => {
                                    const minutosLiquidacion = row.getValue("minutosLiquidacion");

                                    let rowClass = i % 2 === 0 ? "bg-white" : "bg-gray-50"

                                    if (minutosLiquidacion > 240) {
                                        rowClass = "bg-red-100"; // red 4h
                                    } else if (minutosLiquidacion > 120) {
                                        rowClass = "bg-amber-100"; // amber >2h
                                    }

                                    return (
                                        <tr
                                            key={row.id}
                                            className={`${rowClass} border-t hover:bg-blue-50 transition`}
                                        >
                                            {row.getVisibleCells().map(cell => (
                                                <td key={cell.id} className="px-4 py-2 text-gray-700">
                                                    {flexRender(cell.column.columnDef.cell, cell.getContext())}
                                                </td>
                                            ))}
                                        </tr>
                                    );
                                })}
                            </tbody>
                        </table>
                    </div>
                )
            }
        </div>
    );
}
