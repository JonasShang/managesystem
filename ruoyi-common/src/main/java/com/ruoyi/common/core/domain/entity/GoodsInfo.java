package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品信息表 goodsInfo
 *
 * @author ShangZhixiang
 */
public class GoodsInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Long spbh;  /**商品编号*/
    private String spsx;  /**商品属性*/
    private String spmc;  /**商品名称*/
    private String spjg;  /**商品价格*/
    private String spdw;  /**商品单位*/
    private String spjj;  /**商品进价*/
    private String sphyd;  /**商品货源地*/
    private String spjhdh;  /**商品进货电话*/
    private String bz;

    public GoodsInfo() {
    }

    public GoodsInfo(Long spbh) {
        this.spbh = spbh;
    }

    /**备注*/


    public Long getSpbh() {
        return spbh;
    }

    public void setSpbh(Long spbh) {
        this.spbh = spbh;
    }

    public String getSpsx() {
        return spsx;
    }

    public void setSpsx(String spsx) {
        this.spsx = spsx;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpjg() {
        return spjg;
    }

    public void setSpjg(String spjg) {
        this.spjg = spjg;
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }

    public String getSpjj() {
        return spjj;
    }

    public void setSpjj(String spjj) {
        this.spjj = spjj;
    }

    public String getSphyd() {
        return sphyd;
    }

    public void setSphyd(String sphyd) {
        this.sphyd = sphyd;
    }

    public String getSpjhdh() {
        return spjhdh;
    }

    public void setSpjhdh(String spjhdh) {
        this.spjhdh = spjhdh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public GoodsInfo(Long spbh, String spsx, String spmc, String spjg, String spdw, String spjj, String sphyd, String spjhdh, String bz) {
        this.spbh = spbh;
        this.spsx = spsx;
        this.spmc = spmc;
        this.spjg = spjg;
        this.spdw = spdw;
        this.spjj = spjj;
        this.sphyd = sphyd;
        this.spjhdh = spjhdh;
        this.bz = bz;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("spbh",getSpbh())
                .append("spsx",getSpsx())
                .append("spmc",getSpmc())
                .append("spjg",getSpjg())
                .append("spdw",getSpdw())
                .append("spjj",getSpjj())
                .append("sphyd",getSphyd())
                .append("spjhdh",getSpjhdh())
                .append("bz",getBz())
                .toString();
    }
}
