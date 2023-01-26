<template>
  <div class="profile-page" id="my-page">
    <section class="section-profile-cover section-shaped my-0">
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
                    class="rounded-circle"
                    :src="userDetail.userImgUrl"
                    v-if="userDetail.userImgUrl != null"
                  />
                  <div v-else>
                    <img
                      class="rounded-circle"
                      src="@/assets/img/theme/noprofile.jpg"
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

                  <base-button
                    type="default"
                    size="sm"
                    class="float-right"
                    @click="withdraw"
                    >탈퇴하기</base-button
                  >
                </div>
              </div>
              <div class="col-lg-4 order-lg-1">
                <div class="card-profile-stats d-flex justify-content-center">
                  <div>
                    <span class="heading">22</span>
                    <span class="description">Friends</span>
                  </div>
                  <div>
                    <span class="heading">10</span>
                    <span class="description">Photos</span>
                  </div>
                  <div>
                    <span class="heading">89</span>
                    <span class="description">Comments</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="text-center mt-5">
              <h3>
                {{ userDetail.userName }}
              </h3>
              <div class="h6 font-weight-300">
                <i class="ni location_pin mr-2"></i>{{ userDetail.userId }}
              </div>
              <div class="h6 mt-4">
                <i class="ni business_briefcase-24 mr-2"></i
                >{{ userDetail.userEmail }}
              </div>
              <div>
                <i class="ni education_hat mr-2"></i>{{ userDetail.userPhone }}
              </div>
            </div>
            <div class="mt-5 py-5 border-top text-center">
              <div class="row justify-content-center">
                <div class="col-lg-9"></div>
              </div>
            </div>
          </div>
        </card>
      </div>
    </section>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
import { deleteMember } from "@/api/member";

export default {
  computed: {
    ...mapGetters("memberStore", ["userDetail"]),
  },
  methods: {
    ...mapActions("memberStore", ["userLogout"]),
    withdraw() {
      if (confirm("정말로 탈퇴하겠습니까?")) {
        deleteMember(this.userDetail.userId, () => {
          this.userLogout(this.userDetail.userId);
          sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
          sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
          if (this.$route.path != "/") this.$router.push({ name: "main" });
        });
      }
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
</style>
