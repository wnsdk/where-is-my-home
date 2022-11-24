<template>
  <b-container class="bv-example-row mt-3 body-container">
    <b-row class="mb-1">
      <b-col class="d-flex">
        <div class="input-group input-group-sm">
          <b-button variant="outline-primary" @click="moveWrite()"
            >글쓰기</b-button
          >
        </div>
        <div>
          <form class="d-flex" id="form-search" style="width: 500px">
            <select
              class="form-select form-select-sm col"
              style="width: 200px"
              v-model="searchOption"
              name="key"
              aria-label="검색조건"
            >
              <option value="" selected>검색조건</option>
              <option value="subject">제목</option>
              <option value="userid">아이디</option>
            </select>

            <div class="input-group input-group-sm" style="width: 400px">
              <input
                v-model="word"
                type="text"
                class="form-control col-12"
                name="searchBar"
                placeholder="검색어..."
              />
              <div class="input-group-append">
                <button class="btn btn-dark" @click.self.prevent="search()">
                  검색
                </button>
              </div>
            </div>
          </form>
        </div>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <section class="intro">
          <div class="table-responsive">
            <b-table
              :items="articles"
              :fields="fields"
              @row-clicked="showAnswer"
              :per-page="perPage"
              :current-page="currentPage"
            >
              <template #cell(answer)="data">
                <div
                  v-if="data.item.answer == null || data.item.answer == ''"
                  style="color: red"
                >
                  답변 대기
                </div>
                <div v-else style="color: #2c9bd6">답변 완료</div>
              </template>

              <template #cell(details)="data"
                ><b-button
                  size="sm"
                  class="mr-2"
                  @click="data.toggleDetails"
                  @change="data.toggleDetails"
                >
                  {{ data.detailsShowing ? "Hide" : "Show" }}
                </b-button>
              </template>
              <template #row-details="data">
                <b-card style="overflow: visible">
                  <table>
                    <tr class="detail">
                      <th class="detail"><b>질문</b></th>
                      <td class="detail" v-html="data.item.content"></td>
                      <td class="detail" style="overflow: visible">
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
                          <div class="dropdown-item">
                            <div
                              v-if="
                                userInfo != null && userInfo.userRole == 'admin'
                              "
                            >
                              <div v-if="data.item.answer == null">
                                <a
                                  v-b-toggle
                                  href="#example-collapse"
                                  @click.prevent
                                  >답변달기</a
                                >
                              </div>
                              <div
                                v-else
                                @click="deleteAnswer(data.item.articleNo)"
                              >
                                답변삭제
                              </div>
                            </div>
                            <div
                              v-if="
                                userInfo != null &&
                                userInfo.userId == data.item.userId
                              "
                            >
                              <div
                                @click="moveModifyArticle(data.item.articleNo)"
                              >
                                수정하기
                              </div>
                              <div @click="removeArticle(data.item.articleNo)">
                                삭제하기
                              </div>
                            </div>
                            <div v-else>
                              <div>신고하기</div>
                            </div>
                          </div>
                        </base-dropdown>
                      </td>
                    </tr>
                    <tr v-if="data.item.answer">
                      <th class="detail"><b>답변</b></th>
                      <td
                        class="detail"
                        colspan="2"
                        v-text="data.item.answer"
                      ></td>
                    </tr>

                    <!-- 답변달기 버튼을 누르면 보이는 영역 -->
                    <tr>
                      <td colspan="3" style="padding: 0">
                        <b-collapse
                          id="example-collapse"
                          :class="data.item.articleNo"
                          style="display: none"
                        >
                          <textarea
                            :id="data.item.articleNo"
                            class="form-control form-control-alternative"
                            rows="3"
                            placeholder="답변을 달아주세요"
                          ></textarea>
                          <div
                            @click="addAnswer(data.item.articleNo)"
                            id="btn-register"
                          >
                            등록
                          </div>
                        </b-collapse>
                      </td>
                    </tr>
                  </table>
                </b-card>
              </template>

              <template #cell(registerTime)="data">
                {{ data.item.registerTime.substr(0, 10) }}
              </template>
            </b-table>
          </div>
        </section>
      </b-col>
    </b-row>
    <b-row>
      <b-col class="d-flex justify-content-center">
        <pagination
          :pageCount="pageCount"
          :perPage="perPage"
          :total="total"
          :value="currentPage"
          :withText="withText"
          :noArrows="noArrows"
          @input="pgfunc"
        >
        </pagination>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http";
