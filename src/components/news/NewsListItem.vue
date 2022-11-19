<template>
  <a :href="link">
    <b-tr>
      <b-td rowspan="3"><a :href="link"></a><img :src="photo" /></b-td>
      <b-th colspan="2" class="text-left">{{ subject }}</b-th>
    </b-tr>
    <b-tr>
      <b-td colspan="2">{{ detail }}</b-td>
    </b-tr>
    <b-tr>
      <b-td>{{ writer }}</b-td>
      <b-td>{{ date }}</b-td>
    </b-tr>
  </a>
</template>

<script>
export default {
  name: "NewsListItem",
  props: {
    photo: String,
    subject: String,
    link: String,
    detail: String,
    writer: String,
    date: String,
  },
  filters: {
    dateFormat() {},
  },
};
</script>

<style scoped>
a {
  color: black;
  text-decoration: none;
}
</style>
