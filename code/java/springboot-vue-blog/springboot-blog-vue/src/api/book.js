import request from '@/utils/request'

export function findAll(currentPage) {
  return request({
    url: 'blog/book/books?pageNum=' + currentPage + '&pageSize=' + 10,
    method: 'get'
  })

}

export function findById(id) {
  return request({
    url: 'blog/book/findById/' + id,
    method: 'get'
  })
}

export function addBook(data) {
  return request({
    url: 'blog/book/save',
    method: 'post',
    data
  })
}

export function editBook(data) {
  return request({
    url: 'blog/book/update',
    method: 'put',
    data
  })

}

export function deleteById(id) {
  return request({
    url: 'blog/book/deleteById/' + id,
    method: 'delete'

  })
}

export default {findAll, addBook, editBook, deleteById}
