import request from '@/utils/request'

export function findAll(currentPage) {
  return request({
    url: 'blog/comment/comments?pageNum=' + currentPage + '&pageSize=10',
    method: 'get'
  })

}

export function findById(id) {
  return request({
    url: 'blog/comment/findById/' + id,
    method: 'get'
  })
}

export function add(data) {
  return request({
    url: 'blog/comment/save',
    method: 'post',
    data
  })

}

export function edit(data) {
  return request({
    url: 'blog/comment/update',
    method: 'put',
    data
  })

}

export function deleteById(id) {
  return request({
    url: 'blog/comment/deleteById' + id,
    method: 'delete'
  })

}

export default {findAll, add, edit, deleteById}
