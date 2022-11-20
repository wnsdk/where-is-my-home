<template>
  <table>
    <tr>
      <th>{{ this.type }}</th>
    </tr>
    <tr v-for="(article, index) in articles" :key="index">
      <td @click="moveDetail(article)">{{ article.subject }}</td>
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
      else
        location.href = `http://localhost:8080/${this.type}/view/${article.articleNo}`;
    },
  },
};
</script>

<style></style>
