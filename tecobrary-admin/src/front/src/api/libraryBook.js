import request from '@/utils/request'

export const getLibraryBooks = (query) => request({
  url: '/api/librarybook',
  method: 'get',
  params: Object.assign(query, {
    page: query.page
  })
})

export const createLibraryBook = (data) => request({
  url: '/api/librarybook',
  method: 'post',
  data: data
})

export const getLibraryBook = (id) => request({
  url: `/api/librarybook/${id}`,
  method: 'get'
})
