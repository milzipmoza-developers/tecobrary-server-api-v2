import Layout from '@/layout'

const wishBookRouter = {
    path: '/wishBook',
    component: Layout,
    redirect: '/wishBook/index',
    name: '신청도서 관리',
    meta: {
        title: '신청도서 관리',
        icon: 'list'
    },
    children: [
        {
            path: 'index',
            component: () => import('@/views/wishbook/index'),
            name: '신청도서 처리',
            meta: { title: '신청도서 처리' },
            roles: ['ROLE_ADMIN']
        },
        {
            path: 'history',
            component: () => import('@/views/wishbook/history'),
            name: '신청도서 히스토리',
            meta: { title: '신청도서 히스토리' },
            roles: ['ROLE_ADMIN']
        }
    ]
}

export default wishBookRouter
