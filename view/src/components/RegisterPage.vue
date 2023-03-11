<template>
  <form @submit.prevent="register">
    <h2>Sign up</h2>
    <div>
      <label for="first-name">First Name:</label>
      <input
        id="first-name"
        v-model="firstname"
        @input="validateFirstName"
        :class="{ invalid: errors.firstNameError }"
      />
      <span class="error" v-if="errors.firstNameError">{{
        errors.firstNameError
      }}</span>
    </div>
    <div>
      <label for="last-name">Last Name:</label>
      <input
        id="last-name"
        v-model="lastname"
        @input="validateLastName"
        :class="{ invalid: errors.lastNameError }"
      />
      <span class="error" v-if="errors.lastNameError">{{
        errors.lastNameError
      }}</span>
    </div>
    <div>
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
    <button type="submit" :disabled="!isFormValid">Register</button>
  </form>
</template>

<script>
import userService from "../service/userService.js";

export default {
  data() {
    return {
      firstname: "",
      lastname: "",
      email: "",
      password: "",
      confirmedPassword: "",
      errors: {
        firstNameError: "",
        lastNameError: "",
        emailError: "",
        passwordError: "",
        confirmedPasswordError: "",
        passwordLengthError: "",
        passwordSymbolError: "",
        passwordLowercaseLetterError: "",
        passwordUppercaseLetterError: "",
      },
    };
  },
  computed: {
    isFormValid() {
      return (
        this.firstname !== "" &&
        this.lastname !== "" &&
        this.email !== "" &&
        this.errors.passwordLengthError === "" &&
        this.errors.passwordSymbolError === "" &&
        this.errors.passwordLowercaseLetterError === "" &&
        this.errors.passwordUppercaseLetterError === "" &&
        this.confirmedPassword !== "" &&
        this.errors.confirmedPasswordError === ""
      );
    },
  },
  mounted() {
    localStorage.clear();
  },
  methods: {
    async register() {
      const registerRequest = {
        firstname: this.firstname,
        lastname: this.lastname,
        email: this.email,
        password: this.password,
        confirmedPassword: this.confirmedPassword,
      };

      await userService.register(registerRequest);

      this.$router.push("/sendLink");
    },

    validateFirstName() {
      const firstname = this.firstname;

      if (!firstname) {
        this.errors.firstNameError = "First name is required";
      } else {
        this.errors.firstNameError = "";
      }
    },

    validateLastName() {
      const lastname = this.lastname;

      if (!lastname) {
        this.errors.lastNameError = "Last name is required";
      } else {
        this.errors.lastNameError = "";
      }
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

<style>
span {
  display: block;
  margin: 5px 0;
}
.error {
  color: red;
}
</style>
