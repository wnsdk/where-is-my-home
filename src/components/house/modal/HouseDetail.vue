<template>
  <div v-if="house" id="house-detail-container">
    <img id="house-img" :src="require('@/assets/apt.png')" />
    <h4 id="house-detail-name">{{ house.apartmentName }}</h4>
    <font-awesome-icon
      :icon="iconHeart"
      id="add-my-house"
      @click="clickMyhouse"
    />

    <table id="house-detail-table">
      <tr>
        <th>일련번호</th>
        <td>{{ house.aptCode }}</td>
      </tr>
      <tr>
        <th>준공년도</th>
        <td>{{ house.buildYear }}년</td>
      </tr>
      <tr>
        <th>도로명주소</th>
        <td>{{ house.roadName }}</td>
      </tr>
      <tr>
        <th>법정동</th>
        <td>{{ house.dong }}</td>
      </tr>
      <tr>
        <th>거래건수</th>
        <td>{{ houseDeal.length }}회</td>
      </tr>
    </table>
    <house-chart id="house-chart"></house-chart>

    <!-- <b-row>
      거래금액 :
      {{ (parseInt(houseDeal.dealAmount.replace(",", "")) * 10000) | price }}원
    </b-row> -->
  </div>
</template>

<script>
import { mapState } from "vuex";
//import http from "@/api/http";
import HouseChart from "./HouseChart.vue";

export default {
  name: "HouseDetail",
  components: {
    HouseChart,
  },
  data() {
    return {
      iconHeart: "fa-regular fa-heart",
    };
  },
  computed: {
    ...mapState("houseStore", ["house", "houseDeal"]),
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  created() {
    console.log("디테일 창이 띄어짐");
    console.log(this.houseDeal);
    // 로그인 되어 있다면
    // 해당 매물이 찜하기 되어있는지 확인한 후
    // this.iconHeart = "fa-solid fa-heart"; 해주기
  },
  methods: {
    clickMyhouse() {
      if (this.iconHeart == "fa-regular fa-heart") this.addMyhouse();
      else this.deleteMyhouse();
    },
    addMyhouse() {
      // 로그인 토큰 적용!!!!
      //http.post(`/${userId}/${this.house.}/${}`);
      this.iconHeart = "fa-solid fa-heart";
    },
    deleteMyhouse() {
      this.iconHeart = "fa-regular fa-heart";
      //http.delete();
    },
  },
};
</script>

<style scoped>
#house-detail-container {
  height: 88vh;
  /* background-color: red; */
  display: flex;
  flex-direction: column;
  /* justify-content: center;
  flex: auto;
  flex-wrap: wrap; */
}
#house-detail-name {
  width: 100%;
}
#add-my-house {
  width: 40px;
  height: 40px;
  color: rgb(233, 132, 141);
}
#house-detail-table {
  width: 100%;
  height: 20px;
}
#house-img {
  width: 100%;
  height: 200px;
  object-fit: cover;
}
#house-chart {
  /* display: flex;
  justify-content: center;
  align-items: center; */
  position: absolute;
  bottom: 40px;
  width: 341px;
  height: 30vh;
  /* background-color: aqua; */
}
</style>
