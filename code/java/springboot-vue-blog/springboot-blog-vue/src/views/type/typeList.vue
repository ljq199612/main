<template>
  <div>
    <el-row :gutter="20" style="margin-right: 1700px">
      <router-link :to="'/typeAdd'">
        <el-button size="small" type="primary" class="filter-item" icon="el-icon-plus" align="left">新增</el-button>
      </router-link>
    </el-row>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column prop="id" label="编号" width="180">
      </el-table-column>
      <el-table-column prop="typeName" label="文章类型" width="150px">
      </el-table-column>
      <el-table-column  label="创建时间" width="250px">
        <template slot-scope="scope">
        {{dateFormat(scope.row.gmtCreate)}}
        </template>
      </el-table-column>
      <el-table-column  label="修改时间" width="250px">
        <template slot-scope="scope">
          {{dateFormat(scope.row.gmtModified)}}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <router-link :to="'/typeEdit/'+scope.row.id">
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
  import {findAll, deleteById} from '../../api/type'

  export default {
    data() {
      return {
        totalNum: null,
        tableData: [{
          id: '',
          typeName: '',
          gmtCreate: '',
          gmtModified: ''
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
      },
      //时间格式化函数，此处仅针对yyyy-MM-dd hh:mm:ss 的格式进行格式化
      dateFormat:function(time) {
        console.log(time)
        let date=new Date(time);
        let year=date.getFullYear();
        /* 在日期格式中，月份是从0开始的，因此要加0
         * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
         * */
        let month= date.getMonth()+1<10 ? "0"+(date.getMonth()+1) : date.getMonth()+1;
        let day=date.getDate()<10 ? "0"+date.getDate() : date.getDate();
        let hours=date.getHours()<10 ? "0"+date.getHours() : date.getHours();
        let minutes=date.getMinutes()<10 ? "0"+date.getMinutes() : date.getMinutes();
        let seconds=date.getSeconds()<10 ? "0"+date.getSeconds() : date.getSeconds();
        // 拼接
        return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
      }
    },
    mounted() {
      this.page()
    }
  }
</script>
