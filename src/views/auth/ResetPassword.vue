<template>
  <div class="resetpassword container">

    <h1>Reset Password</h1>

    <a-card title="Request Token" v-if="(step==='requestToken')">
        <a-input placeholder="john@doe" v-model="email" size="large">
           <a-icon slot="prefix" type="user" />
        </a-input> <br><br>
        <a-button type="primary" @click="requestToken()" :loading="isLoading" block size="large">Request Token</a-button>
    </a-card>

    <a-card title="Reset" v-if="(step==='resetPassword')">
        <p>We have sent a token on your registered email. Please remember to check the spam folder.</p><br><br>
        <a-input placeholder="Token" v-model="token" size="large">
           <a-icon slot="prefix" type="key" />
        </a-input> <br><br>
        <a-input placeholder="password" v-model="password" type="password" size="large">
            <a-icon slot="prefix" type="key" />
        </a-input> <br><br>
        <a-button type="primary" @click="resetPassword()" :loading="isLoading" block size="large">Reset Password</a-button>
    </a-card>

    <a-card title="Success" v-if="(step==='done')">
        <p>Password Reset was successful. You may login now.</p><br><br>
        <router-link to="/login">
            <a-button size="large" block type="primary">Login</a-button>
        </router-link>
    </a-card>


    <br />
   

    
  </div>
</template>
<script>
import axios from "axios"

export default {
  name: "ResetPasswordView",
  data() {
    return {
        email:"",
        token:"",
        password:"",
        step:"requestToken",
        isLoading:false
    };
  },
  methods: {
    requestToken(){
        let vapp = this
        vapp.isLoading = true
        axios
        .get("https://nisb-auth.herokuapp.com/resetpassword?email="+this.email)
        .then(Response => {
            vapp.isLoading = false
            if (Response.data.status == "ok"){
                this.step = "resetPassword"
            }else{
                //some error occurred
            }
        })
    },
    resetPassword(){
        let vapp = this
        vapp.isLoading = true
        axios
        .post("https://nisb-auth.herokuapp.com/resetpassword",{
            email: this.email,
            password: this.password,
            token: this.token
        })
        .then(Response => {
            vapp.isLoading = false
            if (Response.data.status == "ok"){
                this.step = "done"
            }else{
                //invalid token
            }
        })
    }
  }
}
</script>

<style scoped>

</style>