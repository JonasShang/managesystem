import request from '@/utils/request'

//打印销售清单
export function goodsContractPrint() {
  return request({
    url: '/goods/goodsContract/list',
    method: 'put',
    data: ''
  })
}
