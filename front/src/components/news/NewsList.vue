<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-table-simple
          responsive
          :per-page="perPage"
          :current-page="currentPage"
        >
          <tbody>
            <!-- 하위 component인 BoardListItem에 데이터 전달(props) -->
            <news-list-item
              v-for="(article, index) in articles"
              :key="index"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
      </b-col>
    </b-row>
    <!-- <b-row>
      <b-col>
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
    </b-row> -->
  </b-container>
</template>

<script>
import http from "@/api/http";
// import Pagination from "@/components/Pagination.vue";
import NewsListItem from "@/components/news/NewsListItem";

export default {
  name: "NewsList",
  bodyClass: "profile-page",
  components: {
    // Pagination,
    NewsListItem,
  },
  data() {
    return {
      articles: [],
      fields: [
        { key: "photo", label: "사진", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "detail", label: "미리보기", tdClass: "tdClass" },
        { key: "writer", label: "신문사", tdClass: "tdClass" },
        { key: "date", label: "작성시간", tdClass: "tdClass" },
      ],
      currentPage: 1,
      perPage: 5,
      pageCount: 0,
      total: 0,
      withText: true,
      noArrows: false,
    };
  },
  created() {
    http.get(`/news?limit=MAX`).then(({ data }) => {
      this.articles = data;
      this.total = data.length;
    });
  },
  methods: {
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
