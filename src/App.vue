<template>
  <div id="app" @contextmenu="preventContextMenu($event)">
    <a-layout>
      <a-layout-content style="background:#fff;">
        <router-view />
      </a-layout-content>
      <a-layout-footer v-if="isLoggedIn()" class="bottomFixed" style="padding:0;">
        <a-row>
          <a-col :span="6">
            <router-link to="/">
              <a-button icon="home" block  type="primary" size="large" class="navBTN"/>
            </router-link>
          </a-col>
          <a-col :span="6">
            <router-link to="/events">
              <a-button icon="calendar" block  type="primary" size="large" class="navBTN"/>
            </router-link>
          </a-col>
          <a-col :span="6">
            <router-link to="/notices">
              <a-button icon="bell" block type="primary" size="large" class="navBTN"/>
            </router-link>
          </a-col>
          <a-col :span="6">
            <router-link to="/profile">
              <a-button icon="user" block  type="primary" size="large" class="navBTN"/>
            </router-link>
          </a-col>
        </a-row>
      </a-layout-footer>
    </a-layout>
  </div>
</template>


<script>
import session from "./session"
export default {
  name: "app",
  methods:{
    preventContextMenu(e){
      e.preventDefault()
    },
    isLoggedIn(){
      return session.isLoggedIn()
    }
  },
  mounted() {
    if (session.isLoggedIn() == false){
      this.$router.replace("default")
    }
  }
}
</script>

<style>
.bottomFixed {
  position: fixed;
  bottom: 0;
  left: 0;
  width:100%;
  height: 60px;
}
.navBTN{
  height:60px!important;
  border-radius: 0!important;
}
.container{
  padding:20px;
  margin-top:30px;
  margin-bottom:  60px;
}
h1{
  margin-top:30px;
}
</style>
