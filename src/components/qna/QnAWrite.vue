<template>
  <b-container class="bv-example-row mt-3">
    <qn-a-input-item type="register" />
  </b-container>
</template>

<script>
import QnAInputItem from "@/components/qna/item/QnAInputItem";

export default {
  name: "QnAWrite",
  components: {
    QnAInputItem,
  },
  data() {
    return {
      editorKey: process.env.VUE_APP_EDITOR_KEY,
    };
  },
};
</script>

<style></style>
