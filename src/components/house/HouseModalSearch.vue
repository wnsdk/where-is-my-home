<template>
  <div>
    <div id="house-modal">
      <b-col>
        <house-search-bar id="house-search-bar" />
        <house-list />
      </b-col>
    </div>
    <div id="house-modal-detail" v-if="showDetail">
      <b-col> <house-modal-detail></house-modal-detail> </b-col>
    </div>
  </div>
</template>

<script>
import HouseSearchBar from "@/components/house/modal/HouseSearchBar.vue";
import HouseList from "@/components/house/modal/HouseList.vue";
import HouseModalDetail from "@/components/house/HouseModalDetail.vue";

import { mapState, mapMutations } from "vuex";

export default {
  name: "HouseModal",
  components: {
    HouseSearchBar,
    HouseList,
    HouseModalDetail,
  },
  computed: {
    ...mapState("houseStore", ["showDetail"]),
  },
  methods: {
    ...mapMutations("houseStore", ["SET_SHOW_DETAIL_FALSE"]),
  },
  created() {
    this.SET_SHOW_DETAIL_FALSE();
  },
};
</script>

<style scoped>
#house-search-bar {
  padding-top: 15px;
}
#house-modal {
  width: 380px;
  height: 88vh;
  background-color: white;
  position: absolute;
  z-index: 3;
  box-shadow: 5px 0 10px rgb(189, 189, 189);
}
#house-modal-detail {
  width: 380px;
  height: 88vh;
  background-color: white;
  position: absolute;
  z-index: 2;
  margin-left: 380px;
  box-shadow: 5px 0 20px rgb(189, 189, 189);
}
</style>
