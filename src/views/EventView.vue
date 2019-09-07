<template>
  <div :class="{hidden:!show, container:true}">
      <b-button @click="$emit('back',source)" class="go-up">&lt;</b-button>
    <h1 class="title">Event</h1>
    <b class="subtitle">{{event.title}}</b>
    <p>{{event.timestamp}}</p>
    <br>
    <img :src="event.image" alt="">
    <p>{{event.desc}}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "EventView",
  props: ["show","event_id","source"],
  data(){
      return{
          event:{title:"",image:"",desc:""}
      }
  },
  methods: {
    loadEvent(id) {
      axios.get("https://nisb-events.herokuapp.com/event?event_id="+id).then(response => {
        this.event = response.data;
      });
    }
  },
  watch:{
      event_id(){
        this.loadEvent(this.event_id)
      }
  }
  
  
  
};
</script>

<style scoped>
.hidden {
  display: none;
}
.go-up{
    margin-top:-30px;
}
</style>