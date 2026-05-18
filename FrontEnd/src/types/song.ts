import { type Artist } from './artist'
import { type Genre } from './genre'

export type UploadSong = {
  song: File;
  image: File;
  title: string;
  artistId: number;
  userId: number;
  genreId: number;
}


export type Song = {
  id: number;
  tittle: string;
  artist: Artist;
  uploader: string;
  genre: Genre
  duration: number
  size: number
  uploadedAt: Date

}

export type Track = {
  id: number
  title: string
  artist?: string
}

