<template>
  <footer class="footer has-cards">
    <div class="container container-lg">
      <div class="row">
        <div class="col-md-6 mb-5 mb-md-0">
          <div class="card card-lift--hover shadow border-0">
            <router-link to="/landing" title="Landing Page">
              <img
                src="https://static.hubzum.zumst.com/hubzum/2017/04/05/13/1649b01263f240cf8bae3096224aad90.jpg"
                class="card-img"
              />
            </router-link>
          </div>
        </div>
        <div class="col-md-6 mb-5 mb-lg-0">
          <div class="card card-lift--hover shadow border-0">
            <router-link to="/profile" title="Profile Page">
              <img
                src="https://images.chosun.com/resizer/XzBAet4Pt0zZSuDh6Jeg9Qr690g=/616x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/6Y6SF53PVQJ4BUKTA53YMI2PZA.jpg"
                class="card-img"
              />
            </router-link>
          </div>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="row row-grid align-items-center my-md">
        <div class="col-lg-6">
          <h3 class="text-primary font-weight-light mb-2">
            Thank you for visiting us!
          </h3>
          <h4 class="mb-0 font-weight-light">Where is my home</h4>
        </div>
        <div class="col-lg-6 text-lg-center btn-wrapper">
          <a
            target="_blank"
            rel="noopener"
            href="https://www.instagram.com/hellossafy/"
            class="btn btn-neutral btn-icon-only btn-twitter btn-round btn-lg"
            data-toggle="tooltip"
            data-original-title="Follow us"
          >
            <i class="fa fa-instagram"></i>
          </a>
          <a
            target="_blank"
            rel="noopener"
            href="https://ko-kr.facebook.com/hellossafy/"
            class="btn btn-neutral btn-icon-only btn-facebook btn-round btn-lg"
            data-toggle="tooltip"
            data-original-title="Like us"
          >
            <i class="fa fa-facebook-square"></i>
          </a>
          <a
            target="_blank"
            rel="noopener"
            href="https://www.youtube.com/channel/UC_XI3ByFO1uZIIH-g-zJZiw/featured"
            class="btn btn-neutral btn-icon-only btn-dribbble btn-lg btn-round"
            data-toggle="tooltip"
            data-original-title="Follow us"
          >
            <i class="fa fa-youtube"></i>
          </a>
          <a
            target="_blank"
            rel="noopener"
            href="https://github.com/where-is-my-home"
            class="btn btn-neutral btn-icon-only btn-github btn-round btn-lg"
            data-toggle="tooltip"
            data-original-title="Star on Github"
          >
            <i class="fa fa-github"></i>
          </a>
        </div>
      </div>
      <hr />
      <div class="row align-items-center justify-content-md-between">
        <div class="col-md-6">
          <div class="copyright">
            &copy; {{ year }}
            <a
              href=""
              target="_blank"
              rel="noopener"
              style="text-decoration: none"
              >최준아</a
            >
            &
            <a
              href=""
              target="_blank"
              rel="noopener"
              style="text-decoration: none"
              >김민소</a
            >
          </div>
        </div>
        <div class="col-md-6">
          <ul class="nav nav-footer justify-content-end">
            <li class="nav-item">
              <a
                href="https://www.creative-tim.com"
                class="nav-link"
                target="_blank"
                rel="noopener"
                >Creative Tim</a
              >
            </li>
            <li class="nav-item">
              <a
                href="https://www.creative-tim.com/presentation"
                class="nav-link"
                target="_blank"
                rel="noopener"
                >About Us</a
              >
            </li>
            <li class="nav-item">
              <a
                href="http://blog.creative-tim.com"
                class="nav-link"
                target="_blank"
                rel="noopener"
                >Blog</a
              >
            </li>
            <li class="nav-item">
              <a
                href="https://github.com/creativetimofficial/argon-design-system/blob/master/LICENSE.md"
                class="nav-link"
                target="_blank"
                rel="noopener"
                >MIT License</a
              >
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  name: "app-footer",
  data() {
    return {
      year: new Date().getFullYear(),
    };
  },
};
</script>
<style scoped>
i {
  transform: translateX(-6.5px) translateY(-15px);
}
.card-img {
  height: 200px;
  object-fit: cover;
  filter: grayscale(10%);
  opacity: 80%;
}
</style>
