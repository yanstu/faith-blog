<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="d-flex _index"
      mode="horizontal"
      router="true"
      type="flex"
    >
      <el-menu-item class="el-menu-demo">Faithx's Blog</el-menu-item>
      <el-menu-item class="wapnone" index="/home"><i class="el-icon-s-home"></i>{{$t("header.home")}}</el-menu-item>
      <el-menu-item class="wapnone" index="/archive"><i class="el-icon-folder-opened"></i>{{$t("header.archive")}}</el-menu-item>
      <el-menu-item class="wapnone" index="/about"><i class="el-icon-location-information"></i>{{$t("header.about")}}</el-menu-item>
      <el-submenu
        class="ml-auto"
        index="/"
      >
        <template slot="title">
          <i class="el-icon-user-solid"></i>
          {{ user.nickname }}
          <!-- {{$t("header.login")}} -->
        </template>
        <el-menu-item
          v-if="!hasLogin"
          index="/login"
        >{{ $t('header.login') }}</el-menu-item>
        <el-menu-item
          v-if="!hasLogin"
          index="/login"
        >{{ $t('header.register') }}</el-menu-item>
        <el-menu-item
          v-if="hasLogin"
          index="/blog/add"
        >{{ $t('header.articles') }}</el-menu-item>
        <el-menu-item
          v-if="hasLogin"
          @click="logout"
        >{{ $t('header.logout') }}</el-menu-item>
      </el-submenu>

      <el-submenu>
        <template slot="title">{{$t("header.language")}}</template>
        <el-menu-item @click="toggleLang('zh')">{{$t("header.chinaese")}}</el-menu-item>
        <el-menu-item @click="toggleLang('en')">{{$t("header.english")}}</el-menu-item>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
/**
 * 网站头部组件
 * 接口地址：
 *       - 退出登录 http://127.0.0.1:8080/logout
 */

export default {
  data() {
    return {
      activeIndex: "/home",
      hasLogin: false,
      user: {
        nickname: "请先登录",
      },
    };
  },
  methods: {
    toggleLang(lang) {
      if (lang === "zh") {
        localStorage.setItem("locale", "zh");
        this.$i18n.locale = localStorage.getItem("locale");
        this.$message({
          message: "切换为中文！",
          type: "success",
        });
      } else if (lang === "en") {
        localStorage.setItem("locale", "en");
        this.$i18n.locale = localStorage.getItem("locale");
        this.$message({
          message: "Switch to English!",
          type: "success",
        });
      }
    },

    logout() {
      this.$axios
        .get("/logout", {
          headers: {
            Authorization: localStorage.getItem("token"),
          },
        })
        .then((res) => {
          this.$store.commit("REMOVE_INFO");
          //跳转回login页面
          window.location.href = "/login";
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
  created() {
    if (this.$store.getters.getUser.username) {
      this.user.nickname = this.$store.getters.getUser.nickname;
      this.hasLogin = true;
    }
  },
};
</script>

<style scoped>
i {
  margin-top: -4px;
}
</style>