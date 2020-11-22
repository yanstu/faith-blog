<template>
  <div class="f-footer">
    <remote-js src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></remote-js>
    <el-main class="main">
      <el-row>
        <el-col
          :span="24"
          class="weside"
        >
          <div class="item">
            <span id="jinrishici-sentence">正在加载今日诗词....</span>
          </div>
        </el-col>
        <br/>
        <br/>
        <el-col
          :span="24"
          class="weside"
        >
          <div class="item">
            Copyright © 2020 <a href="https://yanstu.cn">faithx</a>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </div>
</template>

<script>
export default {
  name: "footer",
  mounted() {
    var element = document.createElement("script");
    element.src = "https://sdk.jinrishici.com/v2/browser/jinrishici.js";
    document.body.appendChild(element);
  },
};
</script>

<style scoped>
.f-footer {
  padding: 20px;
  text-align: center;
  color: #fff;
  background-color: dimgray;
}
.main {
  width: 70%;
  margin: 0 auto;
  color: #ccc;
}
.out-link {
  text-decoration: none;
  color: #ccc;
}
.out-link:hover {
  color: #ffffff;
}
.weside {
  font-size: 14px;
}
.item-col .title {
  color: #ddd;
  margin-bottom: 10px;
}
.item span {
  margin-right: 5px;
}
</style>