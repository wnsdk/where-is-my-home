<template>
  <div id="house-modal">
    <b-row><house-detail /></b-row>
    <b-row><house-list /></b-row>
  </div>
</template>

<script>
import HouseList from "@/components/house/modal/MyhouseList.vue";
import HouseDetail from "@/components/house/modal/HouseDetail.vue";

export default {
  name: "HouseModal",
  components: {
    HouseList,
    HouseDetail,
  },
};
</script>

<style scoped></style>
