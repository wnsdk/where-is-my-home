<template>
  <div>
    <ul v-for="(comment, index) in comments" :key="index">
      <li>
        <table>
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
            <td rowspan="3" style="overflow: visible">
              <base-dropdown tag="li" class="nav-item">
                <div
                  slot="title"
                  href="#"
                  class="m-2 link"
                  data-toggle="dropdown"
                  role="button"
                >
                  <img
                    src="@/assets/img/icons/common/icon-show-more.png"
                    width="20px"
                  />
                </div>
                <div v-if="userInfo.userId == comment.userId">
                  <div class="dropdown-item">
                    <div @click="showUpdateComment(index)">수정</div>
                  </div>
                  <div class="dropdown-item">
                    <div @click="deleteComment(comment.commentNo)">삭제</div>
                  </div>
                </div>
                <div class="dropdown-item" v-else>
                  <div>
                    <div>신고</div>
                  </div>
                </div>
              </base-dropdown>
            </td>
          </tr>
          <tr>
            <td v-text="comment.comment"></td>
          </tr>
          <tr>
            <td>{{ comment.registerTime.substr(0, 16) }}</td>
          </tr>
        </table>
        <!-- 댓글 수정 버튼을 누르면 보이는 영역 -->
        <form :class="comment.commentNo" style="display: none">
          <textarea
            :id="comment.commentNo"
            class="form-control form-control-alternative"
            rows="3"
            placeholder="댓글을 수정해보세요"
            :value="comment.comment"
          ></textarea>
          <div
            @click="updateComment(comment.commentNo, index)"
            id="btn-register"
            style="transform: translateX(840px) translateY(-30px)"
          >
            수정
          </div>
        </form>
      </li>
    </ul>
    <form>
      <textarea
        class="form-control form-control-alternative"
        rows="3"
        placeholder="댓글을 남겨보세요"
        v-model="comment"
      ></textarea>
      <div @click="writeComment" id="btn-register">등록</div>
    </form>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapState } from "vuex";
import BaseDropdown from "@/components/Argons/BaseDropdown";

export default {
  name: "BoardComment",
  props: {
    articleNo: Number,
  },
  components: {
    BaseDropdown,
  },
  data() {
    return {
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
    updateComment(commentNo, i) {
      let comment = document.getElementById(commentNo).value;
      http
        .put(`/comment`, { commentNo: commentNo, comment: comment })
        .then(() => {
          this.listComment();
          let form = document.getElementsByTagName("form")[i];
          form.setAttribute("style", "display: none");
        });
    },
    showUpdateComment(i) {
      let form = document.getElementsByTagName("form")[i];
      form.setAttribute("style", "display: visible");
    },
  },
};
</script>

<style scoped>
ul {
  padding: 0;
  list-style: none;
}
li {
  margin-top: 10px;
  margin-bottom: 10px;
}
td {
  text-align: left;
}
ul {
  text-align: left;
  padding-bottom: 10px;
  border-bottom: solid 1px rgb(228, 228, 228);
}
table > tr:first-child > td:first-child {
  vertical-align: top;
  padding: 5px 12px 0 0;
}
/* 점 3개 아이콘 */
table > tr:first-child > td:last-child {
  vertical-align: top;
}
/* 댓글 내용 */
table > tr:nth-child(2) > td {
  white-space: pre-wrap !important;
  width: 100%;
}

/* 댓글 작성 시각 */
table > tr:nth-child(3) > td {
  font-size: 12px;
  color: rgb(156, 156, 156);
}

#btn-register {
  transform: translateX(400px) translateY(-30px);
  font-size: 13px;
  color: rgb(122, 122, 122);
}
</style>
