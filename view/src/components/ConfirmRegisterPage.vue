<template>
  <p>
    You have successfully confirmed email. Now you can login to your account.
  </p>
  <button type="submit" @click="verifyEmail()">Confirm email</button>
</template>

<script>
import userService from "../service/userService.js";

export default {
    name: "ConfirmRegister",
    data() {
      return {
        verificationToken: null
      }
    },
    mounted() {
      const urlParams = new URLSearchParams(window.location.search);
      this.verificationToken = urlParams.get('verificationToken').trim();
      console.log(this.verificationToken);

    },
    methods: {
      async verifyEmail() {
        await userService.verifyEmail(this.verificationToken);

        this.$router.push("/auth/authenticate");
      }
    }
};
</script>

<style>
p {
  text-align: center;
}
</style>
