# springboot-blog-vue

> springboot-blog前端项目

## 安装步骤

``` bash
# 执行命令
npm install
#如果不行就执行cnpm install (前提是已经安装了淘宝镜像)
cnpm install

# 运行 localhost:8080
npm run dev

# 打包
npm run build
```

## Axios的二次封装

1.安装 axios

```bash
npm install axios
```

2.在 main.js 中引入 axios

```javascript
import axios from 'axios';

Vue.prototype.$axios = axios
```

3.对axios的二次封装

(1).创建一个utils文件夹，然后再创建一个request.js文件（文件夹和文件的名字随意，到时候用的时候自己引入就好了）

(2).二次封装

```javascript
import axios from 'axios'
import Config from '@/settings'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8088', // api 的 base_url
  timeout: Config.timeout // 请求超时时间
})

export default service
```

(3).如何使用，举例：

```js
//引入封装好的文件
import request from '@/utils/request'

export function findById(id) {
  return request({
    url: 'blog/article/findById/' + id,
    method: 'get'
  })
}

export default {findAll, findById, addMessage, edit, deleteById}

----------------------------------------------------
//在vue页面中使用
<script>
  import {findById} from "../../api/article";

getInfo() {
    let id = this.$route.params.id || 0
    const _this = this
    findById(id).then((res) => {
        _this.ruleForm = res.data
    })
}

```

 

后期应该还会有补充说明，暂时就是这样。

