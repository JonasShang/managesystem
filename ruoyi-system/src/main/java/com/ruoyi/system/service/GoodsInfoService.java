package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.GoodsInfo;
import com.ruoyi.common.core.domain.entity.SysDept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理 商品信息
 *
 * @author ShangZhixiang
 */
public interface GoodsInfoService {
    /**
     * 查询商品信息数据
     *
     * @param gi 商品信息
     * @return 部门信息集合
     */
    public List<GoodsInfo> selectlistGoodsInfo(GoodsInfo gi);

    /**
     * 查询商品信息是否存在
     *
     * @param gi 商品编号
     * @return 结果 true 存在 false 不存在
     */
    public String checkGoodsExist(GoodsInfo gi);


    /**
     * 新增保存商品信息
     *
     * @param gi 商品信息
     * @return 结果
     */
    public int insertGoods(GoodsInfo gi);

    /**
     * 删除商品信息
     *
     * @param spbh 部门ID
     * @return 结果
     */
    public int deleteGoodsBySpbh(Long spbh);

    /**
     * 修改商品信息
     *
     * @param gi 部门信息
     * @return 结果
     */
    public int updateGoods(GoodsInfo gi);
}
