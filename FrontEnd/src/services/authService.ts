import axios from "axios";
import apiClient from "../apis/apiClient";

export interface SignInRequest {
  email: string;
  password: string;
}

export interface SignUpRequest {
  email: string;
  username: string;
  password: string;
}

function getApiErrorMessage(error: unknown, fallbackMessage: string) {
  if (axios.isAxiosError<{ message?: string }>(error)) {
    return error.response?.data?.message || fallbackMessage;
  }

  return fallbackMessage;
}

export async function signIn(request: SignInRequest) {
  try {
    const response = await apiClient.post("/auth/signin", request);

    const data = response.data;

    localStorage.setItem("token", data.token);

    return data;

  } catch (error: unknown) {
    throw new Error(getApiErrorMessage(error, "Credenciales inválidas"));
  }
}

export async function signUp(request: SignUpRequest) {

  console.log(request);

  try {
    const response = await apiClient.post("/auth/signup", request);

    const data = response.data;

    localStorage.setItem("token", data.token);

    return data;

  } catch (error: unknown) {
    throw new Error(getApiErrorMessage(error, "Error al registrarse"));
  }
}

export function logoutUser() {
  localStorage.removeItem("token");
}

export function getToken() {
  return localStorage.getItem("token");
}

export function isAuthenticated() {
  return !!getToken();
}