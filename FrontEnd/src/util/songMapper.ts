import axios from "axios";

import { songService } from '../services/songService'

import { type Song, type Track } from '../types/song'

function getApiErrorMessage(error: unknown, fallbackMessage: string) {
	if (axios.isAxiosError<{ message?: string }>(error)) {
		return error.response?.data?.message || fallbackMessage;
	}

	return fallbackMessage;
}

export const songsMapper = {
	toTrack: (songs: Song[]): Track[] => {
		try {
			const tracks: Track[] = songs.map((song) => {
				return {
					id: song.id,
					title: song.tittle,
					artist: song.artist.name
				}
			})
			return tracks
		} catch (error) {
			throw new Error(getApiErrorMessage(error, 'Error al cargar las canciones. Por favor, intenta nuevamente.'))
		}
	}
}

export const songMapper = {
	toTrack: (song: Song): Track => {
		try {
			return {
				id: song.id,
				title: song.tittle,
				artist: song.artist.name
			}
		} catch (error) {
			throw new Error(getApiErrorMessage(error, 'Error al cargar las canciones. Por favor, intenta nuevamente.'))
		}
	}
}