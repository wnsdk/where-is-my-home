<template>
  <table>
    <tr v-for="(article, index) in articles" :key="index">
      <td class="card-content" @click="moveDetail(article)">
        {{ article.subject }}
      </td>
    </tr>
  </table>
</template>

<script>
import http from "@/api/http";

export default {
  name: "MainArticles",
  props: {
    type: String,
  },
  data() {
    return {
      articles: [],
    };
  },
  mounted() {
    http.get(`/${this.type}?limit=5`).then(({ data }) => {
      this.articles = data;
    });
  },
  methods: {
    moveDetail(article) {
      if (this.type == "news") location.href = article.link;
      else if (this.type == "board")
        this.$router.push(`${this.type}/view/${article.articleNo}`);
    },
  },
};
</script>

<style scoped>
/* 카드 내용 */
.card-content {
  text-align: left;
  font-family: "Noto Sans KR", sans-serif;
  color: black;
  width: 210px;
  height: 30px;

  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: block;
}
</style>
