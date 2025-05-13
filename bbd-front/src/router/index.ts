import {createRouter, createWebHistory} from 'vue-router'
import AiView from '../views/AiView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'ai',
      component: AiView,
    },
    {
      path: '/about',
      name: 'about',
      component: () => import('../views/ListView.vue'),
    },
  ],
})

export default router
