import { createRouter, createWebHistory } from 'vue-router'
import MainLayout from '../layout/MainLayout.vue'
import Supplier from '../views/Supplier.vue'
import User from '../views/User.vue'
import Dashboard from '../views/Dashboard.vue'
import Settings from '../views/Settings.vue'

const routes = [
  {
    path: '/',
    component: MainLayout,
    redirect: '/dashboard',
    children: [
      {
        path: '/dashboard',
        name: 'Dashboard',
        component: Dashboard,
        meta: { title: '数据概览' }
      },
      {
        path: '/supplier',
        name: 'Supplier',
        component: Supplier,
        meta: { title: '供应商管理' }
      },
      {
        path: '/user',
        name: 'User',
        component: User,
        meta: { title: '用户管理' }
      },
      {
        path: '/settings',
        name: 'Settings',
        component: Settings,
        meta: { title: '系统配置' }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title + ' - 供应商管理系统'
  }
  next()
})

export default router
