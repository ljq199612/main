<template>
  <el-container style="height: 800px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
      <el-menu router :default-openeds="['0', '1']">
        <el-submenu v-for="(item,index) in $router.options.routes" :key="index" :index="index+''" v-if="item.show">
          <template slot="title"><i :class="item.classType"></i>{{item.name}}</template>
          <el-menu-item v-for="(item2,index2) in item.children"
                        :key="index2"
                        :index="item2.path"
                        :class="$route.path==item2.path?'is-active':''"
                        v-text="item2.name"
                         v-if="item2.show">
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-main>
        <router-view/>
      </el-main>
      <el-footer>纯手工操作</el-footer>
    </el-container>
  </el-container>
</template>

<script>
    export default {
        name: "mainPage",
    }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }
</style>
