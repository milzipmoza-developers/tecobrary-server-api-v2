import Layout from '@/layout'

const userRouter = {
    path: '/user',
    component: Layout,
    redirect: '/user/index',
    name: '유저 관리',
    meta: {
        title: '유저 관리',
        icon: 'people'
    },
    children: [
        {
            path: 'index',
            component: () => import('@/views/user/index'),
            name: '유저 관리',
            meta: { title: '유저 관리' },
            roles: ['ADMIN']
        },
        {
            path: 'admin',
            component: () => import('@/views/user/admin'),
            name: '어드민 관리',
            meta: { title: '어드민 관리' },
            roles: ['ADMIN']
        }
    ]
}

export default userRouter
