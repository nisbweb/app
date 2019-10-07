<template>
  <div class="signup container">
    <h1>Signup</h1>

    <a-card v-if="(step==='enterEmail')">
      <p>Please enter your email to begin.</p>
      <br />
      <br />
      <a-input placeholder="john@doe" v-model="email" size="large">
        <a-icon slot="prefix" type="user" />
      </a-input>
      <br />
      <br />
      <a-button type="primary" @click="checkEmail()" :loading="isLoading" block size="large">Begin</a-button>
    </a-card>

    <a-card title="Member" v-if="(step==='memberInfo')">
      <p>We have your details, you may enter your phone number to complete verification.</p>
      <br />
      <br />
      <a-input v-model="name" size="large" disabled>
        <a-icon slot="prefix" type="user" />
      </a-input>
      <br />
      <br />
      <a-input v-model="email" size="large" disabled>
        <a-icon slot="prefix" type="user" />
      </a-input>
      <br />
      <br />
      <a-input v-model="mobile" size="large">
        <a-icon slot="prefix" type="mobile" placeholder="Mobile" />
      </a-input>
      <br />
      <br />IEEE Membership :
      <a-switch defaultChecked v-model="isIEEE" disabled />
      <br />
      <br />CS Membership :
      <a-switch defaultChecked v-model="isCS" disabled />
      <br />
      <br />
      <a-button
        type="primary"
        @click="memberVerify()"
        :loading="isLoading"
        block
        size="large"
      >Continue</a-button>
    </a-card>

    <a-card title="Become a Member" v-if="(step==='nonMemberInfo')">
      <p>Please enter your details to continue.</p>
      <br />
      <br />
      <a-input v-model="name" size="large" placeholder="Name">
        <a-icon slot="prefix" type="user" />
      </a-input>
      <br />
      <br />
      <a-input v-model="email" size="large" disabled>
        <a-icon slot="prefix" type="user" />
      </a-input>
      <br />
      <br />
      <a-input v-model="mobile" size="large" placeholder="Mobile">
        <a-icon slot="prefix" type="mobile" />
      </a-input>
      <br />
      <br />IEEE Membership :
      <a-switch defaultChecked v-model="isIEEE" />
      <br />
      <br />CS Membership :
      <a-switch defaultChecked v-model="isCS" :disabled="!isIEEE" />
      <br />
      <br />
      <a-button
        type="primary"
        @click="nonMemberVerify()"
        :loading="isLoading"
        block
        size="large"
      >Continue</a-button>
    </a-card>

    <a-card title="Set Password" v-if="(step==='setPassword')">
      <p>Please set a password.</p>
      <br />
      <br />
      <a-input placeholder="password" v-model="password" type="password" size="large">
        <a-icon slot="prefix" type="key" />
      </a-input>
      <br />
      <br />
      <a-input placeholder="retype password" v-model="confirmpassword" type="password" size="large">
        <a-icon slot="prefix" type="key" />
      </a-input>
      <br />
      <br />
      <a-button
        type="primary"
        @click="createPassword()"
        :loading="isLoading"
        block
        size="large"
      >Continue</a-button>
    </a-card>

    <a-card title="Success" v-if="(step==='done')">
      <p>Your account has been created. You may login now.</p>
      <br />
      <br />
      <router-link to="/login">
        <a-button size="large" block type="primary">Login</a-button>
      </router-link>
    </a-card>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "SignupView",
  data() {
    return {
      email: "",
      name: "",
      mobile: "",
      confirmMobile: "",
      isIEEE: true,
      isCS: true,
      active: false,
      isLoading: false,
      password: "",
      confirmpassword: "",
      step: "enterEmail"
    };
  },
  methods: {
    checkEmail() {
      // step 1
      let vapp = this;
      vapp.isLoading = true;

      axios
        .get("https://nisb-auth.herokuapp.com/credential?email=" + vapp.email)
        .then(Response => {
          let r = Response.data;
          vapp.isLoading = false;
          if (r.exists === true) {
            vapp.$message.error("Your account already exists.");
          } else {
            // continue
            vapp.isLoading = true;
            axios
              .get(
                "https://nisb-members.herokuapp.com/member?auth=all&email=" +
                  this.email
              )
              .then(Response => {
                let r = Response.data;
                vapp.isLoading = false;
                if ("status" in r) {
                  if (r["status"] === "error") {
                    vapp.active = false;
                    vapp.step = "nonMemberInfo";
                  }
                } else {
                  vapp.name = r["name"];
                  vapp.confirmMobile = r["mobile"];
                  vapp.isIEEE = r["membership"]["isIEEE"];
                  vapp.isIEEE = r["membership"]["isCS"];
                  vapp.active = r["membership"]["active"];
                  vapp.step = "memberInfo";
                }
              });
          }
        });
    },
    memberVerify() {
      // step 2 a
      let vapp = this;
      if (this.mobile === this.confirmMobile) {
        this.step = "setPassword";
      } else {
        vapp.$message.error("Mobile number is incorrect.");
      }
    },
    nonMemberVerify() {
      // step 2 b
      let vapp = this;
      vapp.isLoading = true;
      axios
        .post("https://nisb-members.herokuapp.com/member?auth=all", {
          name: vapp.name,
          email: vapp.email,
          mobile: vapp.mobile,
          membership: {
            isIEEE: vapp.isIEEE,
            isCS: vapp.isCS,
            active: vapp.active
          }
        })
        .then(Response => {
          vapp.isLoading = false;
          if (Response.data.status === "ok") {
            //success
            this.step = "setPassword";
          } else {
            vapp.$message.error("Error while creating account.");
          }
        });
    },
    createPassword() {
      // step 3
      let vapp = this;

      if (vapp.password !== vapp.confirmpassword) {
        vapp.$message.error("Passwords do not match.");
        return;
      }

      vapp.isLoading = true;
      axios
        .post("https://nisb-auth.herokuapp.com/credential", {
          email: vapp.email,
          password: vapp.password
        })
        .then(Response => {
          vapp.isLoading = false;
          if (Response.data.status === "ok") {
            vapp.step = "done";
          }
        });
    }
  }
};
</script>