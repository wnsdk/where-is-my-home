<template>
  <div>
    <house-navigation></house-navigation>
    <house-modal></house-modal>
    <house-map></house-map>
  </div>
</template>
<script>
import HouseMap from "@/components/house/HouseMap.vue";
import HouseNavigation from "@/components/house/navigation/HouseNavigation.vue";
import HouseModal from "@/components/house/HouseModal.vue";

export default {
  name: "AppHouse",
  components: {
    HouseMap,
    HouseNavigation,
    HouseModal,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
#houseModal {
  width: 380px;
  height: 100%;
  background-color: white;
  position: absolute;
  z-index: 3;
}
#map {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: -1;
}
</style>
