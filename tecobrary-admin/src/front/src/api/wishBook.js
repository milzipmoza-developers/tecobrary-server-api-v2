import request from '@/utils/request'

export const getWishBooks = (query) => request({
  url: '/api/wishbook',
  method: 'get',
  params: Object.assign(query, {
    page: query.page
  })
})
