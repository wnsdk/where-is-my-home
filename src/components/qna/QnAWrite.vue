<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>
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
};
</script>

<style></style>
