<template>
  <el-form style="width: 75%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="文章类型" prop="name">
      <el-input v-model="ruleForm.typeName"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  import {add} from "../../api/type";

  export default {
    data() {
      return {
        ruleForm: {
          id: '',
          isDeleted: 0,
          gmtCreate: '',
          gmtModified: '',
         typeName: ''
        },
        rules: {
          typeName: [
            {required: true, message: '请输入类型名称', trigger: 'blur'},
            {min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      submitForm() {
        const _this = this
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            let data = _this.ruleForm
            add(data).then((resp) => {
              if (resp.status = 200) {
                _this.$message({
                  type: 'success',
                  message: '添加成功'
                });
                _this.$router.push('/typeList')
              } else {
                _this.$message({
                  type: 'error',
                  message: '添加失败'
                });
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.ruleForm = {
          typeName: ''
        }
      }
    }
  }
</script>
