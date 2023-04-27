import request from '@/utils/request'

//查询员工信息表
export function selectlistPeopleInfo(query) {
  return request({
    url:'/people/peopleInfo/list',
    method: 'get',
    params:query
  })
}

// 新增员工信息
export function addPeople(data) {
  return request({
    url: '/people/peopleInfo',
    method: 'post',
    data: data
  })
}


//删除员工信息
export function delPeople(ygbh) {
  return request({
    url: '/people/peopleInfo/' + ygbh,
    method: 'delete'
  })
}


// 修改员工信息
export function updatePeople(data) {
  return request({
    url: '/people/peopleInfo',
    method: 'put',
    data: data
  })
}
