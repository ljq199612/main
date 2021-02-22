<template>
  <div>
    <el-row :gutter="20" style="margin-right: 1700px">
      <router-link :to="'/userAdd'">
        <el-button size="small" type="primary" class="filter-item" icon="el-icon-plus" align="left">新增</el-button>
      </router-link>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column prop="id" label="编号" width="180">
      </el-table-column>
      <el-table-column prop="userName" label="用户名" width="150px">
      </el-table-column>
      <el-table-column prop="nickName" label="昵称" width="150px">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="120px">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="150px">
      </el-table-column>
      <el-table-column prop="gmtCreate" label="创建时间" width="150px">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <router-link :to="'/userEdit/'+scope.row.id">
            <el-button size="mini" type="primary" icon="el-icon-edit">编辑</el-button>
          </router-link>
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="totalNum"
      @current-change="page">
    </el-pagination>
  </div>
</template>

<script>
  import {findAll, deleteById} from '../../api/user'

  export default {
    data() {
      return {
        totalNum: null,
        tableData: [{
          id: '5',
          name: '',
          author: '',
          publish: '',
          pages: 0,
          price: 0
        }]
      }
    },
    methods: {
      handleDelete(index, row) {
        const _this = this
        const id = row.id
        console.log(id)
        deleteById(id).then((resp) => {
          if (resp.status = 'success') {
            this.$message({
              type: 'success',
              message: '删除成功'
            });
          } else {
            this.$message({
              type: 'error',
              message: '删除失败'
            });
          }
          // window.location.reload()
        })
      },
      page(currentPage) {
        const _this = this
        if (!currentPage) { //如果currentPage为undefined，则令current Page=1
          currentPage = 1
        }
        findAll(currentPage, 10).then(res => {
          _this.tableData = res.data.records
          _this.totalNum = res.data.total
          // console.log(res)
        }).catch(err => {
          console.log(err)
        })
      }
    },
    mounted() {
      this.page()
    }
  }
</script>
