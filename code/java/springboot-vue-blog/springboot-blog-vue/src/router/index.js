import Vue from 'vue'
import Router from 'vue-router'
import MainPage from '../views/mainPage'
import UserList from '../views/user/userList'
import UserAdd from '../views/user/userAdd'
import UserEdit from '../views/user/userEdit'
import ArticleList from '../views/article/articleList'
import ArticleAdd from '../views/article/articleAdd'
import ArticleEdit from '../views/article/articleEdit'
import TagList from '../views/tag/tagList'
import TagAdd from '../views/tag/tagAdd'
import TagEdit from '../views/tag/tagEdit'
import TypeList from '../views/type/typeList'
import TypeAdd from '../views/type/typeAdd'
import TypeEdit from '../views/type/typeEdit'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '博客管理-',
      classType: 'el-icon-document',
      component: MainPage,
      show: true,
      redirect:"/articleList", //默认加载页面
      children:[
        {
          path: '/articleList',
          name: '文章管理',
          component: ArticleList,
          classType: 'el-icon-notebook-2 small',
          show: true
        },
        {
          path: '/articleAdd',
          name: '新建文章',
          component: ArticleAdd,
          show: false
        },
        {
          path: '/articleEdit/:id',
          name: '文章编辑',
          component: ArticleEdit,
          show: false
        },
        {
          path: '/tagList',
          name: '标签管理',
          component: TagList,
          classType: 'el-icon-s-flag',
          show: true
        },
        {
          path: '/tagAdd',
          name: '新增标签',
          component: TagAdd,
          show: false
        },
        {
          path: '/tagEdit/:id',
          name: '编辑标签',
          component: TagEdit,
          show: false
        },
        {
          path: '/typeList',
          name: '文章类型管理',
          component: TypeList,
          classType: 'el-icon-s-ticket',
          show: true
        },
        {
          path: '/typeAdd',
          name: '新增文章类型',
          component: TypeAdd,
          show: false
        },
        {
          path: '/typeEdit/:id',
          name: '编辑文章类型',
          component: TypeEdit,
          show: false
        },
      ],
    },
    {
      path: '/',
      name: '用户信息',
      classType: 'el-icon-user-solid',
      component: MainPage,
      show: true,
      children: [
        {
          path: 'userList',
          name: '用户列表',
          classType: 'el-icon-user',
          component: UserList,
          show: true
        },
        {
          path: '/userAdd',
          name: '用户新增',
          component: UserAdd,
          show: false
        },
        {
          path: '/userEdit/:id',
          name: '用户编辑',
          component: UserEdit,
          show: false
        },
      ]
    }

  ]
})
