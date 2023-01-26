<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped section-my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
    </section>

    <section class="section section-skew">
      <div class="container">
        <card shadow class="card-profile mt--300" no-body>
          <div class="px-4">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <img
                    :src="userDetail.userImgUrl"
                    v-if="userDetail.userImgUrl != null"
                  />
                  <div>
                    <img
                      class="rounded-circle"
                      src="@/assets/img/theme/noprofile.jpg"
                      v-if="userDetail.userImgUrl == null"
                    />
                    <div
                      class="avatarImg"
                      v-text="userDetail.userId.charAt(0).toUpperCase()"
                    ></div>
                  </div>
                </div>
              </div>
              <div
                class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center"
              >
                <div class="card-profile-actions py-4 mt-lg-0">
                  <router-link to="/member/modify">
                    <b-button type="info" size="sm" class="mr-4"
                      >수정하기</b-button
                    >
                  </router-link>

                  <base-button type="default" size="sm" class="float-right"
                    >탈퇴하기</base-button
                  >
                </div>
              </div>
              <div class="col-lg-4 order-lg-1">
                <div class="card-profile-stats d-flex justify-content-center">
                  <div>
                    <span class="heading">2</span>
                    <span class="description">Friends</span>
                  </div>
                  <div>
                    <span class="heading">0</span>
                    <span class="description">Photos</span>
                  </div>
                  <div>
                    <span class="heading">1</span>
                    <span class="description">Comments</span>
                  </div>
                </div>
              </div>
            </div>

            <div class="py-3 border-top text-center">
              <div class="row justify-content-center">
                <div class="col-lg-9"></div>
              </div>
            </div>
            <b-form @submit="onSubmit">
              <b-form-group label-for="userName">
                <b-form-input
                  disabled
                  ref="userName"
                  alternative
                  type="text"
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userName"
                  v-model="userDetail.userName"
                  required
                  placeholder="이름...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label-for="userId">
                <b-form-input
                  disabled
                  ref="userId"
                  alternative
                  type="text"
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userId"
                  v-model="userDetail.userId"
                  required
                  placeholder="아이디...."
                >
                </b-form-input>
              </b-form-group>
              <b-form-group label-for="userPwd">
                <b-form-input
                  ref="userPwd"
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
                <b-button type="submit" variant="success" class="my-4"
                  >회원수정</b-button
                >
              </div>
            </b-form>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import http from "@/api/http";

export default {
  name: "UserModify",
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
  computed: {
    ...mapGetters("memberStore", ["userDetail"]),
  },

  methods: {
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.userPwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userPwd.focus());
      err &&
        !this.user.userEmail &&
        ((msg = "이메일을 입력해주세요"),
        (err = false),
        this.$refs.userEmail.focus());
      err &&
        !this.user.userPhone &&
        ((msg = "전화번호를 입력해주세요"),
        (err = false),
        this.$refs.userPhone.focus());
      if (!err) alert(msg);
      else this.modifyMember();
    },

    modifyMember() {
      http
        .put(`/member`, {
          userId: this.userDetail.userId,
          userName: this.userDetail.userName,
          userPwd: this.user.userPwd,
          userEmail: this.user.userEmail,
          userPhone: this.user.userPhone,
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
<style scoped>
.avatarImg {
  font-size: 100px;
  position: absolute;
  color: white;
  transform: translateX(70px) translateY(-30px);
}
.section-my-0 {
  margin-top: -330px;
}
</style>

<!--<template>
  <user-input-item type="modify" />
</template>

<script>
import UserInputItem from "@/components/user/item/UserInputItem";

export default {
  name: "UserModify",
  components: {
    UserInputItem,
  },
};
</script>
<style></style>
-->
