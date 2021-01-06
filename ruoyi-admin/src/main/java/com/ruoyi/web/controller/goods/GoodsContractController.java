package com.ruoyi.web.controller.goods;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.web.controller.util.GeneratePdfFileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * swagger 接口
 *
 * @author ShangZhixiang
 */
@RestController
@RequestMapping("goods/goodsContract")
public class GoodsContractController {
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

    @PutMapping("/list")
    public AjaxResult print(){
//        String filename = "购销合同"+DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now())+".pdf";
        String filename = "铝合金断桥铝型材门窗制作及安装合同.pdf";
        String filePath = downloadpath + "upload/header/" + filename;
//        boolean generateFlag = GeneratePdfFileUtil.generatePDFFile( goodsTranferDTO, filePath);
        String finalFileStorePath = getFileStorePath(filename);

        return AjaxResult.success(finalFileStorePath,null);
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
