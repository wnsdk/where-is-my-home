<template>
  <div>
    <ul v-for="(comment, index) in comments" :key="index">
      <li>
        <table style="text-align: left">
          <tr>
            <td rowspan="3">
              <b-avatar
                variant="primary"
                v-text="comment.userId.charAt(0).toUpperCase()"
                v-if="comment.userImgUrl == null"
              ></b-avatar>
              <b-avatar
                variant="primary"
                :src="comment.userImgUrl"
                v-if="comment.userImgUrl != null"
                width="500"
              ></b-avatar>
            </td>
            <td>{{ comment.userName }}</td>
            <td rowspan="3">
              <img
                src="@/assets/img/icons/common/icon-show-more.png"
                width="20px"
                @click="isShowMore = true"
              />
            </td>
          </tr>
          <tr>
            <td>{{ comment.comment }}</td>
          </tr>
          <tr>
            <td>{{ comment.registerTime.substr(0, 16) }}</td>
          </tr>
        </table>
        <div v-if="isShowMore">
          <div v-if="userInfo.userId == comment.userId">
            <div>수정</div>
            <div @click="deleteComment(comment.commentNo)">삭제</div>
          </div>
          <div v-else>
            <div>신고</div>
          </div>
        </div>
      </li>
    </ul>
    <form>
      <textarea
        class="form-control form-control-alternative"
        rows="3"
        placeholder="댓글을 남겨보세요"
        v-model="comment"
      ></textarea>
      <div @click="writeComment">등록</div>
    </form>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";

export default {
  name: "BoardComment",
  props: {
    articleNo: Number,
  },
  data() {
    return {
      isShowMore: false,
      comment: "",
      comments: [],
    };
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  mounted() {
    this.listComment();
  },
  methods: {
    listComment() {
      setTimeout(() => {
        http.get(`/comment/${this.articleNo}`).then(({ data }) => {
          console.log(data);
          this.comments = data;
        });
      }, 100);
    },
    writeComment() {
      http
        .post(`/comment`, {
          articleNo: this.articleNo,
          comment: this.comment,
          userId: this.userInfo.userId,
        })
        .then(() => {
          this.listComment();
          this.comment = "";
        });
    },
    deleteComment(commentNo) {
      http.delete(`/comment/${commentNo}`).then(() => {
        this.listComment();
      });
    },
  },
};
</script>

<style>
ul {
  list-style: none;
}
</style>
