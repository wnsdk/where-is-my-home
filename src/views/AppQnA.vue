<template>
  <div>
    <b-row>
      <b-col>
        <h3>Q&A</h3>
      </b-col>
    </b-row>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "AppQnA",
  methods: {
    ...mapMutations(["SET_FONT_COLOR"]),
  },
  created() {
    this.SET_FONT_COLOR("#2E2E2E");
  },
};
</script>

<style></style>
