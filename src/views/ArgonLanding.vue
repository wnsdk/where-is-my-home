<template>
  <div>
    <section class="section-shaped my-0 position-relative">
      <div class="shape shape-style-1 shape-default shape-skew">
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
          <div class="display-6 text-white">어떤 집을 찾고 계세요?</div>
          <house-search-bar id="main-house-search-bar"></house-search-bar>
        </div>
      </div>
    </section>

    <section id="card3">
      <div class="container">
        <div class="col-lg-12 row justify-content-center">
          <div class="col-lg-4">
            <card class="border-0">
              <h6 class="text-primary text-uppercase">NEWS</h6>
              <div id="main-news" class="text-primary text-uppercase">
                <main-articles type="news"></main-articles>
              </div>
              <p class="description mt-3">
                Argon is a great free UI package based on Bootstrap 4 that
                includes the most important components and features.
              </p>
              <div>
                <badge type="primary" rounded>design</badge>
                <badge type="primary" rounded>system</badge>
                <badge type="primary" rounded>creative</badge>
              </div>
              <div id="main-news" class="text-primary text-uppercase">
                <main-articles type="news"></main-articles>
              </div>
              <base-button tag="a" href="#" type="primary" class="mt-4">
                Learn more
              </base-button>
            </card>
          </div>

          <div class="col-lg-4">
            <card class="border-0">
              <icon name="ni ni-check-bold" type="primary" rounded class="mb-4">
              </icon>
              <h6 class="text-primary text-uppercase">게시판</h6>
              <p class="description mt-3"></p>
              <div id="main-news" class="text-primary text-uppercase">
                <main-articles type="board"></main-articles>
              </div>
            </card>
          </div>
          <div class="col-lg-4">
            <card class="border-0">
              <icon name="ni ni-check-bold" type="primary" rounded class="mb-4">
              </icon>
              <h6 class="text-primary text-uppercase">QnA</h6>
              <div id="main-news" class="text-primary text-uppercase">
                <main-articles type="QnA"></main-articles>
              </div>
              <p class="description mt-3">
                Argon is a great free UI package based on Bootstrap 4 that
                includes the most important components and features.
              </p>
              <div>
                <badge type="primary" rounded>design</badge>
                <badge type="primary" rounded>system</badge>
                <badge type="primary" rounded>creative</badge>
              </div>
              <div id="main-news" class="text-primary text-uppercase">
                <main-articles type="news"></main-articles>
              </div>
              <base-button tag="a" href="#" type="primary" class="mt-4">
                Learn more
              </base-button>
            </card>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/modal/HouseSearchBar.vue";
import MainArticles from "@/components/main/MainArticles.vue";

export default {
  name: "home",
  components: {
    HouseSearchBar,
    MainArticles,
  },
};
</script>

<style scoped>
#card3 {
  margin-bottom: -126px !important;
}
</style>
