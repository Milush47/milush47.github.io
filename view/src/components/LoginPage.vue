<template>
  <form @submit.prevent="submit">
    <div>
      <h2>Sign in</h2>
      <!-- <p v-if="!isLoggedIn">Please login to access your profile.</p> -->
      <label for="email">Email:</label>
      <input
        id="email"
        type="email"
        v-model="email"
        @input="validateEmail"
        :class="{ invalid: errors.emailError }"
      />
      <span class="error" v-if="errors.emailError">{{
        errors.emailError
      }}</span>
    </div>
    <div>
      <label for="password">Password:</label>
      <input
        id="password"
        type="password"
        v-model="password"
        @input="validatePassword"
        :class="{ invalid: errors.passwordError }"
      />
      <span class="error" v-if="errors.passwordError">{{
        errors.passwordError
      }}</span>
    </div>
    <button type="submit" :disabled="!isFormValid">
      Sign in
    </button>
    <p>Forgot your password?</p><RouterLink to="/auth/provideEmail">Try this</RouterLink>
  </form>
</template>

<script>
import userService from "../service/userService.js";
import axios from "axios";

export default {
  data() {
    return {
      email: "",
      password: "",
      errors: {
        emailError: "",
        passwordError: "",
      },
      // isLoggedIn: false,
    };
  },
  computed: {
    isFormValid() {
      return (
        this.email !== "" &&
        this.password !== ""
      );
    }
  },
  methods: {
    submit() {
      const request = {
        email: this.email,
        password: this.password,
      };
      userService
        .login(request)
        .then((response) => {
          // this.isLoggedIn = true;
          // isLoggedIn ? this.$router.push('/profile') : this.$router.push('/auth/authenticate');

        })
        .catch((error) => {
          if (error.response && error.response.status === 409) {
            this.emailError = error.message;
          } else {
            this.loginError =
              "Sign in failed. Please try again later.";
          }
        });

        this.$router.push("/profile");
    },

    validateEmail() {
      const email = this.email;
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      if (!email) {
        this.errors.emailError = "Email is required";
      } else if (!emailRegex.test(email)) {
        this.errors.emailError = "Invalid email format";
      } else {
        this.errors.emailError = "";
      }
    },

    validatePassword() {
      const password = this.password;

      if (!password) {
        this.errors.passwordError = "Password is required";
      } else if (password.length < 8) {
        this.errors.passwordError = "Password must be at least 8 characters long";
      } else {
        this.errors.passwordError = "";
      }
    },
  },
};
</script>

<style>
span {
  display: block;
  margin: 5px 0;
}
.error {
  color: red;
}
</style>
