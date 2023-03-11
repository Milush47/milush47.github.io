<template>
  <Header/>
  <RouterView/>
  <Footer/>
</template>

<script>
import { RouterView } from 'vue-router'
import Footer from './components/view/Footer.vue'
import Header from './components/view/Header.vue';
import Error from './components/errors/UnauthorizedUser.vue';
import axios from 'axios';

export default {
  name: "App",
  components: {
    Header, 
    Footer
  }

}
</script>

