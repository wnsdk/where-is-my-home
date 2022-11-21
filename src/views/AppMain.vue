<template>
  <div id="main-body">
    <section id="main-background" class="section-shaped my-0">
      <!-- 배경 이미지-->
      <div
        class="shape shape-style-1 shape-default shape-skew"
        id="main-background"
      >
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
      <div class="container shape-container d-flex">
        <div class="col">
          <h6 class="display-6 text-white">어떤 집을 찾고 계세요?</h6>
          <house-search-bar id="main-house-search-bar"></house-search-bar>
        </div>
      </div>
    </section>

    <section id="card3">
      <div class="container">
        <div class="col-lg-12 row justify-content-center">
          <card id="card" class="border-0">
            <icon name="ni ni-check-bold" type="primary" rounded class="mb-4">
            </icon>
            <h6 class="text-primary text-uppercase card-title">NEWS</h6>
            <!-- <p class="description mt-3">
                Argon is a great free UI package based on Bootstrap 4 that
                includes the most important components and features.
              </p> -->
            <div>
              <badge type="primary" rounded>design</badge>
              <badge type="primary" rounded>system</badge>
              <badge type="primary" rounded>creative</badge>
            </div>
            <p class="description mt-3"></p>
            <div id="main-news" class="text-primary text-uppercase">
              <main-articles type="news"></main-articles>
            </div>

            <base-button tag="a" href="#" type="primary" class="mt-4">
              더보기
            </base-button>
          </card>

          <card id="card" class="border-0">
            <icon name="ni ni-check-bold" type="primary" rounded class="mb-4">
            </icon>
            <h6 class="text-primary text-uppercase card-title">공지사항</h6>
            <div>
              <badge type="primary" rounded>design</badge>
              <badge type="primary" rounded>system</badge>
              <badge type="primary" rounded>creative</badge>
            </div>
            <p class="description mt-3"></p>
            <div id="main-news" class="text-primary text-uppercase">
              <main-articles type="board"></main-articles>
            </div>

            <base-button tag="a" href="#" type="primary" class="mt-4">
              더보기
            </base-button>
          </card>

          <card id="card" class="border-0">
            <icon name="ni ni-check-bold" type="primary" rounded class="mb-4">
            </icon>
            <h6 class="text-primary text-uppercase card-title">Q&A</h6>
            <div>
              <badge type="primary" rounded>design</badge>
              <badge type="primary" rounded>system</badge>
              <badge type="primary" rounded>creative</badge>
            </div>
            <p class="description mt-3"></p>
            <div id="main-news" class="text-primary text-uppercase">
              <main-articles type="qna"></main-articles>
            </div>
            <base-button tag="a" href="#" type="primary" class="mt-4">
              더보기
            </base-button>
          </card>
        </div>
      </div>
    </section>

    <argon-app-footer id="footer"></argon-app-footer>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/modal/HouseSearchBar.vue";
import MainArticles from "@/components/main/MainArticles.vue";
import ArgonAppFooter from "@/layout/ArgonAppFooter";
import { mapMutations } from "vuex";

export default {
  name: "home",
  components: {
    HouseSearchBar,
    MainArticles,
    ArgonAppFooter,
  },
  methods: {
    ...mapMutations(["SET_FONT_COLOR"]),
  },
  created() {
    this.SET_FONT_COLOR("#2E2E2E");
  },
};
</script>

<style scoped>
#main {
  height: 1300px;
  min-height: 100%;
  position: relative;
}
#main-background {
  width: 100%;
  height: 600px;
  position: absolute;
  z-index: -1;
}
#card3 {
  width: 100%;
  display: flex;
  justify-content: center;

  position: absolute;
  z-index: 2;
  margin-top: 350px;
  margin-bottom: -126px !important;
}
#card {
  width: 265px;
  margin-left: 20px;
  margin-right: 20px;
  box-shadow: 5px 5px 10px rgba(68, 68, 68, 0.3);
}
/* 카드 제목 */
.card-title {
  font-weight: 900 !important;
  font-size: 20px;
  font-family: "Noto Sans KR", sans-serif;
}
#footer {
  width: 100%;
  position: absolute;
  bottom: 0;
  right: 0;
  /* transform: translatY(-100%); */
}
</style>
