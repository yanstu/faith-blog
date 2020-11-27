<template>
  <div class="archive">
    <div
      class="count"
      v-for="(year,index) in activities"
      :key="index"
    >{{ index }}
      <br />
      <br />

      <div
        class="block"
        v-for="(month,ind) in year"
        :key="ind"
      >
        <el-timeline>
          <el-timeline-item
            :timestamp="ind"
            placement="top"
          >
            <el-card
              style="margin-top:10px"
              v-for="(title,inx) in month"
              :key="inx"
            >
              <el-link class="_index" :underline="false"
                :href="'/article/'+title.id"
                target="_blank"
              >
                <p>{{ title.title }}</p>
              </el-link>
            </el-card>
          </el-timeline-item>
        </el-timeline>
      </div>

    </div>
  </div>
</template>


<script>
/**
 * 文章归档页
 * 接口地址：
 *       - 获取全部归档 http://127.0.0.1:8080/archive
 */

export default {
  name: "archive",
  data() {
    return {
      activities: [],
    };
  },
  methods: {
    hoverLine(activity) {
      activity.color = "#409eff";
    },
  },
  created() {
    this.$axios.get("/archive").then((res) => {
      this.activities = res.data.data;
    });
  },
};
</script>

<style scoped>
.line-item {
  display: inline-block;
}

.line-item:hover {
  cursor: pointer;
  color: #409eff;
}

.count {
  margin-bottom: 20px;
  font-size: 20px;
  color: #e6a23c;
}
</style>