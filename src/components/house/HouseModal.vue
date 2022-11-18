<template>
  <div id="container">
    <house-search-bar></house-search-bar>
    <b-row>
      <b-col><house-list /></b-col>
      <b-col><house-detail /></b-col>
    </b-row>
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

<style scoped>
#container {
}
</style>
