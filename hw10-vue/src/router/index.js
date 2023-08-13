import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/book/HomeView.vue'
import CreateView from '../views/book/CreateView.vue'
import EditView from '../views/book/EditView.vue'
import DetailsView from '../views/book/DetailsView.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
            path: '/book/new',
            name: 'new',
            component: CreateView
        },
        {
            path: '/book/:id/edit',
            name: 'edit',
            component: EditView
        },
        {
            path: '/book/:id',
            name: 'details',
            component: DetailsView
        }
    ]
})

export default router