<template>
  <SlideYUpTransition :duration="animationDuration">
    <div
      class="modal fade"
      @click.self="closeModal"
      :class="[
        { 'show d-block': show },
        { 'd-none': !show },
        { 'modal-mini': type === 'mini' },
      ]"
      v-show="show"
      tabindex="-1"
      role="dialog"
      :aria-hidden="!show"
    >
      <div
        class="modal-dialog modal-dialog-centered"
        :class="[{ 'modal-notice': type === 'notice' }, modalClasses]"
      >
        <div
          class="modal-content"
          :class="[
            gradient ? `bg-gradient-${gradient}` : '',
            modalContentClasses,
          ]"
        >
          <div class="modal-header" :class="[headerClasses]">
            <slot name="header">비밀번호 찾기</slot>
            <slot name="close-button">
              <button
                type="button"
                class="close"
                v-if="showClose"
                @click="closeModal"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span :aria-hidden="!show">×</span>
              </button>
            </slot>
          </div>

          <div class="modal-body" :class="bodyClasses">
            <slot>
              <b-form-group>
                아이디
                <b-form-input
                  alternative
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userId"
                  v-model="user.userId"
                  required
                  placeholder="가입 시 기입한 아이디를 입력해주세요"
                ></b-form-input>

                이메일
                <b-form-input
                  alternative
                  class="mb-3"
                  addon-left-icon="ni ni-email-83"
                  id="userEmail"
                  v-model="user.userEmail"
                  required
                  placeholder="가입 시 기입한 이메일을 입력해주세요"
                ></b-form-input>
              </b-form-group>
            </slot>
          </div>

          <div class="modal-footer" :class="footerClasses">
            <slot name="footer">
              <b-button
                type="button"
                variant="success"
                class="my-4"
                @click="sendEmail"
                >이메일 전송</b-button
              >
            </slot>
          </div>
        </div>
      </div>
    </div>
  </SlideYUpTransition>
</template>
<script>
import { SlideYUpTransition } from "vue2-transitions";
import http from "@/api/http";

export default {
  name: "modal",
  components: {
    SlideYUpTransition,
  },
  props: {
    show: Boolean,
    showClose: {
      type: Boolean,
      default: true,
    },
    type: {
      type: String,
      default: "",
      validator(value) {
        let acceptedValues = ["", "notice", "mini"];
        return acceptedValues.indexOf(value) !== -1;
      },
      description: 'Modal type (notice|mini|"") ',
    },
    modalClasses: {
      type: [Object, String],
      description: "Modal dialog css classes",
    },
    modalContentClasses: {
      type: [Object, String],
      description: "Modal dialog content css classes",
    },
    gradient: {
      type: String,
      description: "Modal gradient type (danger, primary etc)",
    },
    headerClasses: {
      type: [Object, String],
      description: "Modal Header css classes",
    },
    bodyClasses: {
      type: [Object, String],
      description: "Modal Body css classes",
    },
    footerClasses: {
      type: [Object, String],
      description: "Modal Footer css classes",
    },
    animationDuration: {
      type: Number,
      default: 500,
      description: "Modal transition duration",
    },
  },
  methods: {
    closeModal() {
      //this.$emit("update:show", false);
      this.$emit("closeModal");
    },
    sendEmail() {
      http
        .put(`member/findpwd`, this.user)
        .then(({ data }) => {
          console.log(data);
          if (data.message == "success") {
            alert("이메일이 성공적으로 보내졌습니다.");
            this.closeModal();
          } else {
            alert("아이디와 이메일을 다시 확인해보세요.");
          }
        })
        .catch(() => {
          alert("아이디와 이메일을 다시 확인해보세요.");
        });
    },
  },
  watch: {
    show(val) {
      let documentClasses = document.body.classList;
      if (val) {
        documentClasses.add("modal-open");
      } else {
        documentClasses.remove("modal-open");
      }
    },
  },
  data() {
    return {
      user: {
        userId: null,
        userEmail: null,
      },
    };
  },
};
</script>
<style scoped>
.modal.show {
  background-color: rgba(0, 0, 0, 0.3);
}
</style>
