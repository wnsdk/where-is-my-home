<template>
  <div id="map"></div>
</template>

<script>
import { mapState } from "vuex";

export default {
  data() {
    return {
      map: null,
      geocoder: null,
      markers: [],
      customOverlays: [],
    };
  },
  computed: {
    ...mapState("houseStore", ["sidos", "guguns", "houses", "house"]),
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
        this.apt_map = new Map();
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

          if (!this.apt_map.has(`${apt.aptName}`)) {
            this.apt_map.set(`${apt.aptName}`, 1);
          } else {
            this.apt_map.set(
              `${apt.aptName}`,
              this.apt_map.get(`${apt.aptName}`) + 1
            );
          }
          // 나중에 처음 생성될때만 만들고 그 다음부터는 오버레이.setContent(content)로 값 변경 하게 했으면 좋겠다.
          let price_count = this.apt_map.get(`${apt.aptName}`);

          // 커스텀 오버레이에 표시할 내용입니다
          var content = `<div class ="price-label"><span class="left"></span><span class="center">${price_count}건</span><span class="right"></span></div>`;

          // 커스텀 오버레이가 표시될 위치입니다
          var position = coords;

          // 커스텀 오버레이를 생성합니다
          var customOverlay = new kakao.maps.CustomOverlay({
            position: position,
            content: content,
          });
          this.customOverlays.push(customOverlay);

          // 커스텀 오버레이를 지도에 표시합니다
          customOverlay.setMap(this.map);

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        });
        this.map.setCenter(coords);
        this.map.setLevel(2);
      }
    },
  },
  methods: {
    initMap() {
      var mapContainer = document.getElementById("map"); // 지도를 표시할 div
      var mapOption = {
        center: new kakao.maps.LatLng(37.5012743, 127.039585), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
      };
      // 지도 생성
      this.map = new kakao.maps.Map(mapContainer, mapOption);
      this.geocoder = new kakao.maps.services.Geocoder();
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");

      // appkey를 .env.local 파일에서 관리하는 방법??????????
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&libraries=services&appkey=3c7045b115e2a3139ea644855b1dee31`;

      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      document.head.appendChild(script);
    } else {
      this.initMap();
    }
  },
};
</script>

<style scoped></style>
