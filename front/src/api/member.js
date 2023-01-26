import { apiInstance } from "./index.js";
import store from "@/store";

const api = apiInstance();

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
async function checkAuthUser() {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");

  // 토큰 유효성 체크하기
  if (checkUserInfo != null && token)
    await store.dispatch("memberStore/getUserInfo", token);

  return checkToken && checkUserInfo != null;
}

async function login(user, success, fail) {
  await api
    .post(`/member/login`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(userId, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/info/${userId}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/member/refresh`, user).then(success).catch(fail);
}

async function logout(userId, success, fail) {
  await api.get(`/member/logout/${userId}`).then(success).catch(fail);
}

async function join(userId, success, fail) {
  await api.get(`/member`).then(success).catch(fail);
}

async function deleteMember(userId, success, fail) {
  await api.delete(`/member/${userId}`).then(success).catch(fail);
}

export {
  checkAuthUser,
  login,
  findById,
  tokenRegeneration,
  logout,
  join,
  deleteMember,
};
