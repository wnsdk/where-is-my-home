import http from "@/api/http.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시도를 선택하세요" }],
    guguns: [{ value: null, text: "구군을 선택하세요" }],
    houses: [],
    house: null,
    houseDeal: null,
    showDetail: false,
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시도를 선택하세요" }];
    },
    CLEAR_APT_LIST(state) {
      state.houses = [];
      state.house = null;
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구군을 선택하세요" }];
    },
    CLEAR_DETAIL_HOUSE(state) {
      state.house = null;
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      // console.log("Mutations", house);
      state.house = house;
      console.log("HouseInfo 값이 채워짐");
      console.log(state.houseDeal);
    },
    SET_HOUSEDEAL(state, houseDeal) {
      state.houseDeal = houseDeal;
      console.log("HouseDeal 값이 채워짐");
      console.log(state.houseDeal);
    },
    SET_SHOW_DETAIL_FALSE(state) {
      state.showDetail = false;
    },
    SET_SHOW_DETAIL_TRUE(state) {
      state.showDetail = true;
    },
  },
  actions: {
    getSido({ commit }) {
      http
        .get(`/house/sido`)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/house/gugun`, { params })
        .then(({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseList({ commit }, gugunCode) {
      http
        .get(`house/gugunAptList/${gugunCode}`)
        .then(({ data }) => {
          commit("SET_HOUSE_LIST", data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async detailHouse({ commit }, house) {
      // 나중에 house.일련번호를 이용하여 API 호출
      // console.log(commit, house);

      await commit("SET_DETAIL_HOUSE", house);
      await http.get(`house/AptDealList/${house.aptCode}`).then(({ data }) => {
        commit("SET_HOUSEDEAL", data);
      });
      await commit("SET_SHOW_DETAIL_TRUE");
    },
    getMyhouse({ commit }, userId) {
      http.get(`myhouse/${userId}`).then(({ data }) => {
        commit("SET_HOUSE_LIST", data);
      });
    },
  },
};

export default houseStore;
