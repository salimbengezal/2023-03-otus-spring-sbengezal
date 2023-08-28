<script setup>

import { storeToRefs } from 'pinia'
import { useCommentStore } from '../../stores/comment'

const { book, comments, model } = storeToRefs(useCommentStore())

const { fetchBookWithComments, deleteComment, addComment } = useCommentStore()

fetchBookWithComments()

</script>

<template>
    <div class="container mt-5">
        <div class="card">
            <div class="card-header">
                <h3>Book details</h3>
            </div>
            <div class="card-body">
                <div>
                    <h6><b>Name:</b> <span>{{ book.name }}</span></h6>
                    <h6><b>Year:</b> <span>{{ book.releaseYear }}</span></h6>
                    <h6><b>Genre:</b> <span>{{ book.genre.name }}</span></h6>
                    <h6><b>Author:</b> <span>{{ book.author.name }}</span></h6>
                </div>
            </div>
            <div class="card-body">
                <h5>Comments</h5>
                <div v-if="comments.length == 0">
                    <p>No comments</p>
                </div>
                <div class="container" v-if="comments">
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
                <textarea cols="75" v-model="model.comment.message" rows="10"></textarea>
                <div>
                    <button type="submit" @click="addComment" class="btn btn-success">Add</button>
                </div>
            </div>
        </div>

    </div>
</template>