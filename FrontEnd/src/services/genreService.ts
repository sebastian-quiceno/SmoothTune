import axios from "axios";
import apiClient from "../apis/apiClient";
import {type Genre, type CreateGenreRequest} from '../types/genre'


function getApiErrorMessage(error: unknown, fallbackMessage: string) {
  if (axios.isAxiosError<{ message?: string }>(error)) {
    return error.response?.data?.message || fallbackMessage;
  }

  return fallbackMessage;
}

export const genreService = {
  getGenres: async (): Promise<Genre[]> => {
    try {
      const response = await apiClient.get<Genre[]>("/genre/getGenres");
      return response.data;
    } catch (error: unknown) {
      throw new Error(getApiErrorMessage(error, "Error al obtener géneros"));
    }
  },

  createGenre: async (request: CreateGenreRequest) => {
    try {
      const response = await apiClient.post<Genre>("/genre/create", request);
      return response.data;

    } catch (error: unknown) {
      throw new Error(getApiErrorMessage(error, "Error al crear el genero"))
    }
  },
};
