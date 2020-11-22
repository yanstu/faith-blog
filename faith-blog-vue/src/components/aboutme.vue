<template>
  <div class="friend">
    <el-card class="box-card">
      <el-avatar
        shape="square"
        :size="230"
        style="max-width:100%"
        :fit="fit"
        src="https://7.dusays.com/2020/11/21/7bb338f162ae5.jpg"
      ></el-avatar>
      <div class="divinfo info">
        <span class="jinrishici-sentence">正在加载今日诗词....</span>
      </div>
      <div class="divinfo">
        <el-col
          class="info"
          :span="6"
        >
          <el-link
            href="/atom.xml"
            type="danger"
            target="_blank"
          ><i class="fa fa-rss fa-lg"></i></el-link>
        </el-col>
        <el-col
          class="info"
          :span="6"
        >
          <el-link
            href="mailto:luckyxbot@gmail.com"
            type="success"
            target="_blank"
          ><i class="fa fa-envelope fa-lg"></i></el-link>
        </el-col>
        <el-col
          class="info"
          :span="6"
        >
          <el-link
            href="https://github.com/yanstu"
            type="info"
            target="_blank"
          ><i class="fa fa-github fa-lg"></i></el-link>
        </el-col>
        <el-col
          class="info"
          :span="6"
        >
          <el-link
            href="https://pan.yanstu.cn"
            type="primary"
            target="_blank"
          ><i class="fa fa-cloud fa-lg"></i></el-link>
        </el-col>
      </div>

    </el-card>
  </div>
</template>

<script>
export default {
  name: "aboutme",
  mounted() {
    var element = document.createElement("script");
    element.src = "https://sdk.jinrishici.com/v2/browser/jinrishici.js";
    document.body.appendChild(element);
  },
};
</script>

<style scoped>
.box-card .item a {
  text-decoration: none;
}
.info {
  text-align: center;
}
.divinfo {
  height: 35px;
  line-height: 35px;
}
.jinrishici-sentence {
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>