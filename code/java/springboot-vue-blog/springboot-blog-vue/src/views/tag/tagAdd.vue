<template>
<el-form style="width: 75%" :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
  <el-form-item label="标签名称" prop="tagName">
    <el-input v-model="form.tagName"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm">立即创建</el-button>
    <el-button @click="resetForm(form)">重置</el-button>
  </el-form-item>
</el-form>
</template>
<script>
  import {add} from "../../api/tag";

  export default {
    data() {
      return {
        form: {
          id: '',
          tagName: ''
        },
        rules: {
          tagName: [
            { required: true, message: '请输入标签名称', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm() {
        // console.log(formName)
        debugger
        const _this = this
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let data = _this.form
            add(data).then((resp) =>{
              if(resp.status = 'success'){
                _this.$message({
                  type: 'success',
                  message: '添加成功'
                });
                _this.$router.push('/tagList')
              }else{
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
        this.form= {
         tagName: ''
        }
      }
    }
  }
</script>
