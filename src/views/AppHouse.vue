<template>
  <div>
    <house-navigation></house-navigation>
    <router-view></router-view>
    <house-map></house-map>
  </div>
</template>
<script>
import HouseMap from "@/components/house/HouseMap.vue";
import HouseNavigation from "@/components/house/navigation/HouseNavigation.vue";

export default {
  name: "AppHouse",
  components: {
    HouseMap,
    HouseNavigation,
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
#house-navbar {
  height: 5vh;
  margin-top: 0;
}
#map {
  position: absolute;
  width: 85%;
  height: 88vh;
  z-index: -1;
  right: 0;
}
</style>
