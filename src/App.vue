<template>
  <div id="app">
    <router-view />
    <section class="fixed-bottom">
      <router-link to="/home">
        <b-button type="is-light" class="one-four">
          <i class="fas fa-home"></i>
        </b-button>
      </router-link>
      <router-link to="/events">
        <b-button type="is-light" class="one-four">
          <i class="fas fa-calendar-check"></i>
        </b-button>
      </router-link>
      <router-link to="/notices">
        <b-button type="is-light" class="one-four">
          <i class="fas fa-bell"></i>
        </b-button>
      </router-link>
      <router-link to="/profile">
        <b-button type="is-light" class="one-four">
          <i class="fas fa-user"></i>
        </b-button>
      </router-link>
    </section>
  </div>
</template>

<script>

export default {
  name: "app",
};
</script>>

<style>
.container {
  padding: 60px 20px;
}

.title {
  padding: 20px 10px;
}

.one-four{
    width:25%;
    border: 0;
    font-size: 1.3em;
    border-radius: 0;
}
.one-four:hover{
    border: 0;
}
.one-four:active{
    border: 0;
}
.fixed-bottom{
    position: fixed;
    bottom: 0;
    width: 100%;
    left: 0;
    padding:0;
}
</style>
