<template>
  <div><router-view></router-view></div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "AppNews",
  methods: {
    ...mapMutations(["SET_FONT_COLOR"]),
  },
  created() {
    this.SET_FONT_COLOR("#2E2E2E");
  },
};
</script>

<style></style>
