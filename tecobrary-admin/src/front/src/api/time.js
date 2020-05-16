import request from '@/utils/request'

export function requestTime() {
  return request.get('/api/time');
}

