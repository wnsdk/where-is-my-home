<template>
  <div v-if="house" id="house-detail-container">
    <div id="roadview"></div>
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
        <td>{{ house.roadName }} {{ parseInt(this.house.roadNameBonBun) }}</td>
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
      aptImgUrl: String,
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
  watch: {
    house() {
      this.fillHeart();
      this.getKakaoRoadView();
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&libraries=services&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;

      /* global kakao */
      document.head.appendChild(script);
    }
    console.log("디테일 창이 띄어짐");
    console.log(this.houseDeal);

    this.fillHeart();
    this.getKakaoRoadView();
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
    getKakaoRoadView() {
      // 로드뷰 이미지 가져오기
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체
      var position = new kakao.maps.LatLng(this.house.lat, this.house.lng);

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
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
  align-items: center;
  /* justify-content: center;
  flex: auto;
  flex-wrap: wrap; */
}
#house-detail-name {
  margin: 20px 0;
  width: 100%;
  font-size: 30px;
  font-weight: 600;
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
#roadview {
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
}
table {
  display: block;
  margin-top: 20px;
  margin-left: 50px;
  width: 100px;
}
th {
  width: 100px;
  background-color: rgb(245, 245, 245);
}
td {
  width: 200px;
}
td,
th {
  border-top: solid 1px rgb(228, 228, 228);
  text-align: left;
  font-size: 15px;
  padding: 2px 0 2px 20px;
}
tr:first-child > * {
  border-top: solid 1px rgb(167, 167, 167);
}
tr:last-child > * {
  border-bottom: solid 1px rgb(167, 167, 167);
}
</style>
