<template>
  <div>
    <h2>Password Reset</h2>
    <h1>
      Я ЕБУСЬ В ЖОПУ ЗА КОЛБАСУ, ЗА ХЛЕБ И МОЛОКО ХУИ СОСУ, ЗА КВАРТИРНУЮ ПЛАТУ
      ЕМ ГОВНО, ТАРАНИВ СОБОЙ СОЦИАЛЬНОЕ ДНО
    </h1>
    <form @submit.prevent="resetPassword()">
      <div>
        <label for="password">Password:</label>
        <input
          id="password"
          type="password"
          v-model="password"
          @input="validatePassword"
          :class="{ invalid: errors.passwordError }"
        />
        <span
          v-if="errors.passwordLengthError"
          :class="{
            error: errors.passwordLengthError,
          }"
          >{{ errors.passwordLengthError }}</span
        >
        <span
          v-if="errors.passwordSymbolError"
          :class="{
            error: errors.passwordSymbolError,
          }"
          >{{ errors.passwordSymbolError }}</span
        >
        <span
          v-if="errors.passwordLowercaseLetterError"
          :class="{
            error: errors.passwordLowercaseLetterError,
          }"
          >{{ errors.passwordLowercaseLetterError }}</span
        >
        <span
          v-if="errors.passwordUppercaseLetterError"
          :class="{
            error: errors.passwordUppercaseLetterError,
          }"
          >{{ errors.passwordUppercaseLetterError }}</span
        >
      </div>
      <div>
        <label for="confirm-password">Confirm Password:</label>
        <input
          id="confirm-password"
          type="password"
          v-model="confirmedPassword"
          @input="validateConfirmedPassword"
          :class="{ invalid: errors.confirmedPasswordError }"
        />
        <span class="error" v-if="errors.confirmedPasswordError">{{
          errors.confirmedPasswordError
        }}</span>
      </div>
      <div>
        <button type="submit" :disabled="!isFormValid">Reset Password</button>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ResetPasswordPage",
  data() {
    return {
      password: "",
      confirmedPassword: "",
      resetToken: null,
      errors: {
        passwordLengthError: "",
        passwordSymbolError: "",
        passwordLowercaseLetterError: "",
        passwordUppercaseLetterError: "",
        confirmedPasswordError: "",
      },
    };
  },
  mounted() {
    const urlParams = new URLSearchParams(window.location.search);
    this.resetToken = urlParams.get("resetToken").trim();
    console.log(this.resetToken);
  },
  computed: {
    isFormValid() {
      return (
        this.errors.passwordLengthError === "" &&
        this.errors.passwordLengthError === "" &&
        this.errors.passwordLowercaseLetterError === "" &&
        this.errors.passwordUppercaseLetterError === "" &&
        this.confirmedPassword !== "" &&
        this.errors.confirmedPassword !== ""
      );
    },
  },
  methods: {
    async resetPassword() {
      await userService.resetPassword(this.resetToken);

      this.$router.push("/auth/authenticate");

      if (this.newPassword !== this.confirmPassword) {
        alert("Passwords do not match");
        return;
      }
    },

    validatePassword() {
      const password = this.password;

      let lengthError = "";
      let symbolError = "";
      let lowercaseLetterError = "";
      let uppercaseLetterError = "";

      if (!password) {
        lengthError = "Password is required";
      } else {
        if (password.length < 8) {
          lengthError = "Password must be at least 8 characters long";
        } else {
          lengthError = "";
        }

        if (!/[\W_]/.test(password)) {
          symbolError = "Password must contain at least one special character";
        } else {
          symbolError = "";
        }

        if (!/[a-z]/.test(password) || !/[A-Z]/.test(password)) {
          lowercaseLetterError =
            "Password must contain at least one one lowercase letter";
        } else {
          lowercaseLetterError = "";
        }

        if (!/[A-Z]/.test(password)) {
          uppercaseLetterError =
            "Password must contain at least one one uppercase letter";
        } else {
          uppercaseLetterError = "";
        }
      }

      this.errors.passwordLengthError = lengthError;
      this.errors.passwordSymbolError = symbolError;
      this.errors.passwordLowercaseLetterError = lowercaseLetterError;
      this.errors.passwordUppercaseLetterError = uppercaseLetterError;
    },

    validateConfirmedPassword() {
      const password = this.password;
      const confirmedPassword = this.confirmedPassword;

      if (!confirmedPassword) {
        this.errors.confirmedPasswordError = "Confirm password is required";
      } else if (password !== confirmedPassword) {
        this.errors.confirmedPasswordError = "Passwords do not match";
      } else {
        this.errors.confirmedPasswordError = "";
      }
    },
  },
};
</script>

<style scoped>
span {
  display: block;
  margin: 5px 0;
}
.error {
  color: red;
}
</style>
