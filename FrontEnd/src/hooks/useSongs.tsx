import { useState } from "react";
import { type UploadSong } from "../types/song";
import { songService } from "../services/songService";

import { type Song } from "../types/song";
import { type Page } from "../types/page";

export const useSongs = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [songs, setSongs] = useState<Song[]>([]);
  const [page, setPage] = useState<Page<Song> | null>(null);
  const [maxPage, setMaxPage] = useState<number>();

  const uploadSong = async (request: UploadSong) => {
    setLoading(true);
    setError(null);
    try {
      await songService.uploadSong(request);
    } catch (err: unknown) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError("Error al crear el canción");
      }
    } finally {
      setLoading(false);
    }
  };
  const getSongsUploadedByUserId = async (id: number) => {
    setLoading(true);
    setError(null);
    try {
      const response = await songService.getSongsUploadedByUserId(id);
      return setSongs(response);
    } catch (err: unknown) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError(
          "Error al obtener las canciones subidas por el usuario con id: " + id,
        );
      }
    } finally {
      setLoading(false);
    }
  };
  const getSongs = async (pageNumber?: number) => {
    setLoading(true);
    setError(null);
    try {
      const response = await songService.getSongs(pageNumber);
      setMaxPage(response.totalPages)
      return setPage(response);
    } catch (err: unknown) {
      if (err instanceof Error) {
        setError(err.message);
      } else {
        setError(
          "Error al obtener las canciones de la pagina numero: " + pageNumber,
        );
      }
    } finally {
      setLoading(false);
    }
  };
  return {
    loading,
    error,
    songs,
    page,
    maxPage,
    uploadSong,
    getSongs,
    getSongsUploadedByUserId,
  };
};
