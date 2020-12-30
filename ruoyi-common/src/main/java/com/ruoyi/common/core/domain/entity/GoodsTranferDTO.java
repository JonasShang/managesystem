package com.ruoyi.common.core.domain.entity;

import java.util.List;

public class GoodsTranferDTO {
    private PrintData printData;
    private List<TableData> tableData;

    public GoodsTranferDTO() {
    }

    public GoodsTranferDTO(PrintData printData, List<TableData> tableData) {
        this.printData = printData;
        this.tableData = tableData;
    }

    public PrintData getPrintData() {
        return printData;
    }

    public void setPrintData(PrintData printData) {
        this.printData = printData;
    }

    public List<TableData> getTableData() {
        return tableData;
    }

    public void setTableData(List<TableData> tableData) {
        this.tableData = tableData;
    }

    @Override
    public String toString() {
        return "GoodsTranferDTO{" +
                "printData=" + printData +
                ", tableData=" + tableData +
                '}';
    }
}
