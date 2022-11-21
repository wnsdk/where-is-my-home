<template>
  <header class="header-global">
    <base-nav class="navbar-main" transparent type="" effect="light" expand>
      <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
        <img src="img/brand/blue.png" alt="logo" />
      </router-link>

      <div class="row" slot="content-header" slot-scope="{ closeMenu }">
        <div class="col-6 collapse-brand">
          <a
            href="https://demos.creative-tim.com/vue-argon-design-system/documentation/"
          >
            <img src="img/brand/blue.png" />
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
            class="nav-link"
            data-toggle="dropdown"
            role="button"
          >
            <i class="ni ni-collection d-lg-none"></i>
            <span class="nav-link-inner--text">Examples</span>
          </a>
          <router-link to="/landing" class="dropdown-item">Landing</router-link>
          <router-link to="/profile" class="dropdown-item">Profile</router-link>
          <router-link to="/login" class="dropdown-item">Login</router-link>
          <router-link to="/register" class="dropdown-item"
            >Register</router-link
          >
        </base-dropdown>
        <router-link :to="{ name: 'house' }" class="m-2 link">
          아파트매매정보
        </router-link>
        <router-link :to="{ name: 'news' }" class="link"> 뉴스 </router-link>
        <router-link :to="{ name: 'board' }" class="m-2 link">
          자유게시판
        </router-link>
        <router-link :to="{ name: 'qna' }" class="link"> QnA </router-link>
      </ul>

      <ul class="navbar-nav align-items-lg-center ml-lg-auto">
        <li class="nav-item d-none d-lg-block ml-lg-4">
          <router-link
            to="/member/login"
            target="_blank"
            rel="noopener"
            class="btn btn-neutral btn-icon"
            >로그인</router-link
          >
        </li>
        <li class="nav-item d-none d-lg-block ml-lg-4">
          <router-link
            to="member/join"
            target="_blank"
            rel="noopener"
            class="btn btn-neutral btn-icon"
            >회원가입</router-link
          >
        </li>
      </ul>
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
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userId);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userId);
      this.userLogout(this.userInfo.userId);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>
<style></style>
