<template>
  <b-container class="bv-example-row mt-3">
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
  data() {
    return {
      editorKey: process.env.VUE_APP_EDITOR_KEY,
    };
  },
};
</script>

<style></style>
