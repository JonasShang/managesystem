import request from '@/utils/request'

//查询商品信息表
export function selectlistGoodsInfo(query) {
  return request({
    url:'/goods/goodsInfo/list',
    method: 'get',
    params:query
  })
}

// 新增商品信息
export function addGoods(data) {
  return request({
    url: '/goods/goodsInfo',
    method: 'post',
    data: data
  })
}

// // 修改商品信息
// export function updateGoods(data) {
//   return request({
//     url: '/goods/goodsInfo',
//     method: 'put',
//     data: data
//   })
// }

//删除商品信息
export function delGoods(spbh) {
  return request({
    url: '/goods/goodsInfo/' + spbh,
    method: 'delete'
  })
}

// // 查询商品信息
// export function getDept(deptId) {
//   return request({
//     url: '/goods/goodsInfo/' + spbh,
//     method: 'get'
//   })
// }

// 修改商品信息
export function updateGoods(data) {
  return request({
    url: '/goods/goodsInfo',
    method: 'put',
    data: data
  })
}
