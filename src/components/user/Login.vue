<template>
  <section class="section section-shaped section-lg my-0">
    <div class="shape shape-style-1 bg-gradient-default">
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
      <span></span>
    </div>
    <div class="container pt-lg-md">
      <div class="row justify-content-center">
        <div class="col-lg-5">
          <b-card
            type="secondary"
            shadow
            header-classes="bg-white pb-5"
            body-classes="px-lg-5 py-lg-5"
            class="border-0"
          >
            <template>
              <div class="text-muted text-center mb-3">
                <small>Sign in with</small>
              </div>
              <div class="btn-wrapper text-center">
                <base-button type="neutral">
                  <b-img slot="icon" src="/img/icons/common/github.svg" />
                  Github
                </base-button>

                <base-button type="neutral">
                  <b-img slot="icon" src="/img/icons/common/google.svg" />
                  Google
                </base-button>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">
                <small>Or sign in with credentials</small>
              </div>
              <b-form-group label-for="userId">
                <b-form-input
                alternative
                class="mb-3"
                addon-left-icon="ni ni-email-83"
                id="userId"
                v-model="user.userid"
                required
                placeholder="아이디...."
              ></b-form-input>
              </b-form-group>
              <b-form-group label-for="userPwd" >
                <b-form-input
                alternative
                type="password"
                class="mb-3"
                addon-left-icon="ni ni-email-83"
                id="userPwd"
                v-model="user.userpwd"
                required
                placeholder="비밀번호...."
              ></b-form-input>
              </b-form-group>
                <b-checkbox> Remember me </b-checkbox>
                <div class="text-center">
                  <b-button type="button" variant="success" class="my-4"
                  @click="confirm"
                    >로그인</b-button
                  >
                </div>
              
            </template>
          </b-card>
          <div class="row mt-3">
            <div class="col-6">
              <a href="#" class="text-light">
                <small>비밀번호 찾기?</small>
              </a>
            </div>
            <div class="col-6 text-right">
              <a href="#" class="text-light" @click="movePage">
                <small>회원가입</small>
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import { mapState, mapActions } from "vuex";
const memberStore = "memberStore";

export default {
  name: "Login",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: "",
        userpwd: "",
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user); //로그인은 비동기, 따라서 Actions
      await console.log("여기는 Login.js confirm안에 있는 this.user >> ", this.user)
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
      else{
        console.log("로그인 에러")
      }
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>
<style></style>
