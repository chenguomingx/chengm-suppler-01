import request from '@/utils/request'

export function getSupplierList(keyword) {
  return request({
    url: '/suppliers',
    method: 'get',
    params: { keyword }
  })
}

export function getSupplier(id) {
  return request({
    url: `/suppliers/${id}`,
    method: 'get'
  })
}

export function addSupplier(data) {
  return request({
    url: '/suppliers',
    method: 'post',
    data
  })
}

export function updateSupplier(data) {
  return request({
    url: '/suppliers',
    method: 'put',
    data
  })
}

export function deleteSupplier(id) {
  return request({
    url: `/suppliers/${id}`,
    method: 'delete'
  })
}

export function updateSupplierStatus(id, status) {
  return request({
    url: `/suppliers/${id}/status`,
    method: 'put',
    params: { status }
  })
}
