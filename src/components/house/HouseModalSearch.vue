<template>
  <div id="house-modal">
    <house-search-bar></house-search-bar>
    <b-row><house-detail /></b-row>
    <b-row><house-list /></b-row>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/modal/HouseSearchBar.vue";
import HouseList from "@/components/house/modal/HouseList.vue";
import HouseDetail from "@/components/house/modal/HouseDetail.vue";

export default {
  name: "HouseModal",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
  },
};
</script>

<style scoped></style>
