<template>
  <div
    id="my-house-list"
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
      <b-col>주택 목록이 없습니다.</b-col>
    </b-row>
  </div>
</template>

<script>
import HouseListItem from "@/components/house/modal/HouseListItem";
import { mapState, mapActions } from "vuex";

export default {
  name: "MyhouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState("houseStore", ["houses"]),
    ...mapState("memberStore", ["userInfo"]),
  },
  methods: {
    ...mapActions("houseStore", ["getMyhouse"]),
  },
  created() {
    this.getMyhouse(this.userInfo.userId);
  },
};
</script>

<style scoped>
#my-house-list {
  height: 78vh;
  overflow: scroll;
}
</style>
