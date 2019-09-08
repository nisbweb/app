<template>
  <div class="container">
    <h1 class="title">NISB</h1>

    <h2 class="subtitle">Upcoming Events</h2>

    <div class="horizontal-scroll">
      <router-link
        :to="{name: 'event', params: { id: event.id }}"
        v-for="event in events"
        :key="event.id">
        <img :src="event.image" :alt="event.title" class="fix-height" />
      </router-link>
    </div>
    <br />
    <br />

    <h2 class="subtitle">Latest Announcements</h2>
    <div class="card-list">
      <div class="box" v-for="notice in notices" :key="notice.id">{{notice.message}}</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HomeView",
  props: {
    show: Boolean
  },
  data() {
    return {
      events: [],
      notices: []
    };
  },
  methods: {
    loadEvents() {
      var d = new Date();
      var date_string =
        d.getDate() +
        "/" +
        (d.getMonth() + 1) +
        "/" +
        d.getFullYear() +
        " " +
        d.getHours() +
        ":" +
        d.getMinutes() +
        ":" +
        d.getSeconds();
      axios
        .get("https://nisb-events.herokuapp.com/events?after=" + date_string)
        .then(response => {
          this.events = response.data;
          if (this.events.length == 0) {
            axios
              .get("https://nisb-events.herokuapp.com/events")
              .then(response => {
                this.events = response.data;
                this.events.sort((a, b) =>
                  a.timestamp > b.timestamp ? 1 : -1
                );
                this.events = this.events.slice(0, 10);
              });
          }
        });
    },
    loadNotices() {
      var d = new Date();
      var date_string =
        d.getDate() +
        "/" +
        (d.getMonth() + 1) +
        "/" +
        d.getFullYear() +
        " " +
        d.getHours() +
        ":" +
        d.getMinutes() +
        ":" +
        d.getSeconds();
      axios
        .get("https://nisb-events.herokuapp.com/notices?after=" + date_string)
        .then(response => {
          this.notices = response.data;
          if (this.notices.length == 0) {
            axios
              .get("https://nisb-events.herokuapp.com/notices")
              .then(response => {
                this.notices = response.data;
                this.notices.sort((a, b) =>
                  a.timestamp > b.timestamp ? 1 : -1
                );
                this.notices = this.notices.slice(0, 10);
              });
          }
        });
    }
  },
  mounted() {
    this.loadEvents();
    this.loadNotices();
  }
};
</script>

<style scoped>
.horizontal-scroll {
  overflow-x: scroll;
  overflow-y: hidden;
  height: 150px;
  white-space: nowrap;
}

.fix-height {
  height: 150px;
  margin-left: 10px;
}
</style>
