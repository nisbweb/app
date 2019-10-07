import Vue from 'vue'
import Router from 'vue-router'

import HomeView from './views/main/HomeView.vue'
import EventsView from './views/main/EventsView.vue'
import EventView from './views/main/EventView.vue'
import NoticesView from './views/main/NoticesView.vue'
import ProfileView from './views/main/ProfileView.vue'

import LoginView from './views/auth/LoginView.vue'
import SignupView from './views/auth/SignupView.vue'
import DefaultView from './views/auth/DefaultView.vue'
import ResetPasswordView from './views/auth/ResetPassword.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/default',
      name: 'default',
      component: DefaultView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/signup',
      name: 'signup',
      component: SignupView
    },
    {
      path: '/resetpassword',
      name: 'resetpassword',
      component: ResetPasswordView
    },
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/events',
      name: 'events',
      component: EventsView
    },
    {
      path: '/event/:id',
      name: 'event',
      component: EventView
    },
    {
      path: '/notices',
      name: 'notices',
      component: NoticesView
    },
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView
    }
  ]
})

