package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.PeopleInfo;

import java.util.List;

public interface PeopleInfoService {
    public List<PeopleInfo> selectlistPeopleInfo(PeopleInfo pi);
    public String checkPeopleExit(PeopleInfo pi);
    public int insertPeople(PeopleInfo pi);
    public int deletePeopleByygbh(Long ygbh);
    public int updatePeople(PeopleInfo pi);
}
