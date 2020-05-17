import Vue from 'vue'
import Router from 'vue-router'
/* Layout */
import Layout from '@/layout'
import libraryBookRouter from "./modules/libraryBookRouter";
import wishBookRouter from "./modules/wishBookRouter";
import userRouter from "./modules/userRouter";
import rentHistoryRouter from "./modules/rentHistoryRouter";

Vue.use(Router)

export const constantRoutes = [
  {path: '/login', component: () => import('@/views/login/index'), hidden: true},
  {path: '/404', component: () => import('@/views/error-page/404'), hidden: true},
  {path: '/401', component: () => import('@/views/error-page/401'), hidden: true},
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index'),
      name: '대시보드',
      meta: {title: '대시보드', icon: 'dashboard', noCache: true}
    }]
  }
]

/**
 * asyncRoutes
 * the routes that need to be dynamically loaded based on user roles
 */
export const asyncRoutes = [
  userRouter,
  libraryBookRouter,
  wishBookRouter,
  rentHistoryRouter,

  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
