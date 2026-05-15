import { useState } from "react";
import { type UploadSong } from "../types/song";
import { songService } from "../services/songService";

export const useSongs = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

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
  return { loading, error, uploadSong };
};
