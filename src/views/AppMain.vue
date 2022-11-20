<template>
  <div>
    <!-- <img
      src="../../public/img/main-img.png"
      id="main-img"
      width="100vw"
      height="450px"
    /> -->
    <div id="main-img"></div>
    <div id="main-house-search">
      <div id="main-title">어떤 집을 찾고 계세요?</div>
      <house-search-bar id="main-house-search-bar"></house-search-bar>
    </div>
    <div id="main-body">
      <div id="main-news"><main-articles type="news"></main-articles></div>
      <div id="main-board"><main-articles type="board"></main-articles></div>
      <div id="main-qna"><main-articles type="qna"></main-articles></div>
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/modal/HouseSearchBar.vue";
import MainArticles from "@/components/main/MainArticles.vue";

export default {
  name: "AppMain",
  components: {
    HouseSearchBar,
    MainArticles,
  },
};
</script>

<style scoped>
#main-img {
  width: 100vw;
  height: 450px;
  position: absolute;
  z-index: -1;
  background-size: cover;
  background-image: url("../../public/img/main-img.png");
  opacity: 70%;
}
#main-title {
  font-size: 40px;
}
#main-house-search {
  padding-top: 200px;
  width: 100%;
  height: 450px;
}
#main-house-search-bar {
  margin: auto;
  width: 700px;
}
#main-body {
  width: 800px;
  margin: auto;
  display: flex;
  justify-content: space-between;
}
#main-body > div {
  width: 260px;
  height: 100px;
}
</style>
