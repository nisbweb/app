import Vue from 'vue'
import Router from 'vue-router'

import HomeView from './views/HomeView.vue'
import EventsView from './views/EventsView.vue'
import EventView from './views/EventView.vue'
import NoticesView from './views/NoticesView.vue'
import ProfileView from './views/ProfileView.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home',
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

