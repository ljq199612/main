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
      <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
  import {edit, findById} from "../../api/article";

  export default {
    data() {
      return {
        ruleForm: {
          id: '',
          title: '',
          description: '',
          createdUserId: '',
          typeId: '',
          tagId: '',
          markerdownMessage: '',
          gmtCreate: '',
          gmtModified: ''
        },
        rules: {
          title: [
            {required: true, message: '请输入文章标题', trigger: 'blur'},
            {min: 1, max: 15, message: '长度在 1 到 15 个字符', trigger: 'blur'}
          ],
          description: [
            {required: true, message: '请输入文章描述', trigger: 'change'}
          ],
/*          typeId: [
            {required: true, message: '请选择日期', trigger: 'change'}
          ],
          tagId: [
            {required: true, message: '请选择时间', trigger: 'change'}
          ],*/
          markerdownMessage: [
            {required: true, message: '请输入文章内容', trigger: 'change'}
          ],
        }
      };
    },
    mounted() {
      this.getBookInfo()
    },
    methods: {
      submitForm() {
        const _this = this
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            //let data = _this.ruleForm
            edit(this.ruleForm).then((resp) => {
              if (resp.status = 'success') {
                _this.$message({
                  type: 'success',
                  message: '修改成功'
                });
                _this.$router.push('/articleList')
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
        this.ruleForm = {
          id: '',
          title: '',
          description: '',
          createdUserId: '',
          typeId: '',
          tagId: '',
          markerdownMessage: '',
          gmtCreate: '',
          gmtModified: ''
        }
      },
      getBookInfo() {
        let id = this.$route.params.id || 0
        const _this = this
        findById(id).then((res) => {
          _this.ruleForm = res.data

        })
      },
    }
  }
</script>
