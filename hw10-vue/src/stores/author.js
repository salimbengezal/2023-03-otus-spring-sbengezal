import { defineStore } from 'pinia'
export const useAuthorStore = defineStore({
    id: 'author',
    state: () => ({
        authors: [],
    }),
    actions: {
        async fetchAuthors() {
            this.authors = []
            this.authors = await fetch('/api/author')
                .then(response => response.json())
        }
    }
})