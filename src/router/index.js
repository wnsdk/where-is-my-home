import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  // console.log("로그인 처리 전", checkUserInfo, token);

  // 토큰 유효성 체크하기
  if (checkUserInfo != null && token) {
    await store.dispatch("memberStore/getUserInfo", token);
  }

  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 서비스입니다.");
    router.push({ name: "login" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: () => import("@/views/AppMain"),
  },
  {
    path: "/house",
    name: "house",
    component: () => import("@/views/AppHouse"),
    redirect: "/house/search",
    children: [
      {
        path: "search",
        name: "search",
        component: () => import("@/components/house/HouseModalSearch"),
      },
      {
        path: "myhouse",
        name: "myhouse",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/house/HouseModalMyhouse"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },

      {
        path: "view/:articleNo",
        name: "boardview",
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "modify",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleNo",
        name: "boarddelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete"),
      },
    ],
  },
  {
    path: "/qna",
    name: "qna",
    component: () => import("@/views/AppQnA"),
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "qnalist",
        component: () => import("@/components/qna/QnAList"),
      },

      {
        path: "view/:articleNo",
        name: "qnaview",
        component: () => import("@/components/qna/QnAView"),
      },
      {
        path: "write",
        name: "qnawrite",
        component: () => import("@/components/qna/QnAWrite"),
      },
      {
        path: "modify",
        name: "qnamodify",
        component: () => import("@/components/qna/QnAModify"),
      },
      {
        path: "delete/:articleNo",
        name: "qnadelete",
        component: () => import("@/components/qna/QnADelete"),
      },
    ],
  },
  {
    path: "/member",
    name: "member",
    component: () => import("@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/Login"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/user/UserMyPage"),
      },
    ],
  },
  {
    path: "/news",
    name: "news",
    component: () => import("@/views/AppNews"),
    redirect: "/news/list",
    children: [
      {
        path: "list",
        name: "list",
        component: () => import("@/components/news/NewsList"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
