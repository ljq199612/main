import axios from 'axios'
import Config from '@/settings'
import router from '@/router/index'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://localhost:8088', // api 的 base_url
  timeout: Config.timeout // 请求超时时间
})

export default service
