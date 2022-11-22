<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col>
        <div class="input-group input-group-sm">
          <b-button variant="outline-primary" @click="moveWrite()"
            >글쓰기</b-button
          >
        </div>
        <div class="col-md-10 offset-2">
          <form class="d-flex" id="form-search" action="">
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
              hover
              :items="articles"
              :fields="fields"
              @row-clicked="showAnswer"
              :per-page="perPage"
              :current-page="currentPage"
            >
              <template #cell(answer)="data">
                {{ data.item.answer | answerFlag }}
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
                <b-card>
                  <b-row class="mb-2">
                    <b-col sm="3" class="text-sm-right"><b>질문</b></b-col>
                    <b-col>{{ data.item.content }}</b-col>
                  </b-row>
                  <b-row class="mb-2">
                    <b-col sm="3" class="text-sm-right"><b>답변</b></b-col>
                    <b-col>{{ data.item.answer }}</b-col>
                  </b-row>
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

export default {
  name: "QnAList",
  bodyClass: "profile-page",
  components: {
    Pagination,
  },
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleNo", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userName", label: "작성자", tdClass: "tdClass" },
        { key: "registerTime", label: "작성일", tdClass: "tdClass" },
        { key: "answer", label: "답변여부", tdClass: "tdClass" },
        { key: "details", label: "상세", tdClass: "tdClass" },
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
    http.get(`qna`).then(({ data }) => {
      console.log(data);
      this.articles = data;
      this.total = data.length;
      console.log(data.length);
    });
  },
  filters: {
    answerFlag(answer) {
      if (answer != "" && answer != null) return "답변 완료";
      return "답변 대기";
    },
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnawrite" });
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
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
