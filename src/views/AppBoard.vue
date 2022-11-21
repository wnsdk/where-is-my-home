<template>
  <div>
    <b-row>
      <b-col>
        <h3>자유게시판</h3>
      </b-col>
    </b-row>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "AppBoard",
  methods: {
    ...mapMutations(["SET_FONT_COLOR"]),
  },
  created() {
    this.SET_FONT_COLOR("#2E2E2E");
  },
};
</script>

<style></style>
