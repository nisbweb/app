<template>
  <div class="container">
    <h1 class="title">ANNOUNCEMENTS</h1>

    <div class="box" v-for="notice in notices.slice().reverse()" :key="notice.id">
      <div class="is-dark">{{notice.topic}}</div>
      <div>{{notice.timestamp}}</div>
      {{notice.message}}
    </div>
    <b-loading :is-full-page="true" :active.sync="isLoading" :can-cancel="false"></b-loading>
  </div>
</template>
next 
<script>
import axios from "axios";

export default {
  name: "AnnouncementsView",
  data() {
    return {
      notices: [],
      isLoading:false
    };
  },
  methods: {
    loadNotices() {
      this.isLoading=true
      axios.get("https://nisb-events.herokuapp.com/notices").then(response => {
        this.notices = response.data;
        this.isLoading=false
      });
    }
  },
  created() {
    this.loadNotices();
  }
};
</script>

<style scoped>
.hidden {
  display: none;
}
</style>