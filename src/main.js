import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import Argon from "@/plugins/argon-kit";

import "@/api/lib/fontAwesomeIcon.js";
import "@/api/lib/vueBootstrap.js";

Vue.use(Argon);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
