package com.ruoyi.common.core.domain.entity;

public class PeopleInfo {
    private static final long serialVersionUID = 1L;
    private Long ygbh;
    private String ygxm;
    private String ygxb;
    private String ygsr;
    private String yghj;
    private String gznr;

    public PeopleInfo() {
    }

    public PeopleInfo(Long ygbh, String ygxm, String ygxb, String ygsr, String yghj, String gznr) {
        this.ygbh = ygbh;
        this.ygxm = ygxm;
        this.ygxb = ygxb;
        this.ygsr = ygsr;
        this.yghj = yghj;
        this.gznr = gznr;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getYgbh() {
        return ygbh;
    }

    public void setYgbh(Long ygbh) {
        this.ygbh = ygbh;
    }

    public String getYgxm() {
        return ygxm;
    }

    public void setYgxm(String ygxm) {
        this.ygxm = ygxm;
    }

    public String getYgxb() {
        return ygxb;
    }

    public void setYgxb(String ygxb) {
        this.ygxb = ygxb;
    }

    public String getYgsr() {
        return ygsr;
    }

    public void setYgsr(String ygsr) {
        this.ygsr = ygsr;
    }

    public String getYghj() {
        return yghj;
    }

    public void setYghj(String yghj) {
        this.yghj = yghj;
    }

    public String getGznr() {
        return gznr;
    }

    public void setGznr(String gznr) {
        this.gznr = gznr;
    }

    @Override
    public String toString() {
        return "PeopleInfo{" +
                "ygbh=" + ygbh +
                ", ygxm='" + ygxm + '\'' +
                ", ygxb='" + ygxb + '\'' +
                ", ygsr='" + ygsr + '\'' +
                ", yghj='" + yghj + '\'' +
                ", gznr='" + gznr + '\'' +
                '}';
    }
}
