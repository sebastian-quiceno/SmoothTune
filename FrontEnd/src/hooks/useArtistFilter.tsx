import { useState, useEffect, useMemo } from "react";
import { artistService } from "../services/artistService";

import { type Artist } from "../types/artist";

export const useArtistFilter = (artistName: string) => {
  const [artists, setArtists] = useState<Artist[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    artistService
      .getArtists()
      .then(setArtists)
      .finally(() => setLoading(false));
  }, []);

  const artistsFilters = useMemo(() => {
    const search = artistName?.toLowerCase().trim();

    if (!search) return artists;

    return artists.filter((artist) => artist.name.toLowerCase().includes(search));
  }, [artistName, artists]);

  return { artistsFilters, loading };
};
