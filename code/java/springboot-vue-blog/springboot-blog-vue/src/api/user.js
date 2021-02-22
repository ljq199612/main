import request from '@/utils/request'

export function findAll(currentPage) {
  return request({
    url: 'blog/user/users?pageNum=' + currentPage + '&pageSize=10',
    method: 'get'
  })

}

export function findById(id) {
  return request({
    url: 'blog/user/findById/' + id,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'blog/user/save',
    method: 'post',
    data
  })

}

export function edit(data) {
  return request({
    url: 'blog/user/update',
    method: 'put',
    data
  })

}

export function deleteById(id) {
  return request({
    url: 'blog/user/deleteById' + id,
    method: 'delete'
  })

}

export default {findAll, add, edit, deleteById}
