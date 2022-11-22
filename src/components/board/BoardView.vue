<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card class="mb-2" no-body>
          <b-card-header>
            <table>
              <tr>
                <td colspan="4">{{ article.subject }}</td>
              </tr>
              <tr>
                <td rowspan="2">
                  <b-avatar
                    variant="primary"
                    v-text="article.userId.charAt(0).toUpperCase()"
                    v-if="article.userImgUrl == null"
                  ></b-avatar>
                  <b-avatar
                    variant="primary"
                    :src="article.userImgUrl"
                    v-if="article.userImgUrl != null"
                    width="500"
                  ></b-avatar>
                </td>
                <td colspan="2">{{ article.userName }}</td>
                <td colspan="2"></td>
              </tr>
              <tr>
                <td>{{ article.registerTime.substr(0, 16) }}</td>
                <td>조회 {{ article.hit }}</td>
              </tr>
            </table>
          </b-card-header>
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
          <b-card-footer>
            <board-comment :articleNo="article.articleNo"></board-comment>
          </b-card-footer>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle } from "@/api/board";
import BoardComment from "@/components/board/BoardComment.vue";

export default {
  data() {
    return {
      article: {},
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  components: {
    BoardComment,
  },
  created() {
    getArticle(
      this.$route.params.articleNo,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      }
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardlist" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleNo: this.article.articleNo },
      });
      //this.$router.push({ path: `/board/modify/${this.article.articleNo}` });
    },
    removeArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleNo, () => {
          this.$router.push({ name: "boardlist" });
        });
      }
    },
  },
};
</script>

<style></style>
