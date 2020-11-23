<template>
  <div class="friend">
    <el-card class="box-card">
      <div
        slot="header"
        οndragstart="return false"
        class="d-flex align-items-center"
      >
        <img
          class="card-icon"
          src="../assets/images/total.png"
        />
        <span>{{$t('statistics.title')}}</span>
      </div>
      <div class="text item"><span>{{$t('statistics.articles')}}：{{ totalArticles }}</span></div>
      <div class="text item"><span>{{$t('statistics.views')}}：{{ totalViews }}</span></div>
    </el-card>
  </div>
</template>

<script>
/**
 * 博客统计组件
 * 接口地址：
 *       - 博客统计 http://127.0.0.1:8080/blog/total
 */

export default {
  name: "statistics",
  data() {
    return {
      totalArticles: 0,
      totalViews: 0,
    };
  },
  created() {
    this.$axios
      .get("/blog/total")
      .then((res) => {
        this.totalArticles = res.data.data.totalArticles;
        this.totalViews = res.data.data.totalViews;
      })
      .catch((err) => {
        console.error(err);
      });
  },
};
</script>

<style scoped>
.box-card .item a {
  text-decoration: none;
  /*color: #F56C6C;*/
}
.box-card span {
  font-weight: bold;
}
.card-icon {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}
</style>