package com.ruoyi.web.controller.company;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.PeopleInfo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.PeopleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

/**
 * swagger 接口
 *
 * @author ShangZhixiang
 */
@RestController
@RequestMapping("/people/peopleInfo")
public class PeopleInfoController extends BaseController {
    @Autowired
    private PeopleInfoService peopleInfoService;

    /**
     * 获取员工信息表
     */
    @GetMapping("/list")
    public TableDataInfo selectlistPeopleInfo(PeopleInfo pi){
        startPage();
        List<PeopleInfo> list = peopleInfoService.selectlistPeopleInfo(pi);
        return getDataTable(list);
    }

    /**
     * 新增商品信息
     */
    @Log(title="员工信息",businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody PeopleInfo peopleInfo){
        System.out.println(peopleInfo);
        System.out.println(UserConstants.NOT_UNIQUE);
        System.out.println(peopleInfoService.checkPeopleExit(peopleInfo));
        if(UserConstants.NOT_UNIQUE.equals(peopleInfoService.checkPeopleExit(peopleInfo))){
            return AjaxResult.error("新增员工信息"+peopleInfo.getYgbh()+"失败");
        }
        return toAjax(peopleInfoService.insertPeople(peopleInfo));
    }

    /**
     * 删除员工信息
     */
    @Log(title = "员工信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ygbh}")
    public AjaxResult remove(@PathVariable Long ygbh){
        return toAjax(peopleInfoService.deletePeopleByygbh(ygbh));
    }

    /**
     * 修改员工信息
     */
    @Log(title = "员工管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody PeopleInfo pi){
        return toAjax(peopleInfoService.updatePeople(pi));
    }
}
