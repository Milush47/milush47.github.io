<template>
  <div v-if="error" class="error-message">{{ error }}</div>
</template>

<script>
export default {
  data() {
    return {
      error: "",
    };
  },
  methods: {
    clearError() {
      this.error = "";
    },

    getError() {
        return this.error = "You are not authorized to view this page";
    }
  },
};
</script>
