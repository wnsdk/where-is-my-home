<template>
  <div
    id="list-box"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-row class="m-2 title">
      {{ house.apartmentName }}
    </b-row>
    <b-row class="m-2 description">
      {{ house.roadName }} {{ parseInt(house.roadNameBonBun) }} &nbsp; ({{
        house.dong
      }})
    </b-row>
    <b-row class="m-2 description">
      위도 : {{ house.lat | latlng }} &nbsp;&nbsp;&nbsp;&nbsp; 경도 :
      {{ house.lng | latlng }}
    </b-row>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions("houseStore", ["detailHouse"]),
    selectHouse() {
      console.log("집이 선택됨");
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    latlng(value) {
      return parseFloat(value).toFixed(2);
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: rgb(239, 244, 247);
}
#list-box {
  border-bottom: rgb(218, 218, 218) solid 1px;
  padding: 8px;
}
.title {
  font-weight: 700;
}
.description {
  font-size: 13px;
}
</style>
