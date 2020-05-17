import variables from '@/styles/element-variables.scss'
import defaultSettings from '@/settings'

const { tagsView, fixedHeader, sidebarLogo } = defaultSettings

const state = {
  theme: variables.theme,
  tagsView: tagsView,
  fixedHeader: fixedHeader,
  sidebarLogo: sidebarLogo
}

const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
    }
  }
}

const actions = {
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

