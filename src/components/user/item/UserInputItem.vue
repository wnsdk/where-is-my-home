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
          <card
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
                  <img slot="icon" src="img/icons/common/github.svg" />
                  Github
                </base-button>

                <base-button type="neutral">
                  <img slot="icon" src="img/icons/common/google.svg" />
                  Google
                </base-button>
              </div>
            </template>
            <template>
              <div class="text-center text-muted mb-4">
                <small>Or sign up with credentials</small>
              </div>
              <form role="form">
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="Name"
                  addon-left-icon="ni ni-hat-3"
                >
                </base-input>
                <base-input
                  alternative
                  class="mb-3"
                  placeholder="Email"
                  addon-left-icon="ni ni-email-83"
                >
                </base-input>
                <base-input
                  alternative
                  type="password"
                  placeholder="Password"
                  addon-left-icon="ni ni-lock-circle-open"
                >
                </base-input>
                <div class="text-muted font-italic">
                  <small
                    >password strength:
                    <span class="text-success font-weight-700">strong</span>
                  </small>
                </div>
                <base-checkbox>
                  <span
                    >I agree with the
                    <a href="#">Privacy Policy</a>
                  </span>
                </base-checkbox>
                <div class="text-center">
                  <base-button type="primary" class="my-4"
                    >Create account</base-button
                  >
                </div>
              </form>
            </template>
          </card>
        </div>
      </div>
    </div>
  </section>
</template>
<script>
export default {
  name: "UserInputItem",
  data() {
    return {
      // isLoginError: false,
      user: {
        userId: "",
        userName: "",
        userPwd: "",
        userEmail: "",
        userPhone: "",
        userRole: "",
        userDel: "",
        token: "",
      },
    };
  },
  props:{
    type:{type : String},
  },

  methods:{
    onSubmit(event) {
      event.preventDefault();
      let err = true;
      let msg = "";
      !this.user.userId &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.user.userPwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userPwd.focus());
      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    // onReset(event) {
    //   event.preventDefault();
    //   this.user.userId = "";
    //   this.user.userPwd= "";
    //   this.user.userName= "";
    //   this.user.userPwd= "";
    //   this.user.userEmail= "";
    //   this.user.userPhone= "";
    //   this.user.userRole= "";
    //   this.user.userDel= "";
    //   this.user.token= "";
    //   this.moveList();
    // },
    loginUser(){
        
    }
  }
};
</script>
<style></style>
