<!-- 组件说明 -->
<template>
  <div class="login-container">
    <el-form
      :model="ruleForm"
      :rules="rules"
      status-icon
      ref="ruleForm"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-page"
    >
      <h3 class="title">{{$t('login.title')}}</h3>
      <el-form-item prop="username">
        <el-input
          type="text"
          v-model="ruleForm.username"
          auto-complete="off"
          :placeholder="$t('login.username')"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="ruleForm.password"
          auto-complete="off"
          :placeholder="$t('login.password')"
        ></el-input>
      </el-form-item>
      <el-checkbox
        v-model="checked"
        class="rememberme"
      >{{$t('login.remember')}}</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button
          type="primary"
          style="width:100%;"
          @click="handleSubmit"
          :loading="logining"
        >{{$t('login.login')}}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      logining: false,
      ruleForm: {
        username: "",
        password: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: this.$t("login.usernametis"),
            trigger: "blur",
          },
          {
            min: 3,
            max: 16,
            message: this.$t("login.usernametis2"),
            triger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: this.$t("login.passwordtis"),
            trigger: "blur",
          },
        ],
      },
      checked: false,
    };
  },
  //页面加载调用获取cookie值
  mounted() {
    this.getCookie();
  },
  methods: {
    handleSubmit(event) {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.logining = true;
          this.$axios
            .post("login", this.ruleForm)
            .then((res) => {
              //判断复选框是否被勾选 勾选则调用配置cookie方法
              if (this.checked == true) {
                //传入账号名，密码，和保存天数3个参数
                this.setCookie(
                  this.ruleForm.username,
                  this.ruleForm.password,
                  7
                );
              } else {
                //清空Cookie
                this.clearCookie();
              }

              //从header中获取到authorization
              const jwt = res.headers["authorization"];
              const userInfo = res.data.data;
              //存储信息
              this.$store.commit("SET_TOKEN", jwt);
              this.$store.commit("SET_USERINFO", userInfo);
              //从store获取用户信息
              console.log(this.$store.getters.getters);
              //跳转到博客主页面
              //this.$router.push("/");
              window.location.href = "/";
            })
            .catch((err) => {
              console.error(err);
            });
        } else {
          console.log("error submit!");
          return false;
        }
      });
    },
    //设置cookie
    setCookie(c_name, c_pwd, exdays) {
      var exdate = new Date(); //获取时间
      exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * exdays); //保存的天数
      //字符串拼接cookie
      window.document.cookie =
        "userName" + "=" + c_name + ";path=/;expires=" + exdate.toGMTString();
      window.document.cookie =
        "userPwd" + "=" + c_pwd + ";path=/;expires=" + exdate.toGMTString();
    },
    //读取cookie
    getCookie: function () {
      if (document.cookie.length > 0) {
        var arr = document.cookie.split("; "); //这里显示的格式需要切割一下自己可输出看下
        for (var i = 0; i < arr.length; i++) {
          var arr2 = arr[i].split("="); //再次切割
          //判断查找相对应的值
          if (arr2[0] == "userName") {
            this.ruleForm.username = arr2[1]; //保存到保存数据的地方
          } else if (arr2[0] == "userPwd") {
            this.ruleForm.password = arr2[1];
          }
        }
      }
    },
    //清除cookie
    clearCookie: function () {
      this.setCookie("", "", -1); //修改2值都为空，天数为负1天就好了
    },
  },
};
</script>

<style>
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>