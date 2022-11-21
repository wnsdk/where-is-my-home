<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="subject-group" label-for="subject">
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <editor
          api-key="no-api-key"
          :init="{
            height: 500,
            menubar: false,
            plugins: [
              'advlist autolink lists link image charmap print preview anchor',
              'searchreplace visualblocks code fullscreen',
              'insertdatetime media table paste code help wordcount',
            ],
            toolbar:
              'undo redo | formatselect | bold italic backcolor | \
           alignleft aligncenter alignright alignjustify | \
           bullist numlist outdent indent | removeformat | help',
          }"
          v-model="article.content"
        />

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/api/http";
import Editor from "@tinymce/tinymce-vue";
import { mapState } from "vuex";

export default {
  name: "QnAInputItem",
  components: {
    editor: Editor,
  },
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
      },
      isuserId: false,
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      http.get(`/qna/${this.$route.params.articleNo}`).then(({ data }) => {
        this.article = data;
      });
      this.isuserId = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleNo = 0;
      this.article.subject = "";
      this.article.content = "";
      this.moveList();
    },
    registArticle() {
      http
        .post(`/qna`, {
          userId: this.userInfo.userId,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then((response) => {
          console.log(response);
          this.moveList();
        });
    },
    modifyArticle() {
      http
        .put(`/qna`, {
          articleNo: this.article.articleNo,
          userId: this.userInfo.userId,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then((response) => {
          console.log(response);
          // 현재 route를 /list로 변경.
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style></style>
