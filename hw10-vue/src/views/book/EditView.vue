<script>

export default {
    name: 'edit',
    data() {
        return {
            bookId: null,
            errors: '',
            genres: [],
            authors: [],
            model: {
                book: {
                    name: '',
                    releaseYear: '',
                    genreId: '',
                    authorId: ''
                }
            }
        }
    },
    methods: {
        getBook() {
            fetch(`/api/book/${this.bookId}`)
                .then(res => res.json())
                .then(data => {
                    this.model.book.name = data.book.name;
                    this.model.book.releaseYear = data.book.releaseYear;
                    this.model.book.genreId = data.book.genre.id;
                    this.model.book.authorId = data.book.author.id;
                })
        },
        updateBook() {
            const requestOptions = {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.model.book)
            }
            fetch(`/api/book/${this.bookId}`, requestOptions)
                .then(async response => {
                    if (!response.ok) {
                        const data = await response.json();
                        this.errors = data.errors
                    } else {
                        this.errors = [];
                        this.$router.push('/')
                    }
                })

        },
        getGenres() {
            fetch('/api/genre')
                .then(response => response.json())
                .then(data => this.genres = data)
        },
        getAuthors() {
            fetch('/api/author')
                .then(response => response.json())
                .then(data => this.authors = data)
        }
    },
    mounted() {
        this.bookId = this.$route.params.id;
        this.getBook();
        this.getGenres();
        this.getAuthors();
    }
}

</script>
<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Edit book</h3>
            </div>
            <div class="card-body">

                <ul class="alert alert-warning" v-if="Object.keys(this.errors).length > 0">
                    <li class="mb-0 ms-3" v-for="(error, index) in this.errors" :key="index">
                        {{ error.defaultMessage }}
                    </li>
                </ul>


                <div class="mb-3">
                    <label for="name-input">Name</label>
                    <input id="name-input" v-model="model.book.name" type="text" class="form-control" />
                </div>

                <div class="mb-3">
                    <label for="year-input">Year</label>
                    <input id="year-input" v-model="model.book.releaseYear" type="text" class="form-control" />
                </div>

                <div class="mb-3">
                    <label for="genre-select">Genre</label>
                    <select id="genre-select" v-model="model.book.genreId" class="form-select">
                        <option v-for="genre in genres" v-bind:value="genre.id">
                            {{ genre.name }}
                        </option>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="author-select">Author</label>
                    <select id="author-select" v-model="model.book.authorId" class="form-select">
                        <option v-for="author in authors" v-bind:value="author.id">
                            {{ author.name }}
                        </option>
                    </select>
                </div>

                <div class="mb-3">
                    <button @click="updateBook" type="button" class="btn btn-primary">Update</button>
                </div>
            </div>
        </div>
    </div>
</template>