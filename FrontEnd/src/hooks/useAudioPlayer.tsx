import { useEffect, useRef, useState } from "react";
import { type Track } from "../types/song";

export function useAudioPlayer() {
  const audioRef = useRef(new Audio());

  const [currentTrack, setCurrentTrack] = useState<Track | null>(null);

  const [isPlaying, setIsPlaying] = useState(false);

  const play = async (track: Track) => {
    const audio = audioRef.current;

    if (audio.src !== track.signedUrl) {
      audio.src = track.signedUrl;
    }

    setCurrentTrack(track);

    await audio.play();

    setIsPlaying(true);
  };

  const pause = () => {
    audioRef.current.pause();

    setIsPlaying(false);
  };

  return {
    currentTrack,
    isPlaying,
    play,
    pause,
  };
}
