package com.ruoyi.common.core.domain.entity;

public class GoodsContractTableData {
    private Long bh;
    private String pm;
    private String gg;
    private String dw;
    private String sl;
    private String dj;
    private String je;

    public GoodsContractTableData() {
    }

    public GoodsContractTableData(Long bh, String pm, String gg, String dw, String sl, String dj, String je) {
        this.bh = bh;
        this.pm = pm;
        this.gg = gg;
        this.dw = dw;
        this.sl = sl;
        this.dj = dj;
        this.je = je;
    }

    public Long getBh() {
        return bh;
    }

    public void setBh(Long bh) {
        this.bh = bh;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
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

    @Override
    public String toString() {
        return "GoodsContractTableData{" +
                "bh=" + bh +
                ", pm='" + pm + '\'' +
                ", gg='" + gg + '\'' +
                ", dw='" + dw + '\'' +
                ", sl='" + sl + '\'' +
                ", dj='" + dj + '\'' +
                ", je='" + je + '\'' +
                '}';
    }
}
