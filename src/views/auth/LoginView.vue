<template>
  <div class="login container">
    <h1>Login</h1>

    <br />
    <br />

    <a-card title="Login to NISB">
      <a-input placeholder="john@doe" v-model="email" size="large">
        <a-icon slot="prefix" type="user" />
      </a-input>
      <br />
      <br />
      <a-input placeholder="password" v-model="password" type="password" size="large">
        <a-icon slot="prefix" type="key" />
      </a-input>
      <br />
      <br />

      <a-button type="primary" @click="performLogin" :loading="isLoading" block size="large">Login</a-button>
      <br />
      <br />
      <router-link to="/resetpassword">
        <a-button type="link" block size="large">Reset Password</a-button>
      </router-link>
    </a-card>

  </div>
</template>
<script>
import axios from "axios";
import session from "./../../session";

export default {
  name: "LoginView",
  data() {
    return {
      email: "",
      password: "",
      isLoading: false
    };
  },
  methods: {
    performLogin() {
      this.isLoading = true;
      axios
        .post("https://nisb-auth.herokuapp.com/auth", {
          email: this.email,
          password: this.password
        })
        .then(Response => {
          this.isLoading = false;
          let r = Response.data;
          if (r.status === "ok") {
            session.createSession(this.email, r.auth);
            this.$router.replace("/");
          } else {
            this.$buefy.toast.open({
              message: "email or password is incorrect!",
              type: "is-danger"
            });
          }
        });
    }
  }
};
</script>

<style scoped>
</style>