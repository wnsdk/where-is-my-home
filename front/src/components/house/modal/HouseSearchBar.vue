<template>
  <div id="house-search-bar">
    <select-box :items="sidos" name="sido" @gugunList="gugunList"></select-box>
    <select-box
      :items="guguns"
      name="gugun"
      @searchApt="searchApt"
    ></select-box>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import SelectBox from "./SelectBox.vue";
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  components: {
    SelectBox,
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "houses"]),
  },
  created() {
    // this.CLEAR_SIDO_LIST();
    // this.CLEAR_GUGUN_LIST();
    // this.CLEAR_APT_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_APT_LIST",
      "CLEAR_DETAIL_HOUSE",
    ]),
    gugunList(sidoCode) {
      this.sidoCode = sidoCode;
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt(gugunCode) {
      this.gugunCode = gugunCode;
      if (this.gugunCode) {
        this.getHouseList(this.gugunCode);
        this.CLEAR_DETAIL_HOUSE();
      }
    },
  },
};
</script>

<style scoped>
#search-button {
  width: 17%;
  height: 40px;
}
#house-search-bar {
  display: flex;
  height: 8vh;
}
</style>
