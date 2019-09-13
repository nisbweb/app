<template>
    <section class="fixed-bottom">
      <router-link to="/home">
        <b-button type="is-light navbar-button" class="one-four">
          <i class="fas fa-home"></i>
        </b-button>
      </router-link>
      <router-link to="/events">
        <b-button type="is-light navbar-button" class="one-four">
          <i class="fas fa-calendar-check"></i>
        </b-button>
      </router-link>
      <router-link to="/notices">
        <b-button type="is-light navbar-button" class="one-four">
          <i class="fas fa-bell"></i>
        </b-button>
      </router-link>
      <router-link to="/profile">
        <b-button type="is-light navbar-button" class="one-four">
          <i class="fas fa-user"></i>
        </b-button>
      </router-link>
    </section>
</template>

<script>
export default {
    name: "NavBar"
}
</script>

<style scoped>
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
}
.box{
    padding: 0;
}
</style>
