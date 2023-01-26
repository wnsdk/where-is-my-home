<template>
  <div>
    <b-row>
      <b-col>
        <h3>Q&A</h3>
      </b-col>
    </b-row>
    <router-view></router-view>
    <argon-app-footer></argon-app-footer>
  </div>
</template>

<script>
import { mapMutations } from "vuex";
import ArgonAppFooter from "@/layout/ArgonAppFooter";

export default {
  name: "AppQnA",
  components: {
    ArgonAppFooter,
  },
  methods: {
    ...mapMutations(["SET_FONT_COLOR"]),
  },
  created() {
    this.SET_FONT_COLOR("#2E2E2E");
  },
};
</script>

<style scoped>
h3 {
  margin: 70px 0;
}
</style>
