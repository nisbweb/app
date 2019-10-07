<template>
  <div class="container">
    <h1 class="title">ANNOUNCEMENTS</h1>

    <a-button-group>
      <a-button type="dashed" style="font-size:1.5em;" size="large">Settings </a-button>
      <a-button
        type="primary"
        style="font-size:1.5em;"
        size="large"
        @click="openSettings()"
        icon="sliders"
      ></a-button>
    </a-button-group>

    <br />
    <br />

    <a-list bordered :dataSource="notices.slice().reverse()" :loading="isLoading">
      <a-list-item slot="renderItem" slot-scope="item, index" v-if="checkTopic(item.topic)">
        <a-list-item-meta>
          <a-tag slot="avatar" color="blue">{{item.topic}}</a-tag>
          <template slot="title">{{item.message}}</template>
          <template slot="description">{{item.timestamp}}</template>
        </a-list-item-meta>
      </a-list-item>
    </a-list>

    <a-drawer
      title="Filter &amp; Sort"
      placement="right"
      :closable="false"
      @close="closeSettings()"
      :visible="settingsVisible"
    >
    Topic:
      <a-select defaultValue="all" style="width: 120px" v-model="currentTopic">
        <a-select-option value="all">All</a-select-option>
        <a-select-option value="general">General</a-select-option>
        <a-select-option value="blog">Blog</a-select-option>
        <a-select-option value="mridul.kepler@gmail.com">Me</a-select-option>
      </a-select>

      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-drawer>
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
      isLoading: false,
      settingsVisible: false,
      currentTopic:"all"
    };
  },
  methods: {
    openSettings() {
      this.settingsVisible = true;
    },
    closeSettings() {
      this.settingsVisible = false;
    },
    checkTopic(topic){
      if (this.currentTopic == "all") {
        return true
      }else{
        return (this.currentTopic == topic)
      }
    },
    loadNotices() {
      this.isLoading = true;
      axios.get("https://nisb-events.herokuapp.com/notices?email=" + localStorage.getItem("email")).then(response => {
        this.notices = response.data;
        this.isLoading = false;
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