package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.GoodsInfo;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.GoodsInfoMapper;
import com.ruoyi.system.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    /**
     * 查询商品信息数据
     *
     * @param gi 商品信息信息
     * @return 商品信息集合
     */
    @Override
    public List<GoodsInfo> selectlistGoodsInfo(GoodsInfo gi) {
        return goodsInfoMapper.selectlistGoodsInfo(gi);
    }
    /**
     * 校验商品编号是否唯一
     *
     * @param gi 商品信息
     * @return 结果
     */
    @Override
    public String checkGoodsExist(GoodsInfo gi) {
        Long spbh = StringUtils.isNull(gi.getSpbh()) ? -1L : gi.getSpbh();
        GoodsInfo info = goodsInfoMapper.checkGoodsExist(spbh);
        if (StringUtils.isNotNull(info))
        {
            return UserConstants.NOT_UNIQUE;
        }
       return UserConstants.UNIQUE;
    }
    /**
     * 新增保存商品信息
     *
     * @param gi 商品信息
     * @return 结果
     */
    @Override
    public int insertGoods(GoodsInfo gi) {
        return goodsInfoMapper.insertGoods(gi);
    }

    @Override
    public int deleteGoodsBySpbh(Long spbh) {
        return goodsInfoMapper.deleteGoodsBySpbh(spbh);
    }

    @Override
    public int updateGoods(GoodsInfo gi) {
        return goodsInfoMapper.updateGoods(gi);
    }

}
