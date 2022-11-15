import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
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
      // {
      //   path: "modify",
      //   name: "boardmodify",
      //   component: () => import("@/components/board/BoardModify"),
      // },
      // {
      //   path: "delete/:articleno",
      //   name: "boarddelete",
      //   component: () => import("@/components/board/BoardDelete"),
      // },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
