import axios from "axios";
import { type Song, type UploadSong } from '../types/song'
import apiClient from "../apis/apiClient";

function getApiErrorMessage(error: unknown, fallbackMessage: string) {
	if (axios.isAxiosError<{ message?: string }>(error)) {
		return error.response?.data?.message || fallbackMessage;
	}

	return fallbackMessage;
}

export const songService = {
	uploadSong: async (request: UploadSong): Promise<Song> => {
		try {
			const formData = new FormData();

			console.log(request)

			formData.append("song", request.song);
			formData.append("image", request.image);
			formData.append("title", request.title);
			formData.append("artistId", request.artistId.toString());
			formData.append("userId", request.userId.toString());
			formData.append("genreId", request.genreId.toString());

			const response = await apiClient.post<Song>(
				"/songs/uploadSong",
				formData,
				{
					headers: {
						"Content-Type": "multipart/form-data",
					},
				}
			);
			return response.data;

		} catch (error) {
			throw new Error(getApiErrorMessage(error, "Error al subir canción"))
		}
	}

}
