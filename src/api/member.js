import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  //서버의 memberController 의 @PostMapping("/login과 연결")
  await api.post(`/member/login`, JSON.stringify(user)).then(success).catch(fail);
  //await console.log("여긴 member.js의 로그인 끝 ")
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/member/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/member/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/member/logout/${userid}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout };
