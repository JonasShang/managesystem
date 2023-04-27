package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.PeopleInfo;

import java.util.List;

public interface PeopleInfoMapper {
    public List<PeopleInfo> selectlistPeopleInfo(PeopleInfo pi);
    public PeopleInfo checkPeopleExit(Long ygbh);
    public int insertPeople(PeopleInfo pi);
    public int deletePeopleByygbh(Long ygbh);
    public int updatePeople(PeopleInfo pi);
}
