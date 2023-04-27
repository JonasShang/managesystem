package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.PeopleInfo;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.PeopleInfoMapper;
import com.ruoyi.system.service.PeopleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PeopleInfoServiceImpl implements PeopleInfoService {
    @Autowired
    private PeopleInfoMapper peopleInfoMapper;
    @Override
    public List<PeopleInfo> selectlistPeopleInfo(PeopleInfo pi) {
        return peopleInfoMapper.selectlistPeopleInfo(pi);
    }

    @Override
    public String checkPeopleExit(PeopleInfo pi) {
        Long ygbh = StringUtils.isNull(pi.getYgbh())? -1L : pi.getYgbh();
        PeopleInfo info = peopleInfoMapper.checkPeopleExit(ygbh);
        if(StringUtils.isNotNull(info)){
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertPeople(PeopleInfo pi) {
        return peopleInfoMapper.insertPeople(pi);
    }

    @Override
    public int deletePeopleByygbh(Long ygbh) {
        return peopleInfoMapper.deletePeopleByygbh(ygbh);
    }

    @Override
    public int updatePeople(PeopleInfo pi) {
        return peopleInfoMapper.updatePeople(pi);
    }
}
