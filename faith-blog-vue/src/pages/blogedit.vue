<!-- 组件说明 -->
<template>
  <div class=''>
    <el-card shadow="always">

      <el-form
        :model="ruleForm"
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item
          label="标题"
          prop="title"
        >
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-form-item
          label="摘要"
          prop="description"
        >
          <el-input v-model="ruleForm.description"></el-input>
        </el-form-item>

        <el-form-item label="发表时间">
          <el-date-picker
            v-model="ruleForm.created"
            type="datetime"
            placeholder="选择发表时间"
            align="right"
            :picker-options="pickerOptions"
            value-format="yyyy-MM-dd HH:mm:ss"
            format="yyyy-MM-dd HH:mm:ss"
          >
          </el-date-picker>
        </el-form-item>

        <el-form-item label="置顶">
          <el-switch v-model="ruleForm.top"></el-switch>
        </el-form-item>

        <el-form-item
          label="内容"
          prop="content"
        >
          <mavon-editor
            :codeStyle="codeStyle"
            :placeholder="'Edit ···'"
            ref="md"
            @imgAdd="$imgAdd"
            @imgDel="$imgDel"
            @change="change"
            :toolbars="toolbars"
            :toolbarsBackground="'#f9f9f9'"
            :ishljs="true"
            v-model="ruleForm.content"
          ></mavon-editor>
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            @click="submitForm('ruleForm')"
          >完成</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>

    </el-card>

  </div>
</template>

<script>
/**
 * 博客添加/编辑页
 * 接口地址：
 *       - 查询博客文章 http://127.0.0.1:8080/blog/{blogId}
 *       - 添加/编辑 http://127.0.0.1:8080/blog/edit
 */

export default {
  name: "blogedit",
  components: {},
  data() {
    return {
      ruleForm: {
        id: "",
        title: "",
        description: "",
        content: "",
        top: false,
        created: "",
      },
      rules: {
        title: [
          { required: true, message: "请输入标题", trigger: "blur" },
          {
            min: 3,
            max: 108,
            message: "长度在 3 到 108 个字符",
            trigger: "blur",
          },
        ],
        description: [
          { required: true, message: "请输入摘要", trigger: "blur" },
        ],
        content: [{ required: true, message: "请输入内容", trigger: "blur" }],
        created: [
          { required: true, message: "请选择发表时间", trigger: "blur" },
        ],
      },
      markdownOption: {
        // toolbars: {
        bold: false, // 粗体
        italic: false, // 斜体
        header: false, // 标题
        underline: false, // 下划线
        strikethrough: false, // 中划线
        mark: false, // 标记
        superscript: false, // 上角标
        subscript: false, // 下角标
        quote: false, // 引用
        ol: false, // 有序列表
        ul: false, // 无序列表
        link: false, // 链接
        imagelink: false, // 图片链接
        code: false, // code
        table: false, // 表格
        fullscreen: false, // 全屏编辑
        readmodel: false, // 沉浸式阅读
        htmlcode: false, // 展示html源码
        help: false, // 帮助
        /* 1.3.5 */
        undo: false, // 上一步
        redo: false, // 下一步
        trash: false, // 清空
        save: false, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: false, // 导航目录
        /* 2.1.8 */
        alignleft: false, // 左对齐
        aligncenter: false, // 居中
        alignright: false, // 右对齐
        /* 2.2.1 */
        subfield: false, // 单双栏模式
        preview: false, // 预览
      },
      codeStyle: "monokai-sublime", //主题
    };
  },
  computed: {},
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios
            .post("/blog/edit", this.ruleForm, {
              headers: {
                Authorization: localStorage.getItem("token"),
              },
            })
            .then((res) => {
              this.$alert("操作成功", "提示", {
                confirmButtonText: "确定",
                callback: (action) => {
                  this.$router.push("/article/" + res.data.data.id);
                },
              });
            })
            .catch((err) => {
              console.error(err);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
  created() {
    const blogId = this.$route.params.blogId;
    if (blogId) {
      this.$axios
        .get("/blog/" + blogId)
        .then((res) => {
          const blog = res.data.data;
          this.ruleForm.id = blog.id;
          this.ruleForm.description = blog.description;
          this.ruleForm.title = blog.title;
          this.ruleForm.content = blog.content;
          this.ruleForm.created = blog.created;
          this.ruleForm.top = blog.top;
        })
        .catch((err) => {
          console.error(err);
        });
    }
  },
};
</script>

<style>
</style>