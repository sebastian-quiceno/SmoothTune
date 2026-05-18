import { create } from "zustand"
import { type Track } from "../types/song"
import { songService } from "../services/songService"
import { userSongService } from '../services/userSongService'

const audio = new Audio()

interface PlayerStore {
  currentTrack: Track | null
  queue: Track[]
  currentIndex: number
  isPlaying: boolean
  currentTime: number
  duration: number
  volume: number
  play: (track: Track, queue?: Track[], userSongId?:number) => Promise<void>
  pause: () => void
  resume: () => Promise<void>
  seek: (time: number) => void
  setVolume: (volume: number) => void
  setQueue: (tracks: Track[]) => void
  next: () => Promise<void>
  previous: () => Promise<void>
}

export const usePlayerStore = create<PlayerStore>((set, get) => ({
  currentTrack: null,
  queue: [],
  currentIndex: 0,
  isPlaying: false,
  currentTime: 0,
  duration: 0,
  volume: 1,

  // ======================================================
  // PLAY (NOW FETCHES URL FROM BACKEND)
  // ======================================================
  play: async (track, queue, userSongId) => {
    try {
      //pedir URL al backend
      const url = await songService.getAudioUrl(track.id)

      //set audio source dinámico
      audio.src = url

      await audio.play()

      if (userSongId) {
        userSongService.incrementTimesPlayed(userSongId)
      }

      const index =
        queue?.findIndex((t) => t.id === track.id) ?? 0

      set({
        currentTrack: track,
        queue: queue ?? get().queue,
        currentIndex: index,
        isPlaying: true
      })
    } catch (error) {
      console.error("Error playing track:", error)
    }
  },

  // ======================================================
  pause: () => {
    audio.pause()
    set({ isPlaying: false })
  },

  // ======================================================
  resume: async () => {
    try {
      await audio.play()
      set({ isPlaying: true })
    } catch (error) {
      console.error(error)
    }
  },

  // ======================================================
  seek: (time) => {
    audio.currentTime = time
    set({ currentTime: time })
  },

  // ======================================================
  setVolume: (volume) => {
    audio.volume = volume
    set({ volume })
  },

  // ======================================================
  setQueue: (tracks) => {
    set({ queue: tracks })
  },

  // ======================================================
  next: async () => {
    const { queue, currentIndex } = get()

    const nextIndex = currentIndex + 1

    if (nextIndex >= queue.length) return

    const nextTrack = queue[nextIndex]

    try {
      const url = await songService.getAudioUrl(nextTrack.id)

      audio.src = url
      await audio.play()

      set({
        currentTrack: nextTrack,
        currentIndex: nextIndex,
        isPlaying: true
      })
    } catch (error) {
      console.error("Error playing next track:", error)
    }
  },

  // ======================================================
  previous: async () => {
    const { queue, currentIndex } = get()

    const prevIndex = currentIndex - 1

    if (prevIndex < 0) return

    const prevTrack = queue[prevIndex]

    try {
      const url = await songService.getAudioUrl(prevTrack.id)

      audio.src = url
      await audio.play()

      set({
        currentTrack: prevTrack,
        currentIndex: prevIndex,
        isPlaying: true
      })
    } catch (error) {
      console.error("Error playing previous track:", error)
    }
  }
}))


//EVENT LISTENERS

audio.addEventListener("timeupdate", () => {
  usePlayerStore.setState({
    currentTime: audio.currentTime
  })
})

audio.addEventListener("loadedmetadata", () => {
  usePlayerStore.setState({
    duration: audio.duration
  })
})

audio.addEventListener("play", () => {
  usePlayerStore.setState({ isPlaying: true })
})

audio.addEventListener("pause", () => {
  usePlayerStore.setState({ isPlaying: false })
})

audio.addEventListener("ended", async () => {
  const { next } = usePlayerStore.getState()

  await next()
})