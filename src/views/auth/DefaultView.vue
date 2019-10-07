<template>
  <div class="default container is-centered columns">
    <div class="column has-text-centered">
        <br><br><br><br><br><br><br><br><br>
      <router-link to="/login">
        <a-button size="large" block type="primary">Login</a-button>
      </router-link>
      <br />
      <br />
      <router-link to="/signup">
        <a-button size="large" block type="dashed">Signup</a-button>
      </router-link>
      <br />
      <br />

      <a-button size="large" block @click="guestLogin" type="default">Guest</a-button>
    </div>
  </div>
</template>
<script>
import session from "./../../session"
export default {
  name: "DefaultView",
  methods: {
    guestLogin() {
      session.createGuestSession()
      this.$router.push("/home");
    }
  }
};
</script>

<style scoped>
</style>