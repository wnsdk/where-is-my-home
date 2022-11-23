<template>
  <b-container class="bv-example-row mt-3 body-container">
    <qn-a-input-item type="modify" />
  </b-container>
</template>

<script>
import QnAInputItem from "@/components/qna/item/QnAInputItem";

export default {
  name: "QnAModify",
  components: {
    QnAInputItem,
  },
};
</script>

<style scoped>
.body-container {
  width: 1000px;
}
</style>
