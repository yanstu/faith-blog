<template>
  <div class="apply">
    <el-row
      class="main"
      type="flex"
      justify="center"
    >
      <el-col :span="16">
        <h5 class="title"><i class="el-icon-circle-plus-outline"></i> {{$t('apply.process')}}</h5>

        <el-divider></el-divider>

        <el-card shadow="hover">
          <el-steps
            align-center
            :active="currStep"
          >
            <el-step
              :title="$t('apply.step1')"
              description="首先接入本博客站点"
            ></el-step>
            <el-step
              :title="$t('apply.step2')"
              description="提交您的站点信息"
            ></el-step>
            <el-step
              :title="$t('apply.step3')"
              description="等待审核，邮件通知"
            ></el-step>
          </el-steps>
          <div
            class="stepContent"
            v-if="currStep === 1"
          >
            <div class="statement">
              <div
                class="item"
                v-text="$t('apply.blogLink')+'：'+this.$axios.defaults.baseURL"
              ></div>
              <div
                class="item"
                v-text="$t('apply.nickName') + '：Faithx'"
              ></div>
              <div class="item">{{$t('apply.introduction')}}：海上碧云断，单于秋色来。</div>
            </div>
          </div>
          <div
            class="stepContent"
            v-if="currStep === 2"
          >
            <el-form
              label-position="right"
              :rules="rules"
              label-width="80px"
              ref="formLabelAlign"
              :model="formLabelAlign"
            >
              <el-form-item
                :label="$t('apply.siteName')"
                prop="name"
              >
                <el-input v-model="formLabelAlign.name"></el-input>
              </el-form-item>
              <el-form-item
                :label="$t('apply.email')"
                prop="email"
              >
                <el-input v-model="formLabelAlign.email"></el-input>
              </el-form-item>
              <el-form-item
                :label="$t('apply.blogLink')"
                prop="blogLink"
              >
                <el-input v-model="formLabelAlign.blogLink"></el-input>
              </el-form-item>
              <el-form-item
                :label="$t('apply.introduction')"
                prop="notes"
              >
                <el-input
                  type="textarea"
                  v-model="formLabelAlign.notes"
                ></el-input>
              </el-form-item>
            </el-form>
          </div>
          <div
            class="stepContent"
            v-if="currStep === 3"
          >
            <p class="tips">
              <i
                class="el-icon-success"
                style="color: #409EFF;font-size: 30px;"
              ></i><br /> 感谢您对本站的信赖，审核成功后我将以邮件的方式通知您。
            </p>
          </div>
          <el-row
            type="flex"
            class="btn-tool"
            justify="space-around"
          >
            <el-button
              icon="el-icon-circle-close-outline"
              v-if="currStep === 2"
              @click="lastStep"
            >{{$t('apply.process')}}</el-button>
            <el-button
              type="primary"
              icon="el-icon-circle-check-outline"
              @click="nextStep"
              v-if="currStep !== 3"
            >{{$t('apply.next')}}</el-button>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "apply",
  data() {
    return {
      currStep: 1,
      formLabelAlign: {
        name: "",
        email: "",
        blogLink: "",
        notes: "",
      },
      rules: {
        name: [
          {
            required: true,
            message: "请输入您的昵称",
            trigger: "blur",
          },
        ],
        email: [
          {
            required: true,
            message: "请输入邮箱地址",
            trigger: "blur",
          },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        blogLink: [
          {
            required: true,
            message: "请输入博客",
            trigger: "blur",
          },
        ],
        notes: [
          {
            required: false,
            message: "请输入额外备注",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    nextStep() {
      this.currStep++;
    },
    lastStep() {
      this.currStep--;
    },
  },
};
</script>

<style scoped>
.title {
  margin-top: 40px;
}

.statement {
  padding: 20px;
  background-color: #ebeef5;
  margin-top: 20px;
}

.stepContent {
  margin: 30px 0;
}

.stepContent .tips {
  font-size: 18px;
  text-align: center;
}
</style>