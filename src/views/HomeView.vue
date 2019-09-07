<template>
  <div :class="{hidden:!show, container:true}">
    <h1 class="title">NISB</h1>

    <h2 class="subtitle">Upcoming Events</h2>

    <div class="horizontal-scroll">
      <img
        v-for="event in events"
        :key="event.id"
        :src="event.image"
        :alt="event.title"
        @click="$emit('viewEvent',event.id,'HOME')"
        class="fix-height"
      />
      <!-- <img src="https://www.dropbox.com/s/bb4m4hojb8vhhol/R%20Pi%20%281%29.png?raw=1" class="fix-height">
      <img src="https://www.dropbox.com/s/n6xz8vtqiivlzbz/SelfHosted%20%281%29.png?raw=1" class="fix-height">
      <img src="https://www.dropbox.com/s/bb4m4hojb8vhhol/R%20Pi%20%281%29.png?raw=1" class="fix-height">-->
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
        });
    }
  },
  mounted() {
    this.loadEvents()
    this.loadNotices()
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
