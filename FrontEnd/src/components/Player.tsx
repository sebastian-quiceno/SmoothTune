import { usePlayerStore } from "../hooks/usePlayerStore"
import {SkipBack, SkipForward, Play, Pause} from 'lucide-react'

export function Player() {
  const {
    currentTrack,
    isPlaying,
    currentTime,
    duration,
    pause,
    resume,
    seek,
    next,
    previous
  } = usePlayerStore()

  return (
    <div className="flex flex-col justify-center items-center p-4 bg-[#1b1b36] text-white">
      
      <span className="text-3xl">
        {currentTrack?.title ?? "No hay canción"}
      </span>

      <h2 className="text-xl text-white/50">
        {currentTrack?.artist ?? "No hay artista"}
      </h2>

      {/* CONTROLS  SkipBack, SkipForward, Play, Pause */}
      <div className="flex gap-2 mt-2">

        <SkipBack onClick={previous}/>

        {isPlaying ? (
          <Pause onClick={pause} />
        ) : (
          <Play onClick={resume} />
        )}

        <SkipForward onClick={next} />
      </div>

      {/* SEEK BAR */}
      <input
        type="range"
        min={0}
        max={duration || 0}
        value={currentTime}
        onChange={(e) => seek(Number(e.target.value))}
        className="w-full mt-2 "
      />

      <div className="text-sm justify-between">
        {Math.floor(currentTime)} / {Math.floor(duration)}
        
      </div>
    </div>
  )
}