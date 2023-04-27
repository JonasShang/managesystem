package com.ruoyi.common.core.domain.entity;

import java.util.List;

public class GoodsContractTranferDTO {
    private GoodsContractPrintData goodsContractPrintData;
    private List<GoodsContractTableData> goodsContractTableData;

    public GoodsContractTranferDTO() {
    }

    public GoodsContractTranferDTO(GoodsContractPrintData goodsContractPrintData, List<GoodsContractTableData> goodsContractTableData) {
        this.goodsContractPrintData = goodsContractPrintData;
        this.goodsContractTableData = goodsContractTableData;
    }

    public GoodsContractPrintData getGoodsContractPrintData() {
        return goodsContractPrintData;
    }

    public void setGoodsContractPrintData(GoodsContractPrintData goodsContractPrintData) {
        this.goodsContractPrintData = goodsContractPrintData;
    }

    public List<GoodsContractTableData> getGoodsContractTableData() {
        return goodsContractTableData;
    }

    public void setGoodsContractTableData(List<GoodsContractTableData> goodsContractTableData) {
        this.goodsContractTableData = goodsContractTableData;
    }

    @Override
    public String toString() {
        return "GoodsContractTranferDTO{" +
                "goodsContractPrintData=" + goodsContractPrintData +
                ", goodsContractTableData=" + goodsContractTableData +
                '}';
    }
}
