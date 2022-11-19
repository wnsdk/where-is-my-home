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
              <b-form @submit="onSubmit" @reset="onReset">
                <div class="text-center text-muted mb-4"></div>
              <b-form-group label-for="userName">
                <b-form-input
                  ref = "userName"
                  alternative
                  type="text"
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userName"
                  v-model="user.userName"
                  required
                  placeholder="이름...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label-for="userId">
                <b-form-input
                  ref = "userId"
                  alternative
                  type="text"
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userId"
                  v-model="user.userId"
                  required
                  placeholder="아이디...."
                >
                </b-form-input>
                <b-button type="button" @click="checkId">
                아이디 중복 체크
              </b-button>
              </b-form-group>
              <b-form-group label-for="userPwd">
                <b-form-input
                  ref = "userPwd"
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
              <b-form-group label-for="userEmail">
                <b-form-input
                  alternative
                  type="email"
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userEmail"
                  v-model="user.userEmail"
                  required
                  placeholder="Email...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label-for="userPhone">
                <b-form-input
                  alternative
                  type="text"
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userPhone"
                  v-model="user.userPhone"
                  required
                  placeholder="전화번호...."
                ></b-form-input>
              </b-form-group>
              <div class="text-center">
                <b-button
                  type="submit"
                  variant="success"
                  class="my-4"
                  
                  >회원가입</b-button
                >
              </div>
              </b-form>
            </template>
          </b-card>
          <div class="row mt-3">
            <div class="col-6"></div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
import http from "@/api/http";

export default {
  name: "UserInputItem",
  data() {
    return {
      user: {
        userId: "",
        userName: "",
        userPwd: "",
        userEmail: "",
        userPhone: "",
        userRole: "member",
      },
      isuserId: false,
    };
  },
  props:{
    type:{type : String},
  },

  methods:{
    checkId() {
      http
        .get("member/idcheck/" + this.user.userId)
        .then(({data}) => {
          console.log(data);
          if (data > 0) {
            alert("이미 존재하는 아이디입니다.");
          } else {
            alert("사용할 수 있는 아이디입니다.");
            this.isuserId = true;
            console.log(this.isuserId);
          }
        })
        .catch(() => {
          alert("아이디를 입력하세요");
          return;
        });
    },
    
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.userName &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.userName.focus());
      err &&
        !this.user.userId &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.user.userPwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userPwd.focus());
      err &&
        !this.user.userEmail &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userEmail.focus());
      err &&
        !this.user.userPhone &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userPhone.focus());
      if (!err) alert(msg);
      else
        this.type === "join" ? this.joinMember() : this.modifyMember();
    },
    onReset(event) {
      event.preventDefault();
      this.user.userId= "",
      this.user.userName= "",
      this.user.userPwd= "",
      this.user.userEmail= "",
      this.user.userPhone= "",
      this.user.userRole= "",
      this.moveLogin();
    },
    joinMember() {
      if(!this.isuserId){
        alert("아이디 중복 확인 버튼을 눌러주세요");
        console.log(this.user);
        return;
      } 
      http
        .post(`/member`, {
          userId: this.user.userId,
          userName: this.user.userName,
          userPwd: this.user.userPwd,
          userEmail: this.user.userEmail,
          userPhone: this.user.userPhone,
          userRole : this.user.userRole,
        })
        .then((response) => {
          console.log(response);
          this.moveLogin();
        })
        .catch((err) => {
          console.log(err);
          return;
        });
    },
    modifyMember() {
      http
        .put(`/member`, {
          userId: this.user.userId,
          userName: this.user.userName,
          userPwd: this.user.userPwd,
          userEmail: this.user.userEmail,
          userPhone: this.user.userPhone,
          userRole : this.user.userRole,
        })
        .then((response) => {
          console.log(response);
          this.moveMypage();
        });
    },
    moveLogin() {
      this.$router.push({ name: "login" });
    },
    moveMypage() {
      this.$router.push({ name: "mypage" });
    },
  },
  
};
</script>
<style></style>
