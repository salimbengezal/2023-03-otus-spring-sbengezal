<script setup>
import { storeToRefs } from 'pinia'
import { useBooksStore } from '../stores/book'

const { books, loading } = storeToRefs(useBooksStore())
const { fetchBooks, removeBook } = useBooksStore()

const columns = ['#', 'Name', 'Release Year', 'Author', 'Genre', 'Actions']

fetchBooks()

</script>

<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Book List</h3>
            </div>

            <div class="card-body">
                <p v-if="loading">Loading...</p>
                <table v-if="books" class="table table-bordered">
                    <thead>
                        <tr>
                            <th class="table-success text-center" v-for="column in columns"><strong>{{ column }}</strong>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(book, index) in books">
                            <td>{{ index + 1 }}</td>
                            <td>
                                <RouterLink :to="{ path: '/book/' + book.id }">{{ book.name }}</RouterLink>
                            </td>
                            <td>{{ book.releaseYear }}</td>
                            <td>{{ book.author.name }}</td>
                            <td>{{ book.genre.name }}</td>
                            <td>
                                <RouterLink :to="{ path: '/book/' + book.id + '/edit' }" class="btn btn-primary float-left">
                                    Edit
                                </RouterLink>
                                <button type="button" class="btn btn-danger float-end" @click=removeBook(index)>
                                    Remove
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <div v-if="books.length == 0">No books found</div>
            </div>
        </div>
    </div>
</template>

<style></style>