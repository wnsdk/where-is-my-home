<template>
  <div>
    <div id="house-modal">
      <b-col><house-list /></b-col>
    </div>
    <div id="house-modal-detail" v-if="showDetail">
      <b-col> <house-modal-detail></house-modal-detail> </b-col>
    </div>
    <div id="btn-close-detail" v-if="showDetail" @click="SET_SHOW_DETAIL_FALSE">
      <b-col
        ><img
          class="select-box__icon"
          src="http://cdn.onlinewebfonts.com/svg/img_295694.svg"
      /></b-col>
    </div>
  </div>
</template>

<script>
import HouseList from "@/components/house/modal/MyhouseList.vue";
import HouseModalDetail from "@/components/house/HouseModalDetail.vue";

import { mapState, mapMutations } from "vuex";

export default {
  name: "HouseModal",
  components: {
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
#btn-close-detail {
  width: 15px;
  height: 50px;
  background-color: rgb(255, 255, 255);
  transform: translateY(40vh);
  border-radius: 0 5px 5px 0;
  position: absolute;
  z-index: 2;
  margin-left: 760px;
}
.select-box__icon {
  position: absolute;
  transform: translateY(20px) rotate(90deg);
  right: 20px;

  width: 10px;
  opacity: 0.3;
  transition: 0.2s ease;
}
</style>
