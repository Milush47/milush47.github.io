import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import "./assets/main.css";
import setAuthHeader from "./service/setAuthHeader";
import handleUnauthorizedError from "./handleErrors/handleUnauthorizedError";

import UnauthorizedError from "./components/errors/UnauthorizedUser.vue";

if(localStorage.token) {
    setAuthHeader(localStorage.token);
} else {
    setAuthHeader(null);
}

handleUnauthorizedError();

const app = createApp(App);

app.component('unauthorized-error', UnauthorizedError);

app.use(router);

app.mount("#app");
