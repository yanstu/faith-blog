<template>
  <div>
    <el-menu
      :default-active="activeIndex"
      class="d-flex _index"
      mode="horizontal"
      router="true"
      type="flex"
    >
      <el-menu-item class="el-menu-demo">
        <el-link
          href="/"
          :underline="false"
        >Faithx's Blog</el-link>
      </el-menu-item>
      <el-menu-item
        class="wapnone"
        index="/home"
      ><i class="el-icon-s-home"></i>{{$t("header.home")}}</el-menu-item>
      <el-menu-item
        class="wapnone"
        index="/archive"
      ><i class="el-icon-folder-opened"></i>{{$t("header.archive")}}</el-menu-item>
      <el-menu-item
        class="wapnone"
        index="/about"
      ><i class="el-icon-location-information"></i>{{$t("header.about")}}</el-menu-item>
      <el-menu-item
        class="drawerBtn"
        @click="drawer = true"
      ><i class="fa fa-th-list"></i></el-menu-item>

      <el-submenu
        class="ml-auto wapnone"
        index="/"
      >
        <template slot="title">
          <i class='el-icon-user-solid'></i>
          {{ user.nickname }}
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

      <el-submenu class="wapnone">
        <template slot="title">{{$t("header.language")}}</template>
        <el-menu-item @click="toggleLang('zh')">{{$t("header.chinaese")}}</el-menu-item>
        <el-menu-item @click="toggleLang('en')">{{$t("header.english")}}</el-menu-item>
      </el-submenu>
    </el-menu>

    <!-- 侧边栏开始 -->

    <el-drawer
      size="40%"
      :visible.sync="drawer"
      :with-header="false"
      direction="ltr"
    >

      <el-menu
        default-active="1"
        class="el-menu-vertical-demo"
        router="true"
        active-text-color="#409EFF"
      >
        <el-menu-item index="/home">
          <i class="el-icon-s-home"></i>
          <el-link :underline="false">{{$t("header.home")}} </el-link>
        </el-menu-item>
        <el-menu-item index="/archive">
          <i class="el-icon-folder-opened"></i>
          <el-link :underline="false">{{$t("header.archive")}} </el-link>
        </el-menu-item>
        <el-menu-item index="/about">
          <i class="el-icon-location-information"></i>
          <el-link :underline="false">{{$t("header.about")}} </el-link>
        </el-menu-item>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-user-solid"></i>
            <span>{{ user.nickname }}</span>
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
        <el-submenu
          index="2"
          style="margin-left:6px"
        >
          <template slot="title">
            <i class="fa fa-font"></i>
            <span style="margin-left:10px">{{$t("header.language")}}</span>
          </template>
          <el-menu-item @click="toggleLang('zh')">{{$t("header.chinaese")}}</el-menu-item>
          <el-menu-item @click="toggleLang('en')">{{$t("header.english")}}</el-menu-item>
        </el-submenu>
      </el-menu>

    </el-drawer>

    <!-- 侧边栏结束 -->

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
      temp: "",
      drawer: false,
      activeIndex: "/home",
      hasLogin: false,
      user: {
        nickname: "",
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
    if (this.$store.getters.getUser) {
      this.user.nickname = this.$store.getters.getUser.nickname;
      this.hasLogin = true;
    } else {
      this.user.nickname = this.$t("header.tis");
    }
  },
};
</script>

<style scoped>
.d-flex i {
  margin-top: -4px;
}
</style>