package com.ruoyi.common.core.domain.entity;

public class PrintData {
    private static final long serialVersionUID = 1L;
    private Long pageNum;
    private Long pageSize;
    private String ghdw;
    private String lxdh;
    private String djrq;
    private String djbh;
    private String zjeNum;
    private String zjeChinese;

    public PrintData() {
    }

    public PrintData(Long pageNum, Long pageSize, String ghdw, String lxdh, String djrq, String djbh, String zjeNum, String zjeChinese) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.ghdw = ghdw;
        this.lxdh = lxdh;
        this.djrq = djrq;
        this.djbh = djbh;
        this.zjeNum = zjeNum;
        this.zjeChinese = zjeChinese;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getGhdw() {
        return ghdw;
    }

    public void setGhdw(String ghdw) {
        this.ghdw = ghdw;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
    }

    public String getDjbh() {
        return djbh;
    }

    public void setDjbh(String djbh) {
        this.djbh = djbh;
    }

    public String getZjeNum() {
        return zjeNum;
    }

    public void setZjeNum(String zjeNum) {
        this.zjeNum = zjeNum;
    }

    public String getZjeChinese() {
        return zjeChinese;
    }

    public void setZjeChinese(String zjeChinese) {
        this.zjeChinese = zjeChinese;
    }

    @Override
    public String toString() {
        return "PrintData{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", ghdw='" + ghdw + '\'' +
                ", lxdh='" + lxdh + '\'' +
                ", djrq='" + djrq + '\'' +
                ", djbh='" + djbh + '\'' +
                ", zjeNum='" + zjeNum + '\'' +
                ", zjeChinese='" + zjeChinese + '\'' +
                '}';
    }
}
