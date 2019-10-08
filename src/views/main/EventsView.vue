<template>
  <div class="container">
    <h1 class="title">EVENTS</h1>

    <a-button-group>
      <a-button type="dashed" style="font-size:1.5em;" size="large">Settings</a-button>
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

    <a-card hoverable v-for="event in events.slice().reverse()" :key="event.id">
      <router-link slot="cover" :to="{name: 'event', params: { id: event.id }}">
        <img alt="example" :src="event.image" style="width:100%;" />
      </router-link>

      <a-card-meta>
        <router-link slot="title" :to="{name: 'event', params: { id: event.id }}">
          <template>{{event.title}}</template>
        </router-link>
        <template slot="description">
          <i>{{event.timestamp}}</i>
        </template>
      </a-card-meta>
      <template class="ant-card-actions" slot="actions">
        <a-icon type="check-circle" />
        <a-icon type="share-alt" @click="shareEvent(event)" />
      </template>
    </a-card>

    <a-drawer
      title="Filter &amp; Sort"
      placement="right"
      :closable="false"
      @close="closeSettings()"
      :visible="settingsVisible"
    >
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-drawer>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EventsView",
  data() {
    return {
      events: [],
      isLoading: false,
      settingsVisible: false
    };
  },
  methods: {
    openSettings() {
      this.settingsVisible = true;
    },
    closeSettings() {
      this.settingsVisible = false;
    },
    loadEvents() {
      this.isLoading = true;
      axios.get("https://nisb-events.herokuapp.com/events").then(response => {
        this.events = response.data;
        this.isLoading = false;
      });
    },
    shareEvent(event) {
      if (navigator.canShare) {
        navigator
          .share({
            title: event.title,
            text: event.desc
          })
          .then({
            // console.log('Share was successful.')
          })
          .catch({
            // console.log('Sharing failed', error)
          });
      } else {
        // console.log('Your system doesn\'t support sharing files.');
      }
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
.ant-card {
  margin-bottom: 20px;
}
</style>