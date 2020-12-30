package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.core.domain.BaseEntity;

public class TableData extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Excel(name = "编号", cellType = ColumnType.NUMERIC)
    private Long bh;
    @Excel(name = "产品名称及规格")
    private String cpmcjgg;
    @Excel(name = "单位")
    private String dw;
    @Excel(name = "数量")
    private String sl;
    @Excel(name = "单价")
    private String dj;
    @Excel(name = "金额")
    private String je;
    @Excel(name = "备注")
    private String bz;

    public TableData(Long bh, String cpmcjgg, String dw, String sl, String dj, String je, String bz) {
        this.bh = bh;
        this.cpmcjgg = cpmcjgg;
        this.dw = dw;
        this.sl = sl;
        this.dj = dj;
        this.je = je;
        this.bz = bz;
    }

    public TableData() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getBh() {
        return bh;
    }

    public void setBh(Long bh) {
        this.bh = bh;
    }

    public String getCpmcjgg() {
        return cpmcjgg;
    }

    public void setCpmcjgg(String cpmcjgg) {
        this.cpmcjgg = cpmcjgg;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }


    @Override
    public String toString() {
        return "TableData{" +
                "bh=" + bh +
                ", cpmcjgg='" + cpmcjgg + '\'' +
                ", dw='" + dw + '\'' +
                ", sl='" + sl + '\'' +
                ", dj='" + dj + '\'' +
                ", je='" + je + '\'' +
                ", bz='" + bz + '\'' +
                '}';
    }
}

