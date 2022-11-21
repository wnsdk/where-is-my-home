<template>
  <div id="map"></div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import http from "@/api/http";

export default {
  data() {
    return {
      map: null,
      geocoder: null,
      markers: [],
      parkMarkers: [],
      busMarkers: [],
      customOverlays: [],
    };
  },
  computed: {
    ...mapState("houseStore", [
      "sidos",
      "guguns",
      "houses",
      "house",
      "isShowPark",
      "isShowBus",
    ]),
  },
  watch: {
    // house가 변경되면 마커 focus가 이동됨
    house: function (apt) {
      if (apt != null && window.kakao != null && window.kakao.maps != null) {
        let coords = new kakao.maps.LatLng(apt.lat, apt.lng);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          position: coords,
        });
        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(this.map);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        this.map.setCenter(coords);
        this.map.setLevel(2);
      }
    },
    // houses가 변경되면 지도가 변경됨
    houses: function (value) {
      if (value != null && window.kakao != null && window.kakao.maps != null) {
        // 기존 마커 삭제하기
        for (let i = 0; i < this.markers.length; i++) {
          this.markers[i].setMap(null);
          this.customOverlays[i].setMap(null);
        }

        // 새로운 마커 뿌리기
        var coords;
        value.forEach((apt) => {
          coords = new kakao.maps.LatLng(apt.lat, apt.lng);
          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            position: coords,
          });
          // 마커가 지도 위에 표시되도록 설정합니다
          marker.setMap(this.map);

          // 생성된 마커를 배열에 추가합니다
          this.markers.push(marker);
          ///////////////////////////////////////////////////////////
          console.log(apt);
          // 커스텀 오버레이를 생성합니다
          var customOverlay = new kakao.maps.CustomOverlay({
            position: coords,
            content:
              `<div class='customoverlay'>` +
              `<a href='https://map.kakao.com/link/map/${apt.dongCode}' target='_blank'>` +
              `<span class='title'>${apt.apartmentName}</span>` +
              `</a>` +
              `</div>`,
            xAnchor: 0.3,
            yAnchor: 0.91,
          });

          // 커스텀 오버레이를 지도에 표시합니다
          customOverlay.setMap(this.map);
        });
        this.map.setCenter(coords);
        this.map.setLevel(2);
      }
    },
    isShowPark(value) {
      if (value) this.getParkList();
      else this.deleteParkMarker();
    },
    isShowBus(value) {
      if (value) this.getBusList();
      else this.deleteBusMarker();
    },
  },
  methods: {
    ...mapMutations("houseStore", ["CLEAR_IS_SHOW_PARK", "CLEAR_IS_SHOW_BUS"]),
    initMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      // 지도 생성
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.geocoder = new kakao.maps.services.Geocoder();

      // map(의 중심좌표)가 변경되면 공원 및 버스 정보 보여주는 위치가 달라짐
      kakao.maps.event.addListener(this.map, "tilesloaded", () => {
        // 기존 마커 삭제하기
        this.deleteParkMarker();
        this.deleteBusMarker();

        if (this.isShowPark) this.getParkList();
        if (this.isShowBus) this.getBusList();
      });
    },
    getParkList() {
      let center = this.map.getCenter();
      http
        .get(`/park/list?lat=${center.getLat()}&lng=${center.getLng()}`)
        .then(({ data }) => {
          this.makeParkMarker(data);
        });
    },
    getBusList() {
      // let center = this.map.getCenter();
      // http
      //   .get(`/park/list?lat=${center.getLat()}&lng=${center.getLng()}`)
      //   .then(({ data }) => {
      //     console.log(this.isShowPark);
      //     console.log(data);
      //   });
    },
    makeParkMarker(parks) {
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      var imageSize = new kakao.maps.Size(24, 35);
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      parks.forEach((park) => {
        var marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(park.lat, park.lng),
          title: park.parkName,
          image: markerImage,
        });

        marker.setMap(this.map);
        this.parkMarkers.push(marker);
      });
    },
    makeBusMarker() {
      // var imageSrc =
      //   "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      // var imageSize = new kakao.maps.Size(24, 35);
      // var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      // parks.forEach((park) => {
      //   var marker = new kakao.maps.Marker({
      //     map: this.map,
      //     position: new kakao.maps.LatLng(park.lat, park.lng),
      //     title: bus.parkName,
      //     image: markerImage,
      //   });
      //   marker.setMap(this.map);
      //   this.busMarkers.push(marker);
      // });
    },
    deleteParkMarker() {
      for (let i = 0; i < this.parkMarkers.length; i++) {
        this.parkMarkers[i].setMap(null);
      }
    },
    deleteBusMarker() {
      // for (let i = 0; i < this.busMarkers.length; i++) {
      //   this.busMarkers[i].setMap(null);
      // }
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&libraries=services&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;

      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
    this.CLEAR_IS_SHOW_PARK();
    this.CLEAR_IS_SHOW_BUS();
  },
};
</script>

<style>
.customoverlay {
  position: relative;
  bottom: 50px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
}
.customoverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.customoverlay a {
  display: block;
  text-decoration: none;
  color: #111;
  text-align: center;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 500;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 35px;
  padding: 10px 15px;
  font-size: 14px;
  font-weight: 500;
}
.customoverlay:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: -12px;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
</style>
