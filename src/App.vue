<template>
  <div id="app">
    
    <the-header-navbar></the-header-navbar>
    <main>
      <fade-transition origin="center" mode="out-in" :duration="250">
        <router-view />
      </fade-transition>
    </main>
  </div>
</template>

<script>
import { FadeTransition } from "vue2-transitions";
//import Login from "@/components/user/Login.vue";
import TheHeaderNavbar from "@/components/TheHeaderNavbar";
export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    FadeTransition,
    //Login,
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
