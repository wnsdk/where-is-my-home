<template>
  <section class="section-hero section-shaped my-0">
    <div class="row justify-content-center">
      <div class="col-lg-7 text-center pt-lg align-items-center">
        <section id="house-search-bar">
          <b-form-select
            class="lead mt-4 m-5"
            style="border-radius: 5px 5px 5px 5px"
            v-model="sidoCode"
            :options="sidos"
            @change="gugunList"
          />
          <b-form-select
            class="lead mt-4 m-5"
            style="border-radius: 5px 5px 5px 5px"
            v-model="gugunCode"
            :options="guguns"
          />
          <base-button
            id="search-button"
            class="btn-dark rounded-right lead mt-4 mb-5"
            @click="searchApt"
          >
            검색
          </base-button>
        </section>
      </div>
    </div>
  </section>
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
