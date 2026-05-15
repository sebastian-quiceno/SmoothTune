import { useState, useEffect, useMemo } from "react";
import { genreService } from "../services/genreService";

import { type Genre } from "../types/genre";

export const useGenreFilter = (genreName: string) => {
  const [genres, setGenres] = useState<Genre[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    genreService
      .getGenres()
      .then(setGenres)
      .finally(() => setLoading(false));
  }, []);

  const genresFilters = useMemo(() => {
    const search = genreName?.toLowerCase().trim();

    if (!search) return genres;

    return genres.filter((genre) => genre.name.toLowerCase().includes(search));
  }, [genreName, genres]);

  return { genresFilters, loading };
};
