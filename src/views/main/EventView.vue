<template>
  <div class="container">
    <!-- <b-button @click="$emit('back',source)" class="go-up">&lt;</b-button> -->
    <h1 class="title">Event</h1>
    <b class="subtitle">{{event.title}}</b>
    <p>{{event.timestamp}}</p>
    <br />
    <img :src="event.image" alt />
    <p>{{event.desc}}</p>
    <b-loading :is-full-page="true" :active.sync="isLoading" :can-cancel="false"></b-loading>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventView",
  data() {
    return {
      id: "", 
      event: { title: "", image: "", desc: "" },
      isLoading: false
    };
  },
  created() {
    this.id = this.$route.params.id;
    this.loadEvent(this.id);
  },
  methods: {
    loadEvent(id) {
      this.isLoading = true;
      axios
        .get("https://nisb-events.herokuapp.com/event?event_id=" + id)
        .then(response => {
          this.event = response.data;
          this.isLoading = false;
        });
    }
  }
};
</script>

<style scoped>
.hidden {
  display: none;
}
.go-up {
  margin-top: -30px;
}
</style>