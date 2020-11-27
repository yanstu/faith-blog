<template>
  <div class="about">
    <el-row
      class="main"
      type="flex"
      justify="center"
    >
      <el-col :span="16" :xs="24">
        <h5 class="title"><i class="fa fa-github"></i> {{ $t('about.source') }}</h5>
        <div class="statement _index">
          <div class="item">GitHub：<a href="https://github.com/yanstu/faith-blog">https://github.com/yanstu/faith-blog</a></div>
        </div>
        <h5 class="title"><i class="el-icon-link"></i> {{ $t('about.links') }}</h5>
        <div class="statement _index">
          <div class="item"><a
              :href="this.$axios.defaults.baseURL+'apply'"
              v-text="this.$axios.defaults.baseURL+'apply'"
            ></a></div>
        </div>
        <h5 class="title"><i class="fa fa-wechat"></i> {{$t('about.contactMe')}}</h5>
        <el-card shadow="always" class="_index">
          <el-form
            label-position="left"
            :rules="rules"
            label-width="80px"
            ref="formLabelAlign"
            :model="formLabelAlign"
          >
            <el-form-item
              :label="$t('about.yourName')"
              prop="name"
            >
              <el-input v-model="formLabelAlign.name"></el-input>
            </el-form-item>
            <el-form-item
              :label="$t('about.email')"
              prop="email"
            >
              <el-input v-model="formLabelAlign.email"></el-input>
            </el-form-item>
            <el-form-item
              :label="$t('about.content')"
              prop="content"
            >
              <el-input
                type="textarea"
                v-model="formLabelAlign.content"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button
                type="primary"
                @click="submitForm('formLabelAlign')"
              >{{$t('about.submit')}}</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "about",
  data() {
    return {
      formLabelAlign: {
        name: "",
        email: "",
        content: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: this.$t("about.nameTip"),
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true,
            message: this.$t("about.emailTip1"),
            trigger: "blur",
          },
          {
            type: "email",
            message: this.$t("about.emailTip2"),
            trigger: ["blur", "change"],
          },
        ],
        content: [
          {
            required: true,
            message: this.$t("about.contentTip"),
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert("submit!");
        } else {
          return false;
        }
      });
    },
  },
  created() {
    console.log(process.env)
  },
};
</script>

<style scoped>
.dl-blog dd {
  margin-left: 30px;
}

.dl-blog .icon {
  width: 20px;
  height: 20px;
}

.title {
  margin-top: 40px;
}

.statement {
  border-left: 3px solid #f56c6c;
  padding: 20px;
  background-color: #ebeef5;
  margin-top: 20px;
}
</style>