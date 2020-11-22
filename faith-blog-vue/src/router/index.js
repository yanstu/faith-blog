import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from '@/pages/login';
import home from '@/pages/home';
import about from '@/pages/about';
import article from '@/pages/article';
import archive from '@/pages/archive';
import apply from '@/pages/apply';
import blogedit from '@/pages/blogedit';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: home,
  },
  {
    path: '/blog/add',
    name: 'blogadd',
    component: blogedit,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/blog/:blogId/edit',
    name: 'blogedit',
    component: blogedit,
    meta: {
      requireAuth: true,
    },
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/home',
    name: 'home',
    component: home,
  },
  {
    path: '/about',
    name: 'about',
    component: about,
  },
  {
    path: '/archive',
    name: 'archive',
    component: archive,
  },
  {
    path: '/tag/:name',
    name: 'tag',
    component: archive,
  },
  {
    path: '/article/:blogId',
    name: 'article',
    component: article,
  },
  {
    path: '/apply',
    name: 'apply',
    component: apply,
  },
  {
    path: '*',
    redirect: '/',
  },
];
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});
export default router;
