<template>
<el-form style="width: 75%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="图书名称" prop="name">
    <el-input v-model="ruleForm.name"></el-input>
  </el-form-item>
  <el-form-item label="作者" prop="name">
    <el-input v-model="ruleForm.author"></el-input>
  </el-form-item>
  <el-form-item label="出版单位" prop="name">
    <el-input v-model="ruleForm.publish"></el-input>
  </el-form-item>
  <el-form-item label="页数" prop="name">
    <el-input v-model="ruleForm.pages"></el-input>
  </el-form-item>
  <el-form-item label="价格" prop="name">
    <el-input v-model="ruleForm.price"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
    <el-button @click="resetForm('ruleForm')">重置</el-button>
  </el-form-item>
</el-form>
</template>
<script>
  import {add} from "../../api/user";

  export default {
    data() {
      return {
        ruleForm: {
          name: '',
          author: '',
          publish   : '',
          pages: '',
          price: ''
        },
        rules: {
/*          name: [
            { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          region: [
            { required: true, message: '请选择活动区域', trigger: 'change' }
          ],
          date1: [
            { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
          ],
          date2: [
            { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
          ],
          type: [
            { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
          ],
          resource: [
            { required: true, message: '请选择活动资源', trigger: 'change' }
          ],
          desc: [
            { required: true, message: '请填写活动形式', trigger: 'blur' }
          ]*/
        }
      };
    },
    methods: {
      submitForm(formName) {
        const _this = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let data = _this.ruleForm
            add(data).then((resp) =>{
              if(resp.status = 'success'){
                _this.$message({
                  type: 'success',
                  message: '添加成功'
                });
                _this.$router.push('/userList')
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
        this.ruleForm= {
          name:'',
          author: '',
          publish: '',
          pages: '',
          price: ''
        }
      }
    }
  }
</script>
