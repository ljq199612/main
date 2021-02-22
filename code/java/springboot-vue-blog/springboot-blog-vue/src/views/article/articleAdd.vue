<template>
  <el-form style="width: 75%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="文章标题" prop="title">
      <el-input v-model="ruleForm.title"></el-input>
    </el-form-item>
    <el-form-item label="描述" prop="description">
      <el-input v-model="ruleForm.description"></el-input>
    </el-form-item>
    <el-form-item label="文章类型" prop="typeId">
      <el-input v-model="ruleForm.typeId"></el-input>
    </el-form-item>
    <el-form-item label="文章标签" prop="tagId">
      <el-input v-model="ruleForm.tagId"></el-input>
    </el-form-item>
    <el-form-item label="文章内容" prop="markerdownMessage">
      <mavon-editor v-model="ruleForm.markerdownMessage" ></mavon-editor>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  import {addMessage} from "../../api/article";

  export default {
    data() {
      return {
        ruleForm: {
          title: 'title',
          description: 'des',
          createdUserId: 'create',
          typeId: 'typeid',
          tagId: 'tagid',
          markerdownMessage: 'mark',
          gmtCreate: 'gmt',
          gmtModified: 'creat'
        },
        rules: {
          title: [
            {required: true, message: '请输入文章标题', trigger: 'blur'},
            {min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur'}
          ],
          description: [
            {required: true, message: '请输入文章描述', trigger: 'change'}
          ],
          markerdownMessage: [
            {required: true, message: '请输入文章内容', trigger: 'change'}
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        const _this = this
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            let data = _this.ruleForm
            addMessage(data).then((resp) => {
              if (resp.status = 'success') {
                _this.$message({
                  type: 'success',
                  message: '添加成功'
                });
                _this.$router.push('/articleList')
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
          title: '',
          description: '',
          createdUserId: '',
          typeId: '',
          tagId: '',
          markerdownMessage: '',
          gmtCreate: '',
          gmtModified: ''
        }
      }
    }
  }
</script>
