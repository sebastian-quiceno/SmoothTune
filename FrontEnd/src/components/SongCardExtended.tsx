import { useState, useEffect, useMemo } from "react";

import { InfoCard } from "../components/InfoCard";

import { songService } from "../services/songService";

import portraitNotFound from "/portraitNotFound.jpg";

type SongCardProps = {
  id: number;
  title: string;
  artist: string;
  uploader: string;
  genre: string;
  duration: number;
  size: number;
  dateUpload: Date;
  onClick: () => void;
};

export const SongCardExtended = ({
  id,
  title,
  artist,
  uploader,
  genre,
  duration,
  size,
  dateUpload,
  onClick,
}: SongCardProps) => {
  const [loading, setLoading] = useState<boolean>(true);
  const [imageUrl, setImageUrl] = useState<string | null>(null);

  useEffect(() => {
    songService
      .getImageUrl(id)
      .then(setImageUrl)
      .finally(() => setLoading(false));
  }, []);

  const formattedDate = useMemo(() => {
    const d = new Date(dateUpload);
    const day = String(d.getDate()).padStart(2, "0");
    const month = String(d.getMonth() + 1).padStart(2, "0");
    const year = d.getFullYear();
    return `${day}/${month}/${year}`;
  }, [dateUpload]);

  return (
    <div
      className="grid grid-cols-4 items-center text-white gap-16"
      onClick={onClick}
    >
      <div className="flex flex-row gap-2">
        <img
          className="w-[65px] h-[65px] rounded-xl hover:shadow-sm"
          src={
            !loading && imageUrl !== null && imageUrl !== ""
              ? imageUrl
              : portraitNotFound
          }
          alt="Portada de la canción"
        />
        <div className="flex flex-col">
          <span className="text-2xl">{title}</span>
          <span className="text-white/70 text-xl">{artist}</span>
        </div>
      </div>
      <span className="text-white/70 text-2xl">{genre}</span>
      <span className="text-white/70 text-xl">{formattedDate}</span>
      <InfoCard
        title={title}
        artist={artist}
        genre={genre}
        uploader={uploader}
        dateUpload={dateUpload}
        duration={duration}
        size={size}
      />
    </div>
  );
};
