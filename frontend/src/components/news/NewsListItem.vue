<template>
  <a :href="link" class="page-item">
    <tr>
      <td rowspan="3"><img :src="photo" /></td>
      <th colspan="2" class="text-left">{{ subject }}</th>
    </tr>
    <tr>
      <td colspan="2">{{ detail }}</td>
    </tr>
    <tr>
      <td>{{ writer }}</td>
      <td>{{ date }}</td>
    </tr>
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
* {
  text-align: left;
}
a {
  color: black;
  text-decoration: none;
}
/* 이미지 */
tr:nth-child(1) > td {
  width: 150px;
}
/* 제목 */
tr:nth-child(1) > th {
  width: 850px;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 18px;
  padding: 10px;
}
/* 내용 */
tr:nth-child(2) > td {
  width: 850px;
  font-size: 15px;
  padding: 10px;
  text-overflow: ellipsis;
  white-space: nowrap;
}
/* 기타 */
tr:nth-child(3) > td {
  font-size: 12px;
  padding: 10px;
}
tr:nth-child(3) > td:first-child {
  width: 100px;
}
</style>
