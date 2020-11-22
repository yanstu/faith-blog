import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  //定义变量
  state: {
    token: '',
    userInfo: JSON.parse(sessionStorage.getItem('userInfo')),
  },
  //相当于JavaBean中的set
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
      localStorage.setItem('token', token);
    },
    SET_USERINFO: (state, userInfo) => {
      state.userInfo = userInfo;
      sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
    },
    //移除
    REMOVE_INFO: (state) => {
      state.token = '';
      state.userInfo = {};
      localStorage.setItem('token', '');
      sessionStorage.setItem('userInfo', JSON.stringify(''));
    },
  },
  //相当于JavaBean中的get
  getters: {
    getUser: (state) => {
      return state.userInfo;
    },
  },
  actions: {},
  modules: {},
});
