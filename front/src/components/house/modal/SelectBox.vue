<template>
  <div class="select-box">
    <div
      class="select-box__current"
      tabindex="1"
      @focus="ifFocus()"
      @blur="ifBlur()"
    >
      <div
        class="select-box__value"
        v-for="(item, index) in items"
        :key="index"
      >
        <input
          class="select-box__input"
          type="radio"
          :id="item.value"
          :value="item.value"
          :name="name"
          @click="onEmit(item.value)"
        />
        <p class="select-box__input-text">{{ item.text }}</p>
      </div>

      <img
        class="select-box__icon"
        src="http://cdn.onlinewebfonts.com/svg/img_295694.svg"
        alt="Arrow Icon"
        aria-hidden="true"
      />
    </div>
    <ul class="select-box__list">
      <li v-for="(item, index) in items" :key="index">
        <label
          class="select-box__option"
          :for="item.value"
          aria-hidden="aria-hidden"
          >{{ item.text }}</label
        >
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "SelectBox",
  props: {
    name: String,
    items: Array,
  },
  mounted() {
    // select 박스 첫 번째꺼 checked하기
    document.getElementsByName(this.name)[0].setAttribute("checked", "checked");
    this.ifBlur();
  },
  methods: {
    // 셀렉트 박스가 선택되면 목록 보이기
    ifFocus() {
      let list = document.getElementsByClassName("select-box__list");
      for (let element of list) {
        element.style.display = "block";
      }
    },
    ifBlur() {
      setTimeout(() => {
        let list = document.getElementsByClassName("select-box__list");
        for (let element of list) {
          console.log(element);
          element.style.display = "none";
        }
      }, 500);
    },
    onEmit(code) {
      if (this.name == "sido") this.$emit("gugunList", code);
      else if (this.name == "gugun") this.$emit("searchApt", code);
    },
  },
};
// .select-box__current:focus 일 때
// 모든 .select-box__list 는 보여야됨

// .select-box__current:not(:focus) 일 때
</script>

<style scoped>
.select-box {
  position: relative;
  display: block;
  width: 200px;
  margin: 0 auto;
  color: #60666d;
}
.select-box__current {
  position: relative;
  max-height: 50px;
  box-shadow: 0 15px 30px -10px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  outline: none;
}

.select-box__current:focus + .select-box__list {
  opacity: 1;
  -webkit-animation-name: none;
  animation-name: none;
}
.select-box__current:focus + .select-box__list .select-box__option {
  cursor: pointer;
}
.select-box__current:focus .select-box__icon {
  transform: translateY(-30px) translateX(2px) rotate(180deg);
}
.select-box__icon {
  position: absolute;
  transform: translateY(-30px) translateX(2px);
  right: 10px;

  width: 15px;
  opacity: 0.3;
  transition: 0.2s ease;
}
.select-box__value {
  display: flex;
}
.select-box__input {
  display: none;
}
.select-box__input:checked + .select-box__input-text {
  display: block;
}
.select-box__input-text {
  display: none;
  width: 100%;
  margin: 0;
  padding: 10px;
  background-color: #fff;
}

.select-box__list {
  z-index: 9999;
  position: absolute;
  width: 100%;
  height: 300px;
  overflow: auto;
  padding: 0;
  list-style: none;
  opacity: 0;
  -webkit-animation-name: HideList;
  animation-name: HideList;
  -webkit-animation-duration: 0.5s;
  animation-duration: 0.5s;
  -webkit-animation-delay: 0.5s;
  animation-delay: 0.5s;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
  -webkit-animation-timing-function: step-start;
  animation-timing-function: step-start;
  box-shadow: 0 15px 30px -10px rgba(0, 0, 0, 0.1);
}
.select-box__option {
  display: block;
  padding: 5px;
  margin: 0;
  background-color: #fff;
}
.select-box__option:hover,
.select-box__option:focus {
  color: #546c84;
  background-color: #fbfbfb;
}
</style>
