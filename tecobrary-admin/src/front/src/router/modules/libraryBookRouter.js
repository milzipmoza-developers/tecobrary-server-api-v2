import Layout from '@/layout'

const libraryBookRouter = {
    path: '/libraryBook',
    component: Layout,
    redirect: '/libraryBook/list',
    name: '장서 관리',
    meta: {
        title: '장서 관리',
        icon: 'education'
    },
    children: [
        {
            path: 'index',
            component: () => import('@/views/librarybook/index'),
            name: '보유 장서 관리',
            meta: { title: '보유 장서 관리' },
            roles: ['ADMIN']
        },
        {
            path: 'new',
            component: () => import('@/views/librarybook/new'),
            name: '신규 장서 등록',
            meta: { title: '신규 장서 등록' },
            roles: ['ADMIN']
        }
    ]
}

export default libraryBookRouter
