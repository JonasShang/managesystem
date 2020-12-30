package com.ruoyi.web.controller.goods;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.GoodsInfo;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * swagger 接口
 *
 * @author ShangZhixiang
 */
@RestController
@RequestMapping("/goods/goodsInfo")
public class GoodsInfoController extends BaseController {
    @Autowired
    private GoodsInfoService goodsInfoService;

    /**
     * 获取商品信息表
     */
    //@PreAuthorize("@ss.hasPermi('goods:goodsInfo:list')")
    @GetMapping("/list")
    public TableDataInfo selectlistGoodsInfo(GoodsInfo goodinfo) {
        startPage();
        List<GoodsInfo> list = goodsInfoService.selectlistGoodsInfo(goodinfo);
        return getDataTable(list);
    }

    /**
     * 新增商品信息
     */
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody GoodsInfo goodinfo) {
        System.out.println(goodinfo);
        System.out.println(UserConstants.NOT_UNIQUE);
        System.out.println(goodsInfoService.checkGoodsExist(goodinfo));
        if (UserConstants.NOT_UNIQUE.equals(goodsInfoService.checkGoodsExist(goodinfo))) {
            return AjaxResult.error("新增商品信息'" + goodinfo.getSpbh() + "'失败，商品编号已存在");
        }
        return toAjax(goodsInfoService.insertGoods(goodinfo));
    }

    /**
     * 删除商品信息
     */
//    @PreAuthorize("@ss.hasPermi('goods:goodsInfo:remove')")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{spbh}")
    public AjaxResult remove(@PathVariable Long spbh) {
        return toAjax(goodsInfoService.deleteGoodsBySpbh(spbh));
    }

    /**
     * 修改商品信息
     */
//    @PreAuthorize("@ss.hasPermi('goods:goodsInfo:update')")
    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody GoodsInfo gi){
//        if (UserConstants.NOT_UNIQUE.equals(goodsInfoService.checkGoodsExist(gi))) {
//            return AjaxResult.error("修改商品信息'" + gi.getSpbh() + "'失败，商品编号已存在");
//        }
        return toAjax(goodsInfoService.updateGoods(gi));
    }

}
