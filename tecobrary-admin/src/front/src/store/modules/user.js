import axios from 'axios'

const user = {
  state: {
    user: '',
    name: '',
    email: '',
    picture: '',
    roles: [],
    setting: {
      articlePlatform: []
    }
  },

  mutations: {
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_EMAIL: (state, email) => {
      state.email= email
    },
    SET_PICTURE: (state, picture) => {
      state.picture= picture
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    }
  },

  actions: {
    GetUserInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        axios.get('/api/security/user').then(response => {
          if (!response.data) {
            reject('error')
          }
          const data = response.data

          if (data.roles && data.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', data.roles)
          } else {
            reject('getInfo: roles must be a non-null array !')
          }
          commit('SET_NAME', data.name)
          commit('SET_EMAIL', data.email)
          commit('SET_PICTURE', data.picture)
          resolve(data)
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default user
