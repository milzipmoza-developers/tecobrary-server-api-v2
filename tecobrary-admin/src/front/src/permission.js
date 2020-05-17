import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
  // start progress bar
  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

    // determine whether the user has obtained his permission roles through getInfo
    const hasRoles = store.getters.roles && store.getters.roles.length > 0
    if (hasRoles) {
      next()
    } else {
      try {
        const { roles } = await store.dispatch('GetUserInfo')
        const accessRoutes = await store.dispatch('permission/generateRoutes', roles)

        router.addRoutes(accessRoutes)

        next({ ...to, replace: true })
      } catch (error) {
        if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
          next()
        } else {
          Message.info('로그인을 해주세요.')
          next('/login')
          NProgress.done()
        }
      }
    }
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
