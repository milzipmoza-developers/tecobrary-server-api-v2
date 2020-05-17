import request from '@/utils/request'

export const searchBook = (query) => request({
  url: '/api/naver-search',
  method: 'get',
  params: query
})
