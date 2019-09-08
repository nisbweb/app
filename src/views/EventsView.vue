<template>
  <div class="container">
    <h1 class="title">EVENTS</h1>
    <img v-for="event in events" :key="event.id" :src="event.image" :alt="event.title" @click="$emit('viewEvent',event.id,'EVENTS')">
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'EventsView',
  data(){
    return{
      events:[]
    }
  },
  methods:{
    loadEvents(){
      axios.get("https://nisb-events.herokuapp.com/events").then(response => {
        this.events = response.data
      })
    }
  },
  mounted(){
    this.loadEvents()
  }
  
}
</script>

<style scoped>
.hidden{
  display: none;
}
</style>