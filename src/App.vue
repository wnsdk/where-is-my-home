<template>
  <div id="app">
    <!-- <a-a></a-a> -->
    <the-header-navbar></the-header-navbar>
    <router-view id="main"></router-view>
  </div>
</template>

<script>
//import AA from "@/views/Components.vue";

import TheHeaderNavbar from "@/components/TheHeaderNavbar";
export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    //AA,
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

#main {
  width: 100%;
  height: 100%;
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
