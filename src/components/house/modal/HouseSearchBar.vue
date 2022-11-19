<template>
  <b-row id="house-search-bar">
    <!-- <b-col class="sm-3">
      <b-form-input
        v-model.trim="dongCode"
        placeholder="동코드 입력...(예 : 11110)"
        @keypress.enter="sendKeyword"
      ></b-form-input>
    </b-col>
    <b-col class="sm-3" align="left">
      <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
    </b-col> -->
    <b-form-select
      class="form-select"
      style="border-radius: 5px 0 0 5px"
      v-model="sidoCode"
      :options="sidos"
      @change="gugunList"
    />
    <b-form-select
      class="form-select"
      style="border-radius: 0 0 0 0"
      v-model="gugunCode"
      :options="guguns"
    />

    <button
      id="search-button"
      class="btn-dark rounded-right"
      @click="searchApt"
    >
      검색
    </button>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "houses"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_APT_LIST();
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
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchApt() {
      if (this.gugunCode) {
        this.getHouseList(this.gugunCode);
        this.CLEAR_DETAIL_HOUSE();
      }
    },
  },
};
</script>

<style scoped>
.form-select {
  width: 41%;
  height: 40px;
}
#search-button {
  width: 17%;
  height: 40px;
}
#house-search-bar {
  display: flex;
  height: 8vh;
}
</style>
