<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right"> </b-col>
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
              class="table table-hover mb-0"
              :items="articles"
              :fields="fields"
              @row-clicked="viewArticle"
              :per-page="perPage"
              :current-page="currentPage"
            >
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
        { key: "userName", label: "작성자", tdClass: "tdClass" },
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
      this.articles = data;
      this.total = data.length;
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

/* 테이블 템플릿 */
.intro {
  height: 100%;
}

table td,
table th {
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}

.card {
  border-radius: 0.5rem;
}
</style>
