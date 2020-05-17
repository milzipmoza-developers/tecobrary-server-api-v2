import axios from 'axios'
import { Message } from 'element-ui'

// create an axios instance
const service = axios.create({
  timeout: 5000 // request timeout
})

// response interceptor
service.interceptors.response.use(
  response => {
    return response.data
  },
  error => {
    console.log('err' + error) // for debug
    if(error.response.status === 401) {
      Message({
        message: '인증이 만료되었습니다.',
        type: 'error',
        duration: 5 * 1000
      })
      setTimeout(() => {
        window.location.href='/logout'
      }, 1000)
      return
    }
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
