<template>
  <div v-if="house" id="house-detail-container">
    <img id="house-img" :src="require('@/assets/apt.png')" />
    <h4 id="house-detail-name">{{ house.apartmentName }}</h4>

    <div id="icons">
      <font-awesome-icon
        :icon="iconHeart"
        id="click-my-house"
        class="house-icon"
        @click="clickMyhouse"
      />
      <img
        src="@/assets/icon-share.svg"
        class="house-icon"
        @click="shareKakao"
      />
    </div>

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
        <td>{{ house.roadName }} {{ this.house.roadNameBonBun }}</td>
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
import http from "@/api/http";
import HouseChart from "./HouseChart.vue";
import { checkAuthUser } from "@/api/member";

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
    ...mapState("memberStore", ["userInfo"]),
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

    this.fillHeart();
  },
  watch: {
    house() {
      this.fillHeart();
    },
  },
  methods: {
    fillHeart() {
      checkAuthUser().then((response) => {
        if (response) {
          http
            .get(`myhouse/${this.userInfo.userId}/${this.house.aptCode}`)
            .then(({ data }) => {
              if (data > 0) this.iconHeart = "fa-solid fa-heart";
              else this.iconHeart = "fa-regular fa-heart";
            });
        }
      });
    },
    clickMyhouse() {
      if (this.iconHeart == "fa-regular fa-heart") this.addMyhouse();
      else this.deleteMyhouse();
    },
    addMyhouse() {
      checkAuthUser().then((response) => {
        if (!response) {
          alert("로그인이 필요한 서비스입니다.");
        } else {
          http.post(`myhouse/${this.userInfo.userId}/${this.house.aptCode}`);
          // .then((response) => {
          //   console.log(response);
          // });
          this.iconHeart = "fa-solid fa-heart";
        }
      });
    },
    deleteMyhouse() {
      checkAuthUser().then((response) => {
        if (!response) {
          alert("로그인이 필요한 서비스입니다.");
        } else {
          http.delete(`myhouse/${this.userInfo.userId}/${this.house.aptCode}`);
          this.iconHeart = "fa-regular fa-heart";
        }
      });
    },
    shareKakao() {
      console.log(this.house);

      window.Kakao.Share.sendDefault({
        objectType: "location",
        content: {
          title: this.house.apartmentName,
          description: `일련번호 : ${this.house.aptCode}\n주소 : ${this.house.roadName} ${this.house.roadNameBonBun}`,
          imageUrl:
            "https://img.etoday.co.kr/pto_db/2022/03/600/20220302133228_1723814_1181_787.jpg",
          imageHeight: 200,
          imageWidth: 200,
          link: {
            mobileWebUrl: "http://localhost:8080/",
            webUrl: "http://localhost:8080/",
          },
        },
        addressTitle: this.house.apartmentName,
        address: this.house.roadName + " " + this.house.roadNameBonBun,
      });
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
#icons {
  display: flex;
  justify-content: center;
}
#click-my-house {
  width: 40px;
  height: 40px;
  color: rgb(233, 132, 141);
}
.house-icon {
  margin-left: 20px;
  margin-right: 20px;
  width: 40px;
  height: 40px;
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
