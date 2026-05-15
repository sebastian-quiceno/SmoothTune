import { useState } from "react";
import { genreService } from "../services/genreService";
import { type Genre, type CreateGenreRequest } from "../types/genre";

export function useGenres() {
  const [genres, setGenres] = useState<Genre[]>([]);
  const [loading, setLoading] = useState(false);

  const [error, setError] = useState<string | null>(null);

  const getGenres = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await genreService.getGenres();
      setGenres(data);
    } catch (err: unknown) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError("Error desconocido");
      }
    } finally {
      setLoading(false);
    }
  };

  const createGenre = async (request: CreateGenreRequest) => {
    setLoading(true);
    setError(null);

    console.log("el body es: ")
    console.log(request)

    try {
      await genreService.createGenre(request);
    } catch (err: unknown) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError("Error al crear el género");
      }
    } finally {
      setLoading(false);
    }
  };

  return { genres, loading, error, getGenres, createGenre };
}

