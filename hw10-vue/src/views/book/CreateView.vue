<script>

export default {
    name: 'create',
    data() {
        return {
            isProceeded: false,
            errors: [],
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
        clearModel() {
            this.model.book = {
                name: '',
                releaseYear: '',
                genreId: '',
                authorId: ''
            }
        },
        saveBook() {
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(this.model.book)
            }
            fetch('/api/book', requestOptions)
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
                        this.clearModel();
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
        this.getGenres();
        this.getAuthors();
    }
}

</script>
<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Add new book</h3>
            </div>
            <div class="card-body">

                <div class="alert alert-success" v-if="this.isProceeded">Book added!</div>
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
                    <button @click="saveBook" type="button" class="btn btn-success">Save</button>
                </div>
            </div>
        </div>
    </div>
</template>