import { createRouter, createWebHistory } from 'vue-router'
import Supplier from '../views/Supplier.vue'

const routes = [
  {
    path: '/',
    redirect: '/supplier'
  },
  {
    path: '/supplier',
    name: 'Supplier',
    component: Supplier,
    meta: { title: '供应商管理' }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

export default router
