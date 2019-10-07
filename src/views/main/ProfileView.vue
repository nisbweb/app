<template>
  <div class="container">
    <h1 class="title">PROFILE</h1>

    <a-button-group>
      <a-button 
        type="primary" 
        style="font-size:1.5em;" 
        size="large"
        @click="showQR()">
          <a-icon type="qrcode" />
      </a-button>
      <a-button
        type="primary"
        style="font-size:1.5em;"
        size="large"
        @click="openSettings()"
        icon="sliders"
      ></a-button>
    </a-button-group>
    <br><br>

    <a-card hoverable :loading="isLoading">
      <template class="ant-card-actions" slot="actions">
        <a-icon type="setting" />
        <a-icon type="edit" />
        <a-icon type="logout" @click="performLogout"/>
      </template>
      <a-card-meta v-if="session.isGuest()" title="Guest User"></a-card-meta>
      <a-card-meta :title="member.name" :description="member.email" v-else>
      </a-card-meta>
    </a-card>


    <a-drawer
      title="Settings"
      placement="right"
      :closable="false"
      @close="closeSettings()"
      :visible="settingsVisible">
      <p>Some contents...</p>
      <p>Some contents...</p>
    </a-drawer>
  </div>
</template>

<script>
import axios from "axios";
import session from "./../../session"

export default {
  name: "ProfileView",
  data() {
    return {
      isLoading: false,
      member: {},
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
    showQR(){
      this.$success({
        title: 'QR Code for your profile',
        content: (  // JSX support
          <div>
            <img src={"https://api.qrserver.com/v1/create-qr-code/?size=150x150&data="+localStorage.getItem("email")}/>
          </div>
        ),
      });
    },
    loadMember() {
      var m = localStorage.getItem("email");
      var t = localStorage.getItem("token");
      this.isLoading = true;
      axios
        .get(
          "https://nisb-members.herokuapp.com/member?email=" + m + "&auth=" + t
        )
        .then(Response => {
          this.member = Response.data;
          this.isLoading = false;
        });
    },
    performLogout() {
      let vapp = this;
      var t = session.getToken()
      session.destroySession()
      this.isLoading=true
      axios
        .delete("https://nisb-auth.herokuapp.com/auth?auth=" + t)
        .then(function() {
          vapp.isLoading = false
          vapp.$router.replace("default");
        });
    }
  },
  mounted() {
    if (session.isGuest === false){
      this.loadMember()
    }
  }
};
</script>

<style>
.hidden {
  display: none;
}
</style>