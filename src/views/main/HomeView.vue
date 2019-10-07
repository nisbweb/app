<template>
  <div class="container">
    <h1 class="title">NISB</h1>

    <h2 class="subtitle">Upcoming Events</h2>

    
    <div class="horizontal-scroll">
      <div :style="{ width: events.length*240 + 'px' }">
        <div style="width:240px; float:left;" v-for="event in events.slice().reverse()" :key="event.id">
          <router-link
            :to="{name: 'event', params: { id: event.id }}"
            style="float:left; position:relative; display:block;"
          >
            <a-card hoverable style="width: 240px; float:left;">
              <img alt="example" :src="event.image" slot="cover" />
              <a-card-meta :title="event.title">
                <template slot="description">{{event.timestamp.trim(0,10)}}</template>
              </a-card-meta>
            </a-card>
            <!-- <img  :alt="event.title" class="fix-height" /> -->
          </router-link>
        </div>
      </div>
    </div>
    


    <br />
    <br />

    <h2 class="subtitle">Latest Announcements</h2>

    <a-list
    bordered
    :dataSource="notices.slice().reverse()"
    :loading="isLoading"
  >
    <a-list-item slot="renderItem" slot-scope="item,">
      <a-list-item-meta
        :description="item.message">
        <a-tag slot="avatar" color="blue">{{item.topic}}</a-tag>

      </a-list-item-meta>
    </a-list-item>
  </a-list>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HomeView",
  data() {
    return {
      events: [],
      notices: [],
      isLoading: false
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
      this.isLoading = true;
      axios
        .get("https://nisb-events.herokuapp.com/events?after=" + date_string)
        .then(response => {
          this.events = response.data;
          this.isLoading = false;
          if (this.events.length == 0) {
            this.isLoading = true;
            axios
              .get("https://nisb-events.herokuapp.com/events")
              .then(response => {
                this.events = response.data;
                this.events = this.events.slice(0, 10);
                this.isLoading = false;
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
      this.isLoading = true;
      axios
        .get("https://nisb-events.herokuapp.com/notices?after=" + date_string)
        .then(response => {
          this.notices = response.data;
          this.isLoading = false;
          if (this.notices.length == 0) {
            this.isLoading = true;
            axios
              .get("https://nisb-events.herokuapp.com/notices")
              .then(response => {
                this.notices = response.data;
                this.notices = this.notices.slice(0, 10);
                this.isLoading = false;
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
  white-space: nowrap;
}

.fix-height {
  height: 150px;
  margin-left: 10px;
}
</style>
