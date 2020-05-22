import request from '@/utils/request'

export const getAdmin = (query) => request({
  url: '/api/admin',
  method: 'get',
  params: Object.assign(query, {
    page: query.page
  })
})

export const createAdmin = (data) => request({
  url: '/api/admin',
  method: 'post',
  data: data
})
