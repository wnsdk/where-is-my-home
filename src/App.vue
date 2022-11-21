<template>
  <div id="app">
    <argon-app-header></argon-app-header>
    <router-view id="main"></router-view>
  </div>
</template>

<script>
import ArgonAppHeader from "./layout/ArgonAppHeader";

export default {
  name: "App",
  components: {
    ArgonAppHeader,
  },
};
</script>

<style>
* {
  font-family: "Noto Sans KR", sans-serif;
}

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
