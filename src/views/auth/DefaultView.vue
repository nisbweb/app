<template>
  <div class="default is-centered columns">
    <div class="column has-text-centered">
        <br><br><br><br><br><br><br><br><br>
      <router-link to="/login">
        <b-button class="width-80">Login</b-button>
      </router-link>
      <br />
      <br />
      <router-link to="/signup">
        <b-button class="width-80">Signup</b-button>
      </router-link>
      <br />
      <br />

      <b-button class="width-80" @click="guestLogin">Guest</b-button>
    </div>
  </div>
</template>
<script>
export default {
  name: "DefaultView",
  methods: {
    guestLogin() {
      localStorage.setItem("guest", "true");
      this.$router.push("/home");
    }
  }
};
</script>

<style scoped>

</style>