<script setup>

import { storeToRefs } from 'pinia'
import { useBooksStore } from '../../stores/book'
import { useAuthorStore } from '../../stores/author'
import { useGenreStore } from '../../stores/genre'

const { model, errors } = storeToRefs(useBooksStore())
const { genres } = storeToRefs(useGenreStore())
const { authors } = storeToRefs(useAuthorStore())

const { fetchAuthors } = useAuthorStore()
const { fetchGenres } = useGenreStore()
const { updateBook, fetchBook } = useBooksStore()

fetchGenres()
fetchAuthors()
fetchBook()

</script>

<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Edit book</h3>
            </div>
            <div class="card-body">

                <ul class="alert alert-warning" v-if="Object.keys(errors).length > 0">
                    <li class="mb-0 ms-3" v-for="(error, index) in errors" :key="index">
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