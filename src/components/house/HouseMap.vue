<template>
  <div id="map"></div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";
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
      "sliderPark",
      "sliderBus",
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
    // houses가 변경되면 마커가 변경됨
    houses: function (value) {
      if (value != null && window.kakao != null && window.kakao.maps != null) {
        // 기존 마커 삭제하기
        this.deleteAptMarker();

        if (value != null && value.length > 0) {
          // 새로운 마커 뿌리기
          this.makeAptMarker(value);
        }
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
    sliderPark() {
      this.deleteParkMarker();
      if (this.isShowPark) this.getParkList();
    },
    sliderBus() {
      this.deleteBusMarker();
      if (this.isShowBus) this.getBusList();
    },
  },
  methods: {
    ...mapActions("houseStore", ["detailHouse"]),
    ...mapMutations("houseStore", [
      "CLEAR_APT_LIST",
      "CLEAR_IS_SHOW_PARK",
      "CLEAR_IS_SHOW_BUS",
    ]),
    initMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      // 지도 생성
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.geocoder = new kakao.maps.services.Geocoder();

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

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
        .get(
          `/park/list?lat=${center.getLat()}&lng=${center.getLng()}&distance=${
            this.sliderPark
          }`
        )
        .then(({ data }) => {
          this.makeParkMarker(data);
        });
    },
    getBusList() {
      let center = this.map.getCenter();
      http
        .get(
          `/bus/list?lat=${center.getLat()}&lng=${center.getLng()}&distance=${
            this.sliderBus
          }`
        )
        .then(({ data }) => {
          this.makeBusMarker(data);
        });
    },
    makeAptMarker(apts) {
      var coords;
      apts.forEach((apt) => {
        coords = new kakao.maps.LatLng(apt.lat, apt.lng);
        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
          position: coords,
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(this.map);

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", () => {
          this.detailHouse(apt);
        });

        // 생성된 마커를 배열에 추가합니다
        this.markers.push(marker);
        ///////////////////////////////////////////////////////////

        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
          position: coords,
          content:
            `<div class='customoverlay'>` +
            `<a href='https://map.kakao.com/?q=%20${apt.roadName}%20${apt.roadNameBonBun}&map_type=DEFAULT&map_hybrid=false&from=total' target='_blank'>` +
            `<span class='title'>${apt.apartmentName}</span>` +
            `</a>` +
            `</div>`,
          yAnchor: 1,
        });

        // 커스텀 오버레이를 지도에 표시합니다
        customOverlay.setMap(this.map);

        this.customOverlays.push(customOverlay);
      });
      this.map.setCenter(coords);
      this.map.setLevel(2);
    },
    makeParkMarker(parks) {
      if (parks != null && parks.length > 0) {
        var imageSrc = "https://i.ibb.co/Nj00cDg/park-marker-color.png";
        var imageSize = new kakao.maps.Size(40, 40);
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
      } else this.deleteParkMarker();
    },
    makeBusMarker(buses) {
      if (buses != null && buses.length > 0) {
        var imageSrc = "https://i.ibb.co/hK445qn/bus-marker-color.png";
        var imageSize = new kakao.maps.Size(40, 40);
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        buses.forEach((bus) => {
          var marker = new kakao.maps.Marker({
            map: this.map,
            position: new kakao.maps.LatLng(bus.lat, bus.lng),
            title: bus.busName,
            image: markerImage,
          });
          marker.setMap(this.map);
          this.busMarkers.push(marker);
        });
      } else this.deleteBusMarker();
    },
    deleteParkMarker() {
      for (let i = 0; i < this.parkMarkers.length; i++) {
        this.parkMarkers[i].setMap(null);
      }
      this.parkMarkers = [];
    },
    deleteBusMarker() {
      for (let i = 0; i < this.busMarkers.length; i++) {
        this.busMarkers[i].setMap(null);
      }
      this.busMarkers = [];
    },
    deleteAptMarker() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.customOverlays.forEach((marker) => {
        marker.setMap(null);
      });

      this.markers = [];
      this.customOverlays = [];
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

    console.log(this.houses);
    this.makeAptMarker(this.houses);
  },
  destroyed() {
    this.CLEAR_APT_LIST();
  },
};
</script>

<style>
.customoverlay {
  position: relative;
  bottom: 50px;
  border-radius: 3px;
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
  border-radius: 3px;
  font-weight: 500;
  overflow: hidden;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 8px center;
}
.customoverlay .title {
  display: block;
  text-align: center;
  background: #fff;
  margin-right: 25px;
  padding: 5px 15px;
  font-size: 10px;
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
