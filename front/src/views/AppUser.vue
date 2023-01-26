<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

export default {
  name: "AppUser",
  methods: {
    ...mapMutations(["SET_FONT_COLOR"]),
  },
  created() {
    this.SET_FONT_COLOR("#D8D8D8");
  },
};
</script>

<style scoped>
#login-page,
#register-page {
  height: 93vh;
}
</style>
