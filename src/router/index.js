import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

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
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "modify",
        name: "boardmodify",
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleNo",
        name: "boarddelete",
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
        //component: () => import("@/components/user/UserRegister"),
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
        //beforeEnter: onlyAuthUser,
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
