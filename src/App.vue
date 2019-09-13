<template>
  <div id="app" @contextmenu="preventContextMenu($event)">
    <router-view />
    <nav-bar v-if="isLoggedIn()"></nav-bar>
  </div>
</template>

<script>
import NavBar from "./components/NavBar.vue"
export default {
  components:{
    NavBar
  },
  name: "app",
  methods:{
    isLoggedIn(){
      
      if (localStorage.getItem("guest") !== null || localStorage.getItem("email")!== null){
        return true
      }
      return false
    },
    preventContextMenu(e){
      e.preventDefault()
    }
  },
  mounted() {
    if (localStorage.getItem("guest") === null) {
      if (localStorage.getItem("email") === null) {
        // router.replace("default");
        this.$router.replace("default")
      } else {// if logged in as user
        this.$router.replace("home")
      }
    } else {// is logged in as guest
      this.$router.replace("home")
    }
  }
};
</script>>

<style>
.container {
  padding: 60px 20px;
}

.title {
  padding: 20px 10px;
}

.one-four {
  width: 25%;
  border: 0;
  font-size: 1.3em;
  border-radius: 0;
}
.one-four:hover {
  border: 0;
}
.one-four:active {
  border: 0;
}
.fixed-bottom {
  position: fixed;
  bottom: 0;
  width: 100%;
  left: 0;
  padding: 0;
}
.width-80{
    width :80%;
}
.width-100{
  width:100%;
}

/* 
//Dark Mode
html,body{height:100%; display: block; overflow: scroll;}

html, body,.box{
  color:white!important;
  background:black;
}
.box{
  background: #222!important;
} 
.navbar-button{
  background: #333!important;
}
a, .fas{
  color:white!important;
}
.title, .subtitle{color:white!important;} */
</style>
