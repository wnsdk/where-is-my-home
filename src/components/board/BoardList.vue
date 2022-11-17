<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
      <b-col>
        <div class="col-md-10 offset-2">
          <form class="d-flex" id="form-search" action="">
            <b-form-select
              v-model="searchOption"
              class="form-select form-select-sm ms-10 me-1 w-50"
              name="key"
              aria-label="검색조건"
            >
              <option value="" selected>검색조건</option>
              <option value="subject">제목</option>
              <option value="userid">아이디</option>
            </b-form-select>
            <div class="input-group input-group-sm">
              <input
                v-model="word"
                type="text"
                class="form-control"
                name="searchBar"
                placeholder="검색어..."
              />
              <b-button
                id="btn-search"
                class="btn btn-dark"
                @click.self.prevent="search()"
              >
                검색
              </b-button>
            </div>
          </form>
        </div>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          striped
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
          :per-page="perPage"
          :current-page="currentPage"
        >
        </b-table>
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
  name: "BoardList",
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
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "registerTime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
      word: "",
      searchOption: "",
      currentPage: 1,
      perPage: 10,
      pageCount: 0,
      total: 0,
      withText: true,
      noArrows: false,
    };
  },
  created() {
    http.get(`board`).then(({ data }) => {
      console.log(data);
      this.articles = data;
      this.total = data.length;
      console.log(data.length);
    });
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleNo: article.articleNo },
      });
    },
    search() {
      http
        .get(`board?key=${this.searchOption}&word=${this.word}`)
        .then(({ data }) => {
          this.articles = data;
          this.total = data.length;
        });
    },
    pgfunc(data) {
      this.currentPage = data;
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
