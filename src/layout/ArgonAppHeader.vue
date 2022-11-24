<template>
  <header class="header-global">
    <base-nav class="navbar-main" transparent type="" effect="light" expand>
      <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
        <img src="@/assets/img/brand/blue.png" alt="logo" />
      </router-link>

      <div class="row" slot="content-header" slot-scope="{ closeMenu }">
        <div class="col-6 collapse-brand">
          <a
            href="https://demos.creative-tim.com/vue-argon-design-system/documentation/"
          >
            <img src="@/assets/img/brand/blue.png" />
          </a>
        </div>
        <div class="col-6 collapse-close">
          <close-button @click="closeMenu"></close-button>
        </div>
      </div>

      <ul class="navbar-nav navbar-nav-hover align-items-lg-center">
        <base-dropdown tag="li" class="nav-item">
          <a
            slot="title"
            href="#"
            class="m-2 link"
            data-toggle="dropdown"
            role="button"
          >
            <router-link to="/house/search" class="m-2 link my-font-color"
              >아파트매매정보</router-link
            >
          </a>
          <router-link to="/house/search" class="dropdown-item"
            >지역별 검색</router-link
          >
          <router-link to="/house/myhouse" class="dropdown-item"
            >관심 매물</router-link
          >
        </base-dropdown>

        <router-link :to="{ name: 'news' }" class="m-2 link my-font-color"
          >부동산뉴스</router-link
        >
        <router-link :to="{ name: 'board' }" class="m-2 link my-font-color"
          >자유게시판</router-link
        >
        <router-link :to="{ name: 'qna' }" class="m-2 link my-font-color">
          Q&A
        </router-link>
      </ul>

      <!-- after login -->
      <b-navbar-nav class="ml-auto" v-if="userInfo">
        <b-nav-item class="align-self-center header-nav-item">
          <b-avatar
            variant="primary"
            v-text="userInfo.userId.charAt(0).toUpperCase()"
            v-if="userInfo.userImgUrl == null"
          ></b-avatar>
          <b-avatar
            variant="primary"
            :src="userInfo.userImgUrl"
            v-if="userInfo.userImgUrl != null"
            width="500"
          ></b-avatar>
        </b-nav-item>
        <b-nav-item class="align-self-center header-nav-item">
          <router-link :to="{ name: 'mypage' }" class="link align-self-center"
            >{{ userInfo.userName }}님 환영합니다.</router-link
          >
        </b-nav-item>

        <b-nav-item class="align-self-center header-nav-item">
          <div
            :to="{ name: 'main' }"
            class="link align-self-center text-black"
            @click="onClickLogout"
          >
            로그아웃
          </div>
        </b-nav-item>
      </b-navbar-nav>

      <!-- before login -->
      <b-navbar-nav class="ml-auto" v-else>
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-icon icon="people" font-scale="2"></b-icon>
          </template>
          <b-dropdown-item>
            <router-link :to="{ name: 'join' }" class="link">
              <b-icon icon="person-circle"></b-icon> 회원가입
            </router-link>
          </b-dropdown-item>
          <b-dropdown-item>
            <router-link :to="{ name: 'login' }" class="link">
              <b-icon icon="key"></b-icon> 로그인
            </router-link>
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </base-nav>
  </header>
</template>
<script>
import BaseNav from "@/components/Argons/BaseNav";
import BaseDropdown from "@/components/Argons/BaseDropdown";
import CloseButton from "@/components/Argons/CloseButton";

import { mapState, mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "ArgonAppHeader",
  data() {
    return {};
  },
  components: {
    BaseNav,
    CloseButton,
    BaseDropdown,
  },
  computed: {
    ...mapState(["fontColor"]),
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    onClickLogout() {
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      this.userLogout(this.userInfo.userId);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
  watch: {
    fontColor() {
      let texts = document.querySelectorAll(".my-font-color");
      texts.forEach((text) => {
        text.setAttribute("style", `color: ${this.fontColor} !important`);
      });
    },
  },
  mounted() {
    let texts = document.querySelectorAll(".my-font-color");
    texts.forEach((text) => {
      text.setAttribute("style", `color: ${this.fontColor} !important`);
    });
  },
};
</script>
<style scoped>
.navbar-main {
  padding: 0;
  height: 68px;
}
.link {
  text-decoration: none;
}
b-nav-item {
  height: 500px;
}
</style>
