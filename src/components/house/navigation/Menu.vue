<template>
  <div style="width: 100%" class="d-flex flex-row justify-content-between">
    <div id="nav1" class="d-flex">
      <router-link to="/house/search" class="nav1-item"
        >지역별 매물</router-link
      >
      <router-link to="/house/myhouse" class="nav1-item">관심 매물</router-link>
    </div>
    <div id="nav2" class="d-flex justify-content-end">
      <div class="nav2-item d-flex">
        <input class="form-check-input" type="checkbox" v-model="isShowPark" />
        <label class="form-check-label" for="flexCheckDefault">
          주변 공원
        </label>
        <input type="range" v-model="sliders.park" min="0" max="5" step="0.5" />
      </div>
      <div class="nav2-item d-flex">
        <input class="form-check-input" type="checkbox" v-model="isShowBus" />
        <label class="form-check-label" for="flexCheckDefault">
          버스정류장
        </label>
        <input type="range" v-model="sliders.bus" min="0" max="5" step="0.5" />
      </div>
    </div>
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
* {
  color: rgb(226, 226, 226);
  text-decoration: none;
  align-items: center;
}
#nav1 {
  width: 300px;
  margin-left: 30px;
}
#nav2 {
  width: 500px;
}
.nav1-item {
  padding-left: 50px;
}
.nav2-item {
  width: 200px;
  margin-left: 50px;
}
.nav2-item > *:nth-child(3) {
  margin-left: 5px;
}
.nav2-item > *:nth-child(2) {
  margin-left: 5px;
}
.nav2-item > *:nth-child(1) {
  width: 20px;
  height: 20px;
}
.nav2-item > *:nth-child(1):checked {
  background-color: rgb(71, 184, 122);
}
input[type="range"] {
  overflow: hidden;
  -webkit-appearance: none;
  height: 10px;
  width: 100px;
  background: white;
  border-radius: 10px;
}

input[type="range"]::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 12px;
  height: 12px;
  background: rgb(66, 66, 66);
  box-shadow: 1px 1px 7px rgb(71, 184, 122);
  box-shadow: -100vw 0 0 100vw rgb(71, 184, 122);
  border-radius: 10px;
  outline: 0;
  border: 0;
}
</style>
