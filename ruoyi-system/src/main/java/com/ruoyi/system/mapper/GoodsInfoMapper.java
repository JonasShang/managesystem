package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.GoodsInfo;
import com.ruoyi.common.core.domain.entity.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsInfoMapper {
    public List<GoodsInfo> selectlistGoodsInfo(GoodsInfo gi);
    public GoodsInfo checkGoodsExist(Long spbh);
    public int insertGoods(GoodsInfo gi);
    public int deleteGoodsBySpbh(Long deptId);
    public int updateGoods(GoodsInfo gi);

}
