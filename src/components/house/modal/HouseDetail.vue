<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row class="mb-2 mt-1">
      <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col>
    </b-row>
    <b-row><button @click="addMyhouse">찜하기</button></b-row>
    <b-row> 일련번호 : {{ house.aptCode }} </b-row>
    <b-row> 아파트 이름 : {{ house.apartmentName }} </b-row>
    <b-row> 법정동 : {{ house.dong }} </b-row>
    <b-row>
      거래내역
      <house-chart :houseDeal="houseDeal" ref="chart"></house-chart>
    </b-row>
    <!-- <b-row>
      거래금액 :
      {{ (parseInt(houseDeal.dealAmount.replace(",", "")) * 10000) | price }}원
    </b-row> -->
  </b-container>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http";
import HouseChart from "./HouseChart.vue";

export default {
  name: "HouseDetail",
  components: {
    HouseChart,
  },
  computed: {
    ...mapState(["house", "houseDeal"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    addMyhouse() {
      //let userId = "ssafy"; // 로그인 토큰 적용!!!!
      //http.post(`/${userId}/${this.house.}/${}`);
    },
    deleteMyhouse() {
      http.delete();
    },
  },
};
</script>

<style></style>
