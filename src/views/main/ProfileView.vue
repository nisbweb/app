<template>
  <div class="container">
    <h1 class="title">PROFILE</h1>
    <b-button @click="performLogout">Logout</b-button>
    <p v-if="isGuest">You are logged in as Guest.</p>
    <div v-else>
      <br><br>
      Name : {{member.name}} <br>
      Email : {{member.email}} <br>
      Mobile : {{member.mobile}} <br>
    </div>
    <b-loading :is-full-page="true" :active.sync="isLoading" :can-cancel="false"></b-loading>

  </div>
</template>

<script>
import axios from "axios"

export default {
  name: 'ProfileView',
  data(){
    return{
      isGuest:false,
      isLoading:false,
      member:{}
    }
  },
 methods:{
   loadMember(){
     var m = localStorage.getItem("email")
     var t = localStorage.getItem("token")
     this.isLoading=true
     axios.get("https://nisb-members.herokuapp.com/member?email="+ m +"&auth=" + t).then(Response=>{
       this.member = Response.data
       this.isLoading=false
     })
   },
   performLogout(){
     var t = localStorage.getItem("token")
     localStorage.removeItem("guest")
     localStorage.removeItem("email")
     localStorage.removeItem("token")
     axios.delete("https://nisb-auth.herokuapp.com/auth?auth="+t).then(function () {
      this.$router.replace("default")
     })
     
   }
 },
 mounted(){
   if (localStorage.getItem("guest")=="true"){
     this.isGuest=true
   }else{
     this.isGuest=false
    this.loadMember()
   }
 }
}
</script>

<style>
.hidden{
  display: none;
}
</style>