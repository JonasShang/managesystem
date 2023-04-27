package com.ruoyi.common.core.domain.entity;

public class GoodsContractPrintData {
    private static final long serialVersionUID = 1L;
    private String jf;
    private String yf;
    private String jffddbr;
    private String yffddbr;
    private String jfwtdlr;
    private String yfwtdlr;
    private String jfdh;
    private String yfdh;
    private String jfdz;
    private String yfdz;
    private String jfrq;
    private String yfrq;

    public GoodsContractPrintData() {
    }

    public GoodsContractPrintData(String jf, String yf, String jffddbr, String yffddbr, String jfwtdlr, String yfwtdlr, String jfdh, String yfdh, String jfdz, String yfdz, String jfrq, String yfrq) {
        this.jf = jf;
        this.yf = yf;
        this.jffddbr = jffddbr;
        this.yffddbr = yffddbr;
        this.jfwtdlr = jfwtdlr;
        this.yfwtdlr = yfwtdlr;
        this.jfdh = jfdh;
        this.yfdh = yfdh;
        this.jfdz = jfdz;
        this.yfdz = yfdz;
        this.jfrq = jfrq;
        this.yfrq = yfrq;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getJffddbr() {
        return jffddbr;
    }

    public void setJffddbr(String jffddbr) {
        this.jffddbr = jffddbr;
    }

    public String getYffddbr() {
        return yffddbr;
    }

    public void setYffddbr(String yffddbr) {
        this.yffddbr = yffddbr;
    }

    public String getJfwtdlr() {
        return jfwtdlr;
    }

    public void setJfwtdlr(String jfwtdlr) {
        this.jfwtdlr = jfwtdlr;
    }

    public String getYfwtdlr() {
        return yfwtdlr;
    }

    public void setYfwtdlr(String yfwtdlr) {
        this.yfwtdlr = yfwtdlr;
    }

    public String getJfdh() {
        return jfdh;
    }

    public void setJfdh(String jfdh) {
        this.jfdh = jfdh;
    }

    public String getYfdh() {
        return yfdh;
    }

    public void setYfdh(String yfdh) {
        this.yfdh = yfdh;
    }

    public String getJfdz() {
        return jfdz;
    }

    public void setJfdz(String jfdz) {
        this.jfdz = jfdz;
    }

    public String getYfdz() {
        return yfdz;
    }

    public void setYfdz(String yfdz) {
        this.yfdz = yfdz;
    }

    public String getJfrq() {
        return jfrq;
    }

    public void setJfrq(String jfrq) {
        this.jfrq = jfrq;
    }

    public String getYfrq() {
        return yfrq;
    }

    public void setYfrq(String yfrq) {
        this.yfrq = yfrq;
    }

    @Override
    public String toString() {
        return "GoodsContractPrintData{" +
                "jf='" + jf + '\'' +
                ", yf='" + yf + '\'' +
                ", jffddbr='" + jffddbr + '\'' +
                ", yffddbr='" + yffddbr + '\'' +
                ", jfwtdlr='" + jfwtdlr + '\'' +
                ", yfwtdlr='" + yfwtdlr + '\'' +
                ", jfdh='" + jfdh + '\'' +
                ", yfdh='" + yfdh + '\'' +
                ", jfdz='" + jfdz + '\'' +
                ", yfdz='" + yfdz + '\'' +
                ", jfrq='" + jfrq + '\'' +
                ", yfrq='" + yfrq + '\'' +
                '}';
    }
}
