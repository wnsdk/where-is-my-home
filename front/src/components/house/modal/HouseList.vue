<template>
  <div
    id="house-list"
    v-if="houses && houses.length != 0"
    class="bv-example-row mt-3"
  >
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </div>
  <div v-else class="bv-example-row mt-3">
    <b-row>
      <b-col style="display: absolute; z-index: -1"
        >주택 목록이 없습니다.</b-col
      >
    </b-row>
  </div>
</template>

<script>
import HouseListItem from "@/components/house/modal/HouseListItem";
import { mapState } from "vuex";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState("houseStore", ["houses"]),
  },
};
</script>

<style>
#house-list {
  height: 78vh;
  overflow: auto;
}
</style>
