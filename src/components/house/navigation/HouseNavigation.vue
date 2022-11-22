<template>
  <base-nav
    v-model="menu"
    effect="dark"
    expand
    title="아파트 매매 정보"
    id="house-navbar"
    @click="goSearch"
  >
    <div class="row" slot="content-header" slot-scope="{ closeMenu }">
      <div class="col-6 collapse-close">
        <close-button @click="closeMenu" :target="`navbar-${menu.type}`">
        </close-button>
      </div>
    </div>
    <component :is="menu.menuComponent"></component>
  </base-nav>
</template>
<script>
import BaseNav from "@/components/Argons/BaseNav";
import CloseButton from "@/components/Argons/CloseButton";
import Menu from "@/components/house/navigation/Menu.vue";

export default {
  components: {
    BaseNav,
    CloseButton,
    Menu,
  },
  data() {
    return {
      menu: { type: "info", menuComponent: Menu },
      // menus: [
      //   { type: "default", menuComponent: Menu1 },
      //   { type: "primary", menuComponent: Menu2 },
      //   { type: "success", menuComponent: Menu3 },
      //   { type: "danger", menuComponent: Menu4 },
      //   { type: "warning", menuComponent: Menu5 },
      //   { type: "info", menuComponent: Menu6 },
      // ],
    };
  },
  methods: {
    goSearch() {
      this.$router.push("house/search");
    },
  },
};
</script>
<style></style>
