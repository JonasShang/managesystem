import request from '@/utils/request'

// 导出销售清单
export function exportGoodsList(data) {
  return request({
    url: '/goods/goodsPrint/export',
    method: 'put',
    data: data
  })
}
// //打印销售清单
// export function goodsHandlePrint(tableData) {
//   return request({
//     url: '/goods/goodsPrint/list',
//     method: 'put',
//     data: tableData
//   })
// }
//打印销售清单
export function goodsHandlePrint(tableData,printData) {
  return request({
    url: '/goods/goodsPrint/list',
    method: 'put',
    data: {tableData,printData}
  })
}


