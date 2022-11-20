<template>
  <div id="house-menu">
    <ul class="navbar-nav">
      <li class="nav-item">
        <router-link to="/house/search" class="nav-link"
          >지역별 매물</router-link
        >
      </li>
      <li class="nav-item">
        <router-link to="/house/myhouse" class="nav-link"
          >관심 매물</router-link
        >
      </li>
    </ul>
    <ul class="navbar-nav">
      <li class="nav-item">
        <div class="form-check">
          <label class="form-check-label" for="flexCheckDefault">
            주변 공원
          </label>
          <input
            class="form-check-input"
            type="checkbox"
            v-model="isShowPark"
          />
        </div>
      </li>
      <li class="nav-item">
        <div class="form-check">
          <label class="form-check-label" for="flexCheckDefault">
            주변 버스정류장
          </label>
          <input class="form-check-input" type="checkbox" v-model="isShowBus" />
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
import { mapMutations } from "vuex";
export default {
  data() {
    return {
      isShowPark: false,
      isShowBus: false,
    };
  },
  methods: {
    ...mapMutations("houseStore", ["SET_IS_SHOW_PARK", "SET_IS_SHOW_BUS"]),
  },
  watch: {
    isShowPark(value) {
      this.SET_IS_SHOW_PARK(value);
    },
    isShowBus(value) {
      this.SET_IS_SHOW_BUS(value);
    },
  },
};
</script>
<style scoped>
#house-menu {
  display: flex;
}
</style>
