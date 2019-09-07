<template>
  <div id="app">
    <home-view :show="'HOME'==currentView"  @viewEvent="showEvent"/>
    <events-view :show="'EVENTS'==currentView" @viewEvent="showEvent"/>
    <announcements-view :show="'ANNOUNCEMENTS'==currentView"/>
    <profile-view :show="'PROFILE'==currentView"/>

    <event-view :show="'EVENT'==currentView" :event_id="current_event_id" :source="show_event_source" @back="changeView"></event-view>

    <nav-bar
      @view-changed="changeView"/>
  </div>
</template>

<script>
import Vue from 'vue'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'

Vue.use(Buefy)

import HomeView from './views/HomeView.vue'
import EventsView from './views/EventsView.vue'
import AnnouncementsView from './views/AnnouncementsView.vue'
import ProfileView from './views/ProfileView.vue'

import EventView from './views/EventView.vue'

import NavBar from './components/NavBar.vue'

export default {
  name: 'app',
  data(){
    return{
      currentView: "HOME",
      current_event_id:"",
      show_event_source:""
    }
  },
  components: {
    HomeView,EventsView,AnnouncementsView,ProfileView,
    NavBar, EventView
  },
  methods:{
    changeView(v){
      if (v==""){
        v="HOME"
      }
        this.currentView = v
    },
    showEvent(id,source){
      this.current_event_id = id
      this.show_event_source = source
      this.changeView("EVENT")
    }
  }
  
}
</script>

<style>

.container{
  padding: 60px 20px;
}

.title{
  padding: 20px 10px;
}

.hidden{
  display: none;
}
</style>
