<template>
  <el-form style="width: 75%" :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
    <el-form-item label="标签名称" prop="name">
      <el-input v-model="form.tagName"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm">提交</el-button>
      <el-button @click="resetForm">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  import {findById, edit} from "../../api/tag";

  export default {
    data() {
      return {
        form: {
          tagName: ''
        },
        rules: {
          tagName: [
            {required: true, message: '请输入标签名称', trigger: 'blur'},
            {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
          ]
        }
      };
    },
    mounted() {
      this.getInfo()
    },
    methods: {
      submitForm() {
        const _this = this
        this.$refs['form'].validate((valid) => {
          if (valid) {
            edit(this.form).then((resp) => {
              if (resp.status = 200) {
                _this.$message({
                  type: 'success',
                  message: '修改成功'
                });
                _this.$router.push('/tagList')
              } else {
                _this.$message({
                  type: 'error',
                  message: '修改失败'
                });
              }
            })
          } else {
            console.log('更新失败');
            return false;
          }
        });
      },
      resetForm() {
        this.form = {
         tagName: ''
        }
      },
      getInfo() {
        let id = this.$route.params.id || 0
        const _this = this
        findById(id).then((res) => {
          console.log("aaaaa")
          console.log(res.data)
          _this.form = res.data

        }).catch()
      },
    }
  }
</script>
