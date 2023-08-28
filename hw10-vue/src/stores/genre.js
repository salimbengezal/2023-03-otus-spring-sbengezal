import { defineStore } from 'pinia'
export const useGenreStore = defineStore({
    id: 'genre',
    state: () => ({
        genres: [],
        loading: false
    }),
    actions: {
        async fetchGenres() {
            this.genres = []
            this.genres = await fetch('/api/genre')
                .then(response => response.json())
        }
    }
})