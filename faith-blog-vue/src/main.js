import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import Element from 'element-ui';
import axios from 'axios';
import header from './components/header';
import footer from './components/footer';
import i18n from './i18n/i18n';
//markdown编辑器
import mavonEditor from 'mavon-editor';
import moment from 'moment/moment';
import VueParticles from 'vue-particles'  

//样式、js
import './plugins/axios.js';
import './assets/css/style.css';
import './assets/css/scroll.css';
import './assets/css/media.css';
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/display.css';
import 'mavon-editor/dist/css/index.css';
import 'font-awesome/css/font-awesome.min.css';
import 'moment/moment.js';
// 路由拦截
import './plugins/permission.js';
import  'lib-flexible'


Vue.use(VueParticles) 
Vue.use(mavonEditor);
Vue.use(Element);
Vue.prototype.$axios = axios;
Vue.config.productionTip = false;
Vue.component('f-header', header);
Vue.component('f-footer', footer);

Vue.filter('moment', function(value, formatString) {
  formatString = formatString || 'YYYY年MM月DD日';
  return moment(value).format(formatString);
});

new Vue({
  router,
  store,
  i18n,
  render: (h) => h(App),
}).$mount('#app');
