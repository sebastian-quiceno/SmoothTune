import axios from "axios";
import { type Song, type UploadSong } from '../types/song'
import apiClient from "../apis/apiClient";

import {type Page} from '../types/page'

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
	},

	getSongsUploadedByUserId: async (id: number): Promise<Song[]> => {
		try {
			const response = await apiClient.get<Song[]>(`/songs/getSongsUploadedByUserId/${id}`);
			return response.data
		} catch (error) {
			throw new Error(getApiErrorMessage(error, "Error al obtener las canciones subidas por el usuario con id: " + id))
		}
	},

	getSongs: async (page?: number): Promise<Page<Song>> => {
		try {
			const response = await apiClient.get<Page<Song>>(`/songs/getSongs?page=${page?page:0}&size=10`);
			console.log("Pagina canciones: ")
			console.log(response.data)	
			return response.data
		} catch (error) {
			throw new Error(getApiErrorMessage(error, "Error al obtener las canciones de la pagina: "+page))
		}
	},

	getImageUrl: async (id: number): Promise<string> => {
		try {
			const response = await apiClient.get<string>(`/songs/getImageUrl/${id}`);
			return response.data;

		} catch (error) {
			throw new Error(getApiErrorMessage(error, "Error al obtener url de la imagen"))
		}
	},

	getAudioUrl: async (id: number): Promise<string> => {
		try {
			const response = await apiClient.get<string>(`/songs/getAudioUrl/${id}`);
			return response.data;

		} catch (error) {
			throw new Error(getApiErrorMessage(error, "Error al obtener url de la cancion"))
		}
	},

	getSong: async (id: number): Promise<Song> => {
		try {
			const response = await apiClient.get<Song>(`/songs/getSong/${id}`);
			return response.data
		} catch (error) {
			throw new Error(getApiErrorMessage(error, "Error al obtener la cancion con id: " + id))
		}
	}


}
