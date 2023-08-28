import { defineStore } from 'pinia'

import { useRoute, useRouter } from 'vue-router'

export const useBooksStore = defineStore({
    id: 'book',
    state: () => ({
        router: useRouter(),
        route: useRoute(),
        books: [],
        loading: false,
        isProceeded: false,
        errors: [],
        model: {
            book: {
                name: '',
                releaseYear: '',
                genreId: '',
                authorId: ''
            }
        },
        bookId: ''
    }),
    actions: {
        async fetchBooks() {
            this.books = []
            this.loading = true
            this.books = await fetch('/api/book')
                .then(response => response.json())
            this.loading = false
        },
        removeBook(index) {
            const id = this.books[index].id
            fetch('/api/book/' + id, { method: 'DELETE' })
                .then(() => this.books.splice(index, 1))
        },
        async saveBook() {
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.model.book)
            }
            await fetch('/api/book', requestOptions)
                .then(async response => {
                    if (!response.ok) {
                        this.isProceeded = false;
                        const data = await response.json();
                        if (data.errors.length == 0) {
                            this.errors.clear();
                        } else {
                            this.errors = data.errors
                        }
                    } else {
                        this.isProceeded = true;
                        this.errors = [];
                        this.model.book = {
                            name: '',
                            releaseYear: '',
                            genreId: '',
                            authorId: ''
                        }
                    }
                })
        },
        async fetchBook() {
            this.bookId = this.route.params.id
            await fetch(`/api/book/${this.bookId}`)
                .then(res => res.json())
                .then(data => {
                    this.model.book.name = data.book.name;
                    this.model.book.releaseYear = data.book.releaseYear;
                    this.model.book.genreId = data.book.genre.id;
                    this.model.book.authorId = data.book.author.id;
                })
        },
        async updateBook() {
            const requestOptions = {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.model.book)
            }
            await fetch(`/api/book/${this.bookId}`, requestOptions)
                .then(async response => {
                    if (!response.ok) {
                        const data = await response.json();
                        this.errors = data.errors
                    } else {
                        this.errors = [];
                        this.router.push('/')
                    }
                })

        }
    }
})