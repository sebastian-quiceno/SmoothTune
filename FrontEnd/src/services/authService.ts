const API_URL = "http://localhost:8080/auth";

export interface SignInRequest {
  email: string;
  password: string;
}

export interface SignUpRequest {
  email: string;
  username: string;
  password: string;
}

//Crea la solicitud para el backEnd
export async function signIn(data: SignInRequest) {
    
  const response = await fetch(`${API_URL}/signin`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });


  if (!response.ok) {
    throw new Error("Credenciales inválidas");
  }

  return response.json(); // { token }
}

export async function signUp(data: SignUpRequest) {

  console.log(data);

  const response = await fetch(`${API_URL}/signup`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    throw new Error("Error al registrarse");
  }

  return response.json(); // { token }
}
