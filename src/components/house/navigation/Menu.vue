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
          <base-slider v-model="sliders.park" :range="{ min: 0, max: 5 }">
          </base-slider>
        </div>
      </li>
      <li class="nav-item">
        <div class="form-check">
          <label class="form-check-label" for="flexCheckDefault">
            주변 버스정류장
          </label>
          <input class="form-check-input" type="checkbox" v-model="isShowBus" />
          <base-slider v-model="sliders.bus" :range="{ min: 0, max: 5 }">
          </base-slider>
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
      sliders: {
        park: 2.5,
        bus: 2.5,
      },
    };
  },
  methods: {
    ...mapMutations("houseStore", [
      "SET_IS_SHOW_PARK",
      "SET_IS_SHOW_BUS",
      "SET_DIST_SLIDER",
    ]),
  },
  watch: {
    isShowPark(value) {
      this.SET_IS_SHOW_PARK(value);
    },
    isShowBus(value) {
      this.SET_IS_SHOW_BUS(value);
    },
  },
  updated() {
    this.SET_DIST_SLIDER(this.sliders);
  },
};
</script>
<style scoped>
#house-menu {
  display: flex;
}
</style>
