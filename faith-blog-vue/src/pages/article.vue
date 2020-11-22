<template>
  <div>
    <el-row
      class="main"
      type="flex"
      justify="center"
    >
      <el-col
        :span="16"
        class="mblog"
      >
        <div id="artcle-info">
          <h2 class="text-center"><strong>{{ blog.title }}</strong></h2>
          <!-- 描述：文章信息 -->

          <div class="text-center timeAndView">
            <el-avatar
              :size="17"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></el-avatar>
            Luckyx
            <span class="article-time">
              <i class="el-icon-time"></i>
              发表于：<span>{{ blog.created }}</span>
            </span>
            &nbsp;|&nbsp;
            <span class="article-views">
              <i class="el-icon-view"></i>
              阅读量：<span>{{ blog.views }}</span>
            </span>
          </div>

          <p class="abstract">
            {{ blog.description }}
          </p>
        </div>

        <el-card
          class="box-card"
          shadow="always"
        >
          <div
            slot="header"
            class="clearfix"
          >
            <span>最后修改时间：{{ blog.modified }}</span>
            <el-link
              v-if="ownBlog"
              @click="toEdit"
              style="float: right; padding: 3px 0"
              icon="el-icon-edit"
            >
              编辑
            </el-link>
          </div>
          <div
            class="markdown-body"
            id="artcle-content"
            v-html="blog.content"
          >
          </div>
        </el-card>
        <div id="statement">
          <div class="item">{{$t('article.author')}}：{{ article.author }}</div>
          <div class="item">{{$t('article.originalLink')}}：
            <a href="https://www.fengziy.cn/">{{ article.originalLink }}</a>
          </div>
          <div class="item">{{$t('article.copyright')}}：本博客所有文章除特别声明外，转载请注明出处！</div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import "github-markdown-css/github-markdown.css";

export default {
  name: "article",
  data() {
    return {
      ownBlog: false,
      blog: {
        id: "",
        title: "",
        description: "",
        content: "",
        views: "",
        modified: "",
      },
      article: {
        author: "",
        originalLink: "",
      },
    };
  },
  methods: {
    //跳转到编辑博客页面
    toEdit() {
      this.$router.push("/blog/" + this.$route.params.blogId + "/edit");
    },
  },
  created() {
    const blogId = this.$route.params.blogId;
    if (blogId) {
      this.article.author = this.$store.getters.getUser.nickname;
      this.article.originalLink = window.location.href;
      //增加博客文章浏览量
      this.$axios
        .get("/view/" + blogId)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.error(err);
        });

      //初始化博客文章
      this.$axios
        .get("/blog/" + blogId)
        .then((res) => {
          const blog = res.data.data;
          this.blog.id = blog.id;
          this.blog.title = blog.title;
          this.blog.description = blog.description;
          this.blog.views = blog.views;
          this.blog.modified = blog.modified;
          this.blog.views++;

          //把博客markdown形式的内容转换为html然后渲染出来
          var MardownIt = require("markdown-it");
          var md = new MardownIt();
          var result = md.render(blog.content);
          this.blog.content = result;

          //判断当前登录用户是否为发表博客用户
          this.ownBlog = blog.userId === this.$store.getters.getUser.id;
        })
        .catch((err) => {
          console.error(err);
        });
    }
  },
};
</script>

<style scoped>
@import url('https://cdn.bootcss.com/github-markdown-css/2.10.0/github-markdown.min.css');
.mblog {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  min-height: 700px;
  padding: 20px 15px;
}
#artcle-info {
  padding: 20px;
  background-image: url(../assets/images/vue.jpg);
  margin-bottom: 20px;
}

#artcle-info .abstract {
  color: #ffffff;
  border-left: 3px solid #f56c6c;
  padding: 10px;
  background-color: rgba(126, 129, 135, 0.3);
}

#artcle-info .timeAndView {
  padding: 20px;
  line-height: 30px;
  font-size: 16px;
  color: #ffffff;
}

pre.has {
  color: #ffffff;
  background-color: rgba(0, 0, 0, 0.8);
}

img.has {
  width: 100%;
}

#statement {
  margin-top: 20px;
  border-left: 3px solid #f56c6c;
  padding: 20px;
  background-color: #ebeef5;
}
</style>