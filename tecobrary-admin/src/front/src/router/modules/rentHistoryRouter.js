import Layout from '@/layout'

const userRouter = {
  path: '/history',
  component: Layout,
  redirect: '/history/index',
  name: '대여내역',
  meta: {
    title: '대여내역',
    icon: 'documentation',
    roles: ['ROLE_ADMIN']
  },
  children: [
    {
      path: 'index',
      component: () => import('@/views/renthistory/index'),
      name: '대시보드',
      meta: {
        title: '대시보드',
        roles: ['ROLE_ADMIN']
      },
    },
    {
      path: 'rent',
      component: () => import('@/views/renthistory/rent'),
      name: '대여 현황',
      meta: {
        title: '대여 현황',
        roles: ['ROLE_ADMIN']
      },
    },
    {
      path: 'return',
      component: () => import('@/views/renthistory/return'),
      name: '반납 기록',
      meta: {
        title: '반납 기록',
        roles: ['ROLE_ADMIN']
      },
    }
  ]
}

export default userRouter
