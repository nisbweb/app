<template>
  <div class="login container">
    <h1>Login</h1>

    <br />
    <br />
    <b-field label="Email" type="is-danger" message="This email is invalid">
      <b-input type="email" value="john@" maxlength="30" v-model="email"></b-input>
    </b-field>
    <b-field label="Password">
      <b-input type="password" value="iwantmytreasure" password-reveal v-model="password"></b-input>
    </b-field>
    <b-button type="is-info width-100" @click="performLogin" :loading="isLoading">Login</b-button>
  </div>
</template>
<script>
import axios from "axios";

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
            localStorage.setItem("email", this.email);
            localStorage.setItem("token", r.auth);
            this.$router.replace("/home");
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