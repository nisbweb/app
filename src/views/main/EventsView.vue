<template>
  <div class="container">
    <h1 class="title">EVENTS</h1>

    <router-link
      :to="{name: 'event', params: { id: event.id }}"
      v-for="event in events.slice().reverse()"
      :key="event.id"
    >
      <img :src="event.image" :alt="event.title" class="fix-height" />
      <b>{{event.title}}</b> <br><br>
    </router-link>
    <b-loading :is-full-page="true" :active.sync="isLoading" :can-cancel="false"></b-loading>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventsView",
  data() {
    return {
      events: [],
      isLoading: false
    };
  },
  methods: {
    loadEvents() {
      this.isLoading = true;
      axios.get("https://nisb-events.herokuapp.com/events").then(response => {
        this.events = response.data;
        this.isLoading = false;
      });
    }
  },
  mounted() {
    this.loadEvents();
  }
};
</script>

<style scoped>
.hidden {
  display: none;
}
</style>