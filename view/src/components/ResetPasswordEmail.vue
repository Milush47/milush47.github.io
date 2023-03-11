<template>
<div>
    <h2>Enter email address to send you link for password reset</h2>
</div>
<form @submit.prevent="submitEmail">
    <div>
        <label for="email">Email:</label>
        <input
            id="email"
            type="email"
            v-model="email"
            @input="validateEmail"
            :class="{ invalid: errors.emailError }"
        />
        <span class="error" v-if="errors.emailError">{{ errors.emailError }}</span>
    </div>
    <div>
        <button type="submit" :disabled="!isFormValid">Submit email</button>
    </div>
</form>
</template>

<script>
import userService from "../service/userService"

export default {
    data() {
        return {
            email: "",
            errors: {
                emailError: "",
            },
        };
    },
    methods: {
        validateEmail() {
            if (this.email.length === 0) {
                this.errors.emailError = "Email is required";
            } else if (!this.email.includes("@")) {
                this.errors.emailError = "Email must contain @";
            } else {
                this.errors.emailError = "";
            }
        },
        submitEmail() {
            this.validateEmail();
            userService.provideEmail(this.email);
        },
    },
    computed: {
        isFormValid() {
            return this.errors.emailError === "";
        },
    },
}

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