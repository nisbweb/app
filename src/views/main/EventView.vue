<template>
  <div class="event container">
    <h1>Event</h1>

    <router-link to="/home">
      <a-button icon="arrow-left" type="primary"></a-button>
    </router-link>
    <br />
    <br />

    <a-card hoverable :loading="isLoading">
      <img :src="event.image" slot="cover" />
      <a-card-meta :title="event.title">
        <template slot="description">
          <i>{{event.timestamp}}</i>
          <br />
          {{event.desc}}
        </template>
      </a-card-meta>
      <template class="ant-card-actions" slot="actions">
        <a-icon type="check-circle" />
        <a :href="getReminderLink" target="_blank">
          <a-icon type="clock-circle" />
        </a>
        <a-icon type="share-alt" @click="shareEvent()" />
      </template>
    </a-card>
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
  computed: {
    getReminderLink() {
      return (
        "http://www.google.com/calendar/render?action=TEMPLATE&text=" +
        this.event.title +
        "&dates=" +
        this.event.timestamp +
        "/" +
        this.event.timestamp +
        "&details=" +
        this.event.desc +
        "&location=" +
        this.event.venue
      );
    }
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
    },
    shareEvent() {
      if (navigator.canShare) {
        navigator
          .share({
            title: this.event.title,
            text: this.event.desc
          })
          .then({
            // console.log('Share was successful.')
          })
          .catch({
            // console.log('Sharing failed', error)
          });
      } else {
        // console.log("Your system doesn't support sharing files.");
      }
    }
  }
};
</script>

<style scoped>
</style>