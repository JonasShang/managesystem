package com.ruoyi.web.controller.goods;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.GoodsTranferDTO;
import com.ruoyi.common.core.domain.entity.PrintData;
import com.ruoyi.common.core.domain.entity.TableData;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.util.GeneratePdfFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * swagger 接口
 *
 * @author ShangZhixiang
 */
@RestController
@RequestMapping("/goods/goodsPrint")
public class GoodsPrintController {
    private static final Logger log= LoggerFactory.getLogger(GoodsPrintController.class);
    @Value("${ele.ipaddress}")
    private  String eleipaddress;
    @Value("${ele.port}")
    private  String eleport;
    @Value("${ele.downloadpath}")
    private  String downloadpath;
    @Value("${ele.uploadpath}")
    private  String uploadpath;
    @Value("${server.port}")
    private  String port;
    @Value("${ele.filePath}")
    private  String eleFilePath;
//    @GetMapping("/list")
//    @Log(title = "商品管理", businessType = BusinessType.UPDATE)
    @PutMapping("/list")
//    public AjaxResult print(@Validated @RequestBody GoodsTranferDTO goodsTranferDTO){
    public AjaxResult print(@Validated @RequestBody GoodsTranferDTO goodsTranferDTO){
        System.out.println(goodsTranferDTO.getTableData());
        System.out.println(goodsTranferDTO.getPrintData());
//        System.out.println(PrintData);
        PrintData printData = goodsTranferDTO.getPrintData();
//        String filename = "CY"+printData.getGhdw()+"_"+printData.getZjeNum()+"_"+DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()) + (new SecureRandom().nextInt(9000)+1000) + ".pdf";
        String filename = printData.getGhdw()+"-"+printData.getDjbh() + ".pdf";
        String filePath = downloadpath + "upload/header/" + filename;
        boolean generateFlag = GeneratePdfFileUtil.generatePDFFile( goodsTranferDTO, filePath);
        String finalFileStorePath = getFileStorePath(filename);
        return AjaxResult.success(finalFileStorePath);
    }

    @Log(title = "商品管理", businessType = BusinessType.EXPORT)
    @PutMapping("/export")
    public AjaxResult export(@Validated @RequestBody List<TableData> tableDataList){//传过来的是一个包含集合的数组
        System.out.println(tableDataList);
//        List<TableData> list = new GoodsPrintService<TableData>(TableData.class);
        ExcelUtil<TableData> util = new ExcelUtil<TableData>(TableData.class);
        System.out.println(util.exportExcel(tableDataList,"销售清单"));
        return util.exportExcel(tableDataList,"销售清单");
    }
    
    private String getFileStorePath(String filename){
        String ipaddress="";
        try {
            ipaddress= InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取程序部署ip地址失败");
            return "/header/"+filename;
        }
       // file:///E:/openSource/y_project-RuoYi-Vue-master/RuoYi-Vue/domain/upload/header/CY202012281458517351.pdf
        return "http://"+ipaddress+":"+port+"/header/"+filename;
//        return eleFilePath+filename;
    }
}
