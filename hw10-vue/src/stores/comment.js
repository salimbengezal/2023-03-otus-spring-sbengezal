import { defineStore } from 'pinia'
import { useRoute } from 'vue-router'

export const useCommentStore = defineStore({
    id: 'comment',
    state: () => ({
        route: useRoute(),
        comments: [],
        book: null,
        bookId: '',
        book: {
            name: '',
            releaseYear: '',
            genre: '',
            author: ''
        },
        comments: [],
        model: {
            comment: {
                message: '',
                bookId: ''
            }
        }
    }),
    actions: {
        async fetchBookWithComments() {
            this.bookId = this.route.params.id
            await fetch(`/api/book/${this.bookId}`)
                .then(res => res.json())
                .then(data => {
                    this.book = data.book
                    this.comments = data.comments
                })
        },
        addComment() {
            this.model.comment.bookId = this.bookId;
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.model.comment)
            }
            fetch('/api/comment', requestOptions)
                .then(async response => {
                    if (!response.ok) {
                        const data = await response.json();
                        if (data.errors.length == 0) {
                            errors.clear();
                        } else {
                            errors = data.errors
                        }
                    } else {
                        this.errors = [];
                        this.model.comment.message = ''
                    }
                    this.fetchBookWithComments()
                })
        },
        deleteComment(id) {
            const requestOptions = {
                method: 'DELETE',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.model.comment)
            }
            fetch(`/api/comment/${id}`, requestOptions)
                .then(async response => {
                    if (!response.ok) {
                        const data = await response.json();
                        if (data.errors.length == 0) {
                            this.errors.clear();
                        } else {
                            this.errors = data.errors
                        }
                    } else {
                        this.errors = [];
                    }
                    this.fetchBookWithComments()
                })
        }
    }
})