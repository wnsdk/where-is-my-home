<template>
  <section class="section section-shaped section-lg my-0" id="login-page">
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
              <!-- <div class="text-muted text-center mb-3">
                <small>Sign in with</small>
              </div> -->
              <div class="btn-wrapper text-center">
                <b-button id="kakao-login-btn" @click="loginWithKakao">
                  <b-img
                    slot="icon"
                    src="/img/icons/common/kakao_login_medium_wide.png"
                  />
                </b-button>
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
                  v-model="user.userId"
                  required
                  placeholder="아이디...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label-for="userPwd">
                <b-form-input
                  alternative
                  type="password"
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userPwd"
                  v-model="user.userPwd"
                  required
                  placeholder="비밀번호...."
                ></b-form-input>
              </b-form-group>
              <b-checkbox> Remember me </b-checkbox>
              <div class="text-center">
                <b-button
                  type="button"
                  variant="success"
                  class="my-4"
                  @click="confirm"
                  >로그인</b-button
                >
              </div>
            </template>
          </b-card>
          <div class="row mt-3">
            <div class="col-6">
              <a href="#" class="text-light">
                <small>비밀번호 찾기</small>
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
import http from "@/api/http";
const memberStore = "memberStore";

export default {
  name: "Login",
  data() {
    return {
      // isLoginError: false,
      user: {
        userId: null,
        userPwd: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm(kakaoId, loginType) {
      if (loginType == 1) {
        await this.userConfirm({
          userId: kakaoId,
          loginType: loginType,
        });
      } else if (loginType == 0) {
        await this.userConfirm(this.user);
      }

      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      } else {
        console.log("로그인 에러");
      }
    },
    async loginWithKakao() {
      // appkey를 .env.local 파일에서 관리하는 방법??????????
      await window.Kakao.init("3c7045b115e2a3139ea644855b1dee31");

      // 카카오 - 어플리케이션 연결 끊기
      // if (window.Kakao.Auth.getAccessToken()) {
      //   await console.log("카카오 계정 회원탈퇴 시키기");
      //   await window.Kakao.API.request({
      //     url: "/v1/user/unlink",
      //     success: (response) => {
      //       console.log(response);
      //     },
      //     fail: (error) => {
      //       console.log(error);
      //     },
      //   });
      //   await window.Kakao.Auth.setAccessToken(undefined);
      // }

      await window.Kakao.Auth.login({
        success: async () => {
          await window.Kakao.API.request({
            url: "/v2/user/me",
            data: {
              property_keys: ["kakao_account.profile", "kakao_account.email"],
            },
            success: async (response) => {
              // ID 중복체크를 통해 회원가입 유무 확인하기
              var kakaoId = response.id + "K";
              http.get(`/member/idcheck/${kakaoId}`).then(({ data }) => {
                // 존재하는 경우 로그인 처리
                if (data > 0) this.hiddenKakaoLogin(kakaoId);
                // 존재하지 않으면 회원가입
                else this.hiddenKakaoJoin(response, kakaoId);
              });
            },
            fail: (error) => {
              console.log(error);
            },
          });
        },
        fail: (error) => {
          console.log(error);
        },
      });
    },
    hiddenKakaoLogin(kakaoId) {
      this.confirm(kakaoId, 1);
    },
    hiddenKakaoJoin(response, kakaoId) {
      console.log(response);
      http
        .post(`/member`, {
          userId: kakaoId,
          userName: response.kakao_account.profile.nickname,
          userImgUrl: response.kakao_account.profile.profile_image_url,
          userEmail: response.kakao_account.email,
          userRole: "member",
          loginType: 1,
        })
        .then(() => {
          this.hiddenKakaoLogin(kakaoId);
        });
    },
    movePage() {
      this.$router.push({ name: "join" });
    },
  },
};
</script>
<style scoped>
#kakao-login-btn {
  background-color: white;
  border: white;
  padding: 0;
  margin-top: 10px;
  margin-bottom: 15px;
}
</style>
