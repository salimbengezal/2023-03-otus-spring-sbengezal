<script>

export default {
    name: 'detail',
    data() {
        return {
            errors: [],
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
        }
    },
    methods: {
        getBook() {
            fetch(`/api/book/${this.bookId}`)
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
                            this.errors.clear();
                        } else {
                            this.errors = data.errors
                        }
                    } else {
                        this.errors = [];
                        this.model.comment.message = ''
                    }
                    this.getBook()
                })
        },
        deleteComment(id) {
            console.log(id)
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
                    this.getBook()
                })
        }
    },
    mounted() {
        this.bookId = this.$route.params.id;
        this.getBook();
    }
}

</script>

<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Book details</h3>
            </div>
            <div class="card-body">
                <div>
                    <h6><b>Name:</b> <span>{{ this.book.name }}</span></h6>
                    <h6><b>Year:</b> <span>{{ this.book.releaseYear }}</span></h6>
                    <h6><b>Genre:</b> <span>{{ this.book.genre.name }}</span></h6>
                    <h6><b>Author:</b> <span>{{ this.book.author.name }}</span></h6>
                </div>
            </div>
            <div class="card-body">
                <h5>Comments</h5>
                <div v-if="this.comments.length == 0">
                    <p>No comments</p>
                </div>
                <div class="container" v-if="this.comments.length > 0">
                    <div class="row" v-for="comment in comments">
                        <div class="col-sm-6 p-3 m-2 rounded" style="background-color: beige;">
                            <h6 style="font-size:small">{{ comment.updatedOn }}</h6>
                            <p>{{ comment.message }}</p>
                        </div>
                        <div class="col-sm-1 m-2 text-center">
                            <button type="button" class="btn btn-danger" @click=deleteComment(comment.id)>
                                Remove
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <h5>Write your comment</h5>
                <textarea cols="75" v-model="this.model.comment.message" rows="10"></textarea>
                <div>
                    <button type="submit" @click="this.addComment" class="btn btn-success">Add</button>
                </div>
            </div>
        </div>

    </div>
</template>