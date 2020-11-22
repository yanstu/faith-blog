import axios from 'axios';
import Element from 'element-ui';
import router from '@/router';
import store from '@/store';

axios.defaults.baseURL = 'http://localhost/';

//前置拦截
axios.interceptors.request.use(
  (config) => {
    // Do something before request is sent
    return config;
  },
  (error) => {
    // Do something with request error
    return Promise.reject(error);
  }
);

//后置拦截
axios.interceptors.response.use(
  (response) => {
    let res = response.data;
    if (res.code === 200) {
      return response;
    } else {
      Element.Message({
        showClose: true,
        message: res.code + '：' + res.msg,
        type: 'error',
        duration: 3 * 1000,
      });
      return Promise.reject(res.msg);
    }
  },
  (error) => {
    if (error.response.data) {
      error.message = error.response.data.msg;
    }
    if (error.response.status === 401) {
      store.commit('REMOVE_INFO');
      router.push('/login');
    }
    Element.Message({
      showClose: true,
      message: error.message,
      type: 'error',
      duration: 3 * 1000,
    });
    return Promise.reject(error);
  }
);
