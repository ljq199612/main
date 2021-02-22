import request from '@/utils/request'

export function findAll(currentPage) {
  return request({
    url: 'blog/article/articles?pageNum=' + currentPage + '&pageSize=10',
    method: 'get'
  })

}

export function findById(id) {
  return request({
    url: 'blog/article/findById/' + id,
    method: 'get'
  })
}

export function addMessage(data) {
  return request({
    url: 'blog/article/save',
    method: 'post',
    data
})

}

export function edit(data) {
  return request({
    url: 'blog/article/update',
    method: 'put',
    data
  })

}

export function deleteById(id) {
  return request({
    url: 'blog/article/deleteById' + id,
    method: 'delete'
  })

}

export default { findAll, findById, addMessage, edit, deleteById }