import Pagination from "@/components/Pagination.vue";
import { deleteArticle } from "@/api/qna";
import { mapState } from "vuex";
import BaseDropdown from "@/components/Argons/BaseDropdown";

export default {
  name: "QnAList",
  bodyClass: "profile-page",
  components: {
    Pagination,
    BaseDropdown,
  },
  data() {
    return {
      articles: [],
      fields: [
        {
          key: "articleNo",
          label: "글번호",
          tdClass: "tdClass",
          thStyle: { width: "8%" },
        },
        {
          key: "subject",
          label: "제목",
          tdClass: "tdSubject",
          thStyle: { width: "50%" },
        },
        {
          key: "userName",
          label: "작성자",
          tdClass: "tdClass",
          thStyle: { width: "7%" },
        },
        {
          key: "registerTime",
          label: "작성일",
          tdClass: "tdClass",
          thStyle: { width: "15%" },
        },
        {
          key: "answer",
          label: "답변여부",
          tdClass: "tdClass",
          thStyle: { width: "10%" },
        },
        {
          key: "details",
          label: "상세",
          tdClass: "tdClass",
          thStyle: { width: "10%" },
        },
      ],
      word: "",
      searchOption: "",
      currentPage: 1,
      perPage: 10,
      pageCount: 0,
      total: 0,
      withText: true,
      noArrows: false,
      answer: false,
    };
  },
  created() {
    this.getArticles();
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnawrite" });
    },
    getArticles() {
      http.get(`qna`).then(({ data }) => {
        this.articles = data;
        this.total = data.length;
      });
    },
    viewArticle(article) {
      this.$router.push({
        name: "qnaview",
        params: { articleNo: article.articleNo },
      });
    },
    search() {
      http
        .get(`qna?key=${this.searchOption}&word=${this.word}`)
        .then(({ data }) => {
          this.articles = data;
          this.total = data.length;
        });
    },
    pgfunc(data) {
      this.currentPage = data;
    },
    showAnswer() {
      if (this.answer) this.answer = false;
      else this.answer = true;
    },
    moveModifyArticle(articleNo) {
      this.$router.replace({
        name: "qnamodify",
        params: { articleNo: articleNo },
      });
      //this.$router.push({ path: `/qna/modify/${this.article.articleNo}` });
    },
    removeArticle(articleNo) {
      if (confirm("질문을 삭제하시겠습니까?")) {
        deleteArticle(articleNo, () => {
          this.getArticles();
        });
      }
    },
    addAnswer(articleNo) {
      let answer = document.getElementById(articleNo).value;
      http
        .put(`/qna/answer`, { articleNo: articleNo, answer: answer })
        .then(() => {
          this.getArticles();
        });
    },
    deleteAnswer(articleNo) {
      http.get(`/qna/answer?articleNo=${articleNo}`).then(() => {
        this.getArticles();
      });
    },
  },
};
</script>

<style scope>
/* * {
  overflow: hidden;
} */
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
.body-container {
  width: 1000px;
}
tr.detail {
  width: 100%;
}
b-card table {
  border-style: none;
}
th.detail {
  width: 70px;
}
td.detail:nth-child(2) {
  text-align: left;
  width: 800px !important;
  white-space: pre-wrap !important;
  word-break: normal;
}
td.detail:nth-child(3) {
  font-size: 14px;

  color: rgb(146, 146, 146);
  padding-left: 0;
}
td.detail:nth-child(3) > ul {
  list-style: none;
  padding-left: 0;
}
td.detail:nth-child(3) > ul > li:hover {
  background-color: rgb(180, 180, 180);
  color: white;
}
#btn-register {
  transform: translateX(400px) translateY(-30px);
  font-size: 13px;
  color: rgb(122, 122, 122);
}
</style>
