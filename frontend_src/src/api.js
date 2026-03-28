const API_URL = "http://127.0.0.1:8080/api";

// EN UN ENTORNO DE PRODUCCION CADA DTO DEBE TENER ESTADOS, LOADING,FETCHING,SUCCESS,ERROR

export const login = async (username, password) => {
  const response = await fetch(`${API_URL}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ username, password })
  });

  if (!response.ok) {
    throw new Error("Credenciales incorrectas");
  }

  return response.json();
};


export const getBandejas = async () => {
  const token = localStorage.getItem("token");

  const response = await fetch(`${API_URL}/report/bandejas`, {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  });

  if (!response.ok) {
    throw new Error("Error obteniendo bandejas");
  }

  return response.json();
};


export const getPropuestas = async (id) => {
  const token = localStorage.getItem("token");

  const response = await fetch(`${API_URL}/report/propuestas/${id}`, {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  });

  if (!response.ok) {
    return [];
  }

  return response.json();
};
