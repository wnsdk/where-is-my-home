<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <house-list-item
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
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
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
  methods: {
    ...mapActions("houseStore", ["getMyhouse"]),
  },
  created() {
    //////////////////////////////////////////////////
    // 로그인 유저의 아이디를 받아와야됨
    this.getMyhouse("ssafy");
  },
};
</script>

<style></style>
