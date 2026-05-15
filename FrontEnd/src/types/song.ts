export type UploadSong = {
  song: File;
  image: File;
  title: string;
  artistId: number;
  userId: number;
  genreId: number;
}
	 

export type Song = {
	tittle: string;
	artist: string
	uploaderId: number
	genreId: number
	getFilePath: string
	duration: number

}

