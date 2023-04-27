package com.ruoyi.web.controller.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.ruoyi.common.core.domain.entity.*;
import com.ruoyi.web.controller.goods.GoodsPrintController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GeneratePdfFileUtil {
    private static final Logger log= LoggerFactory.getLogger(GeneratePdfFileUtil.class);
    // 定义全局的字体静态变量
    private static Font titlefont;
    private static Font headfont;
    private static Font signfont;
    private static Font keyfont;
    private static Font textfont;
    // 最大宽度
    private static int maxWidth = 520;
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            titlefont = new Font(bfChinese, 16, Font.BOLD);
            headfont = new Font(bfChinese, 14, Font.BOLD);
            signfont = new Font(bfChinese, 12, Font.BOLD);
            keyfont = new Font(bfChinese, 10, Font.BOLD);
            textfont = new Font(bfChinese, 10, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    // main测试
//    public static  void main(String[] args) throws Exception {
////        try {
////            // 1.新建document对象
////            Document document = new Document(PageSize.A4);// 建立一个Document对象
////
////            // 2.建立一个书写器(Writer)与document对象关联
////            File file = new File("E:\\openSource\\y_project-RuoYi-Vue-master\\RuoYi-Vue\\domain\\download\\abc.pdf");
////            file.createNewFile();
////            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
////            //writer.setPageEvent(new Watermark("HELLO ITEXTPDF"));// 水印
////            //writer.setPageEvent(new MyHeaderFooter());// 页眉/页脚
////
////            // 3.打开文档
////            document.open();
//////            document.addTitle("Title@PDF-Java");// 标题
//////            document.addAuthor("Author@umiz");// 作者
//////            document.addSubject("Subject@iText pdf sample");// 主题
//////            document.addKeywords("Keywords@iTextpdf");// 关键字
//////            document.addCreator("Creator@umiz`s");// 创建者
////
////            // 4.向文档中添加内容
////            new GeneratePdfFileUtil().generatePDF(document,null);
////
////            // 5.关闭文档
////            document.close();
////            System.out.println("生成结束");
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//        List<TableData> tableDataList = new ArrayList<>();
//        String filePath=null;
//        GeneratePdfFileUtil.generatePDFFile( tableDataList, filePath);
//    }
/**************************************************************************************************/
/*                                          销售清单打印                                          */
/**************************************************************************************************/
    public static  boolean generatePDFFile(GoodsTranferDTO goodsTranferDTO, String filepath){
        try {
            // 1.新建document对象
            Document document = new Document(PageSize.A4);// 建立一个Document对象
            document.setMargins(36.0F, 36.0F, 36.0F, 200.0F);
            // 2.建立一个书写器(Writer)与document对象关联
            File file = new File(filepath);
            if(file.exists()){
                return false;
            }
            file.createNewFile();
            PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file));
            // 3.打开文档
            document.open();
            // 4.向文档中添加内容
            new GeneratePdfFileUtil().generatePDF(document,goodsTranferDTO);
            // 5.关闭文档
            document.close();
            log.info("生成PDF文件成功，path:[{}]",filepath);
        } catch (Exception e) {
            log.error("生成pdf文件异常",e);
            return false;
        }
        return true;
    }

    public  void generatePDF(Document document,GoodsTranferDTO goodsTranferDTO) throws Exception {
        // 段落
        Paragraph paragraph = new Paragraph("上海畅奕铝业发展有限公司", headfont);
        paragraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12); //设置左缩进
        paragraph.setIndentationRight(12); //设置右缩进
        paragraph.setFirstLineIndent(24); //设置首行缩进
        paragraph.setLeading(20f); //行间距
//        paragraph.setSpacingBefore(5f); //设置段落上空白
//        paragraph.setSpacingAfter(10f); //设置段落下空白
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        // 直线
        Paragraph p1 = new Paragraph();
        p1.add(new Chunk(new LineSeparator()));
        // 点线
        Paragraph p2 = new Paragraph();
        p2.add(new Chunk(new DottedLineSeparator()));
        // 超链接
        Anchor anchor = new Anchor("baidu");
        anchor.setReference("www.baidu.com");
        // 定位
        Anchor gotoP = new Anchor("goto");
        gotoP.setReference("#top");
        List<TableData> tableData = goodsTranferDTO.getTableData();
        PrintData printData = goodsTranferDTO.getPrintData();

        /**表头1*/
        String tradeStr = "销售清单";
        Paragraph tradeParagraph = new Paragraph(tradeStr, textfont);
        tradeParagraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
//        tradeParagraph.setSpacingBefore(5f);
//        tradeParagraph.setSpacingAfter(15f);
        PdfPTable tablehead = createTable(new float[] { 100,180,70,120});
        tablehead.addCell(createCell("     购货单位（地址）：",  textfont, Element.ALIGN_LEFT));
        tablehead.addCell(createCell(printData.getGhdw(),  textfont, Element.ALIGN_LEFT));
        tablehead.addCell(createCell("     单据编号：",  textfont, Element.ALIGN_LEFT));
        tablehead.addCell(createCell(printData.getDjbh(),  textfont, Element.ALIGN_LEFT));
        tablehead.addCell(createCell("     联系电话：",  textfont, Element.ALIGN_LEFT));
        tablehead.addCell(createCell(printData.getLxdh(),  textfont, Element.ALIGN_LEFT));
        tablehead.addCell(createCell("     单据日期：",  textfont, Element.ALIGN_LEFT));
        tablehead.addCell(createCell(printData.getDjrq(),  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell("",  textfont, Element.ALIGN_LEFT));
        tablehead.setSpacingAfter(1f);

//        tradeParagraph.setSpacingBefore(5f); //设置段落上空白
//        tradeParagraph.setSpacingAfter(15f); //设置段落下空白



        /**表中*/
        PdfPTable table = createTable(new float[]{150,100,100,110,110,150});
        table.addCell(createCell("产品名称及规格",  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单位",  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("数量",  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单价",  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("金额",  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("备注",  textfont, Element.ALIGN_CENTER,1,1));

        //用于遍历表中的总金额（很重要（最好别用累加，涉及到数据类型的溢出））
//        BigDecimal allamt=new BigDecimal("0");
//        for (int i = 0; i < tableDataList.size(); i++) {
//            allamt=allamt.add(new BigDecimal(tableDataList.get(i).getTp_jyje()));
//        }
        /**表中*/
        tableData.forEach(e->{
            table.addCell(createCell(e.getCpmcjgg(),  textfont, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(e.getDw(),  textfont, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(e.getSl(),  textfont, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(e.getDj(),  textfont, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(e.getJe(),  textfont, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(e.getBz(),  textfont, Element.ALIGN_CENTER,1,1));
        });
        table.addCell(createCell("合计人民币（大写）",  textfont, Element.ALIGN_CENTER,1));
        table.addCell(createCell(printData.getZjeChinese(),  textfont, Element.ALIGN_CENTER,3));
        table.addCell(createCell("（小写）￥",  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(printData.getZjeNum(),  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("",  textfont, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("",  textfont, Element.ALIGN_CENTER,1,1));

//        table.addCell(createCell(printData.getZjeNum(), Element.ALIGN_LEFT,1,1));
//        Paragraph tradeParagraph = new Paragraph("销售清单", textfont).setAlignment(0);
//        tradeParagraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
//        table.setSpacingAfter(20f);
        PdfPTable finalTailTable = createTable(new float[]{700,50});
//        finalTailTable.addCell(createCell(String.format("注：", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("注： ✔ 1.所有货物当面点清,事后概不处理。谢谢配合！                                    一存根（白）  二客户（红） ", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("           ✔ 2.本单据收货人签名作未付款凭证 ", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
//        finalTailTable.addCell(createCell(String.format("总金额:%s", decimalFormat.format(allamt)), textfont, Element.ALIGN_CENTER));
        finalTailTable.addCell(createCell(String.format("特别提示：退货请出示本次购买的单据。 ", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("地址：上海浦东新区南六公路699弄（两港装饰城）4支弄3幢185-191号 ", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("原东明钢材市场海昌铝业（36栋3-5号）", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), signfont, Element.ALIGN_LEFT));
//        finalTailTable.addCell(createCell(String.format("电话：13764746926（黎女士） 13818603886（尚先生） ", " "), textfont, Element.ALIGN_LEFT));
//        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_RIGHT));
        Chunk sigUnderline = new Chunk("                                            ");
        sigUnderline.setUnderline(0.2f, -2f);
        Paragraph para = new Paragraph("  电话：13764746926（黎女士） 13818603886（尚先生）                                   客户签名: ",textfont);
        para.setAlignment(0);
        para.add(sigUnderline);
//        para.setSpacingBefore(-1f);

        document.add(paragraph);
        document.add(tradeParagraph);
        document.add(tablehead);
        document.add(table);
        document.add(finalTailTable);
        document.add(para);

    }

/**************************************************************************************************/
/*                                          购销合同打印                                          */
/**************************************************************************************************/
public static  boolean gxhtgeneratePDFFile(GoodsContractTranferDTO goodsContractTranferDTO, String filepath){
    try {
        // 1.新建document对象
        Document document = new Document(PageSize.A4);// 建立一个Document对象
        document.setMargins(36.0F, 36.0F, 36.0F, 200.0F);
        // 2.建立一个书写器(Writer)与document对象关联
        File file = new File(filepath);
        if(file.exists()){
            return false;
        }
        file.createNewFile();
        PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(file));
        // 3.打开文档
        document.open();
        // 4.向文档中添加内容
        new GeneratePdfFileUtil().gxhtgeneratePDF(document,goodsContractTranferDTO);
        // 5.关闭文档
        document.close();
        log.info("生成PDF文件成功，path:[{}]",filepath);
    } catch (Exception e) {
        log.error("生成pdf文件异常",e);
        return false;
    }
    return true;
}

    public  void gxhtgeneratePDF(Document document,GoodsContractTranferDTO goodsContractTranferDTO) throws Exception {
        // 段落
        Paragraph paragraph = new Paragraph("铝材购销合同", headfont);
        paragraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
        paragraph.setIndentationLeft(12); //设置左缩进
        paragraph.setIndentationRight(12); //设置右缩进
        paragraph.setFirstLineIndent(24); //设置首行缩进
        paragraph.setLeading(20f); //行间距
//        paragraph.setSpacingBefore(5f); //设置段落上空白
//        paragraph.setSpacingAfter(10f); //设置段落下空白
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        // 直线
        Paragraph p1 = new Paragraph();
        p1.add(new Chunk(new LineSeparator()));
        // 点线
        Paragraph p2 = new Paragraph();
        p2.add(new Chunk(new DottedLineSeparator()));
        // 超链接
        Anchor anchor = new Anchor("baidu");
        anchor.setReference("www.baidu.com");
        // 定位
        Anchor gotoP = new Anchor("goto");
        gotoP.setReference("#top");
        List<GoodsContractTableData> goodsContractTableData = goodsContractTranferDTO.getGoodsContractTableData();
        GoodsContractPrintData goodsContractPrintData = goodsContractTranferDTO.getGoodsContractPrintData();

        /**表头1*/
        String tradeStr = "";
        Paragraph tradeParagraph = new Paragraph(tradeStr, textfont);
        tradeParagraph.setAlignment(1); //设置文字居中 0靠左   1，居中     2，靠右
//        tradeParagraph.setSpacingBefore(5f);
//        tradeParagraph.setSpacingAfter(15f);
//        PdfPTable tablehead = createTable(new float[] { 100,180,70,120});
//        tablehead.addCell(createCell("     购货单位（地址）：",  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell(printData.getGhdw(),  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell("     单据编号：",  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell(printData.getDjbh(),  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell("     联系电话：",  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell(printData.getLxdh(),  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell("     单据日期：",  textfont, Element.ALIGN_LEFT));
//        tablehead.addCell(createCell(printData.getDjrq(),  textfont, Element.ALIGN_LEFT));
//        tablehead.setSpacingAfter(1f);

//        tradeParagraph.setSpacingBefore(5f); //设置段落上空白
//        tradeParagraph.setSpacingAfter(15f); //设置段落下空白



//        /**表中*/
//        PdfPTable table = createTable(new float[]{150,100,100,110,110,150});
//        table.addCell(createCell("产品名称及规格",  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell("单位",  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell("数量",  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell("单价",  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell("金额",  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell("备注",  textfont, Element.ALIGN_CENTER,1,1));

//        /**表中*/
//        tableData.forEach(e->{
//            table.addCell(createCell(e.getCpmcjgg(),  textfont, Element.ALIGN_CENTER,1,1));
//            table.addCell(createCell(e.getDw(),  textfont, Element.ALIGN_CENTER,1,1));
//            table.addCell(createCell(e.getSl(),  textfont, Element.ALIGN_CENTER,1,1));
//            table.addCell(createCell(e.getDj(),  textfont, Element.ALIGN_CENTER,1,1));
//            table.addCell(createCell(e.getJe(),  textfont, Element.ALIGN_CENTER,1,1));
//            table.addCell(createCell(e.getBz(),  textfont, Element.ALIGN_CENTER,1,1));
//        });
//        table.addCell(createCell("合计人民币（大写）",  textfont, Element.ALIGN_CENTER,1));
//        table.addCell(createCell(printData.getZjeChinese(),  textfont, Element.ALIGN_CENTER,3));
//        table.addCell(createCell("（小写）￥",  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell(printData.getZjeNum(),  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell("",  textfont, Element.ALIGN_CENTER,1,1));
//        table.addCell(createCell("",  textfont, Element.ALIGN_CENTER,1,1));

        PdfPTable finalTailTable = createTable(new float[]{700,50});
//        finalTailTable.addCell(createCell(String.format("注：", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("注： ✔ 1.所有货物当面点清,事后概不处理。谢谢配合！                                    一存根（白）  二客户（红） ", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("           ✔ 2.本单据收货人签名作未付款凭证 ", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
//        finalTailTable.addCell(createCell(String.format("总金额:%s", decimalFormat.format(allamt)), textfont, Element.ALIGN_CENTER));
        finalTailTable.addCell(createCell(String.format("特别提示：退货请出示本次购买的单据。 ", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("地址：上海浦东新区南六公路699弄（两港装饰城）4支弄3幢185-191号 ", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("原东明钢材市场海昌铝业（36栋3-5号）", " "), textfont, Element.ALIGN_LEFT));
        finalTailTable.addCell(createCell(String.format("", "" ), signfont, Element.ALIGN_LEFT));
//        finalTailTable.addCell(createCell(String.format("电话：13764746926（黎女士） 13818603886（尚先生） ", " "), textfont, Element.ALIGN_LEFT));
//        finalTailTable.addCell(createCell(String.format("", "" ), textfont, Element.ALIGN_RIGHT));
        Chunk sigUnderline = new Chunk("                                            ");
        sigUnderline.setUnderline(0.2f, -2f);
        Paragraph para = new Paragraph("  电话：13764746926（黎女士） 13818603886（尚先生）                                   客户签名: ",textfont);
        para.setAlignment(0);
        para.add(sigUnderline);
//        para.setSpacingBefore(-1f);

        document.add(paragraph);
        document.add(tradeParagraph);
//        document.add(tablehead);
//        document.add(table);
        document.add(finalTailTable);
        document.add(para);

    }


/**------------------------创建表格单元格的方法start----------------------------*/
    /**
     * 创建单元格(指定字体)
     * @param value
     * @param font
     * @return
     */
    public static PdfPCell createCell(String value, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }
    /**
     * 创建单元格（指定字体、水平..）
     * @param value
     * @param font
     * @param align
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int align,int i,int j) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPhrase(new Phrase(value, font));

//        cell.disableBorderSide(15);
        return cell;
    }
//    public static PdfPCell createCell(String value, Font font, int align,int i) {
//        PdfPCell cell = new PdfPCell();
//        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPhrase(new Phrase(value, font));
//
////        cell.disableBorderSide(15);
//        return cell;
//    }
    public static PdfPCell createCell(String value, Font font, int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));

        cell.disableBorderSide(15);
        return cell;
    }
    /**
     * 创建单元格（指定字体、水平居..、单元格跨x列合并）
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int align, int colspan) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
//        cell.disableBorderSide(15);
        return cell;
    }



    /**
     * 创建单元格（指定字体、水平居..、单元格跨x列合并、设置单元格内边距）
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @param boderFlag
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int align, int colspan, boolean boderFlag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setColspan(colspan);
        cell.setPhrase(new Phrase(value, font));
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(15.0f);
            cell.setPaddingBottom(8.0f);
        } else if (boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(0.0f);
            cell.setPaddingBottom(15.0f);
        }
        return cell;
    }
    /**
     * 创建单元格（指定字体、水平..、边框宽度：0表示无边框、内边距）
     * @param value
     * @param font
     * @param align
     * @param borderWidth
     * @param paddingSize
     * @param flag
     * @return
     */
    public static PdfPCell createCell(String value, Font font, int align, float[] borderWidth, float[] paddingSize, boolean flag) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        cell.setBorderWidthLeft(borderWidth[0]);
        cell.setBorderWidthRight(borderWidth[1]);
        cell.setBorderWidthTop(borderWidth[2]);
        cell.setBorderWidthBottom(borderWidth[3]);
        cell.setPaddingTop(paddingSize[0]);
        cell.setPaddingBottom(paddingSize[1]);
        if (flag) {
            cell.setColspan(2);
        }
        return cell;
    }
/**------------------------创建表格单元格的方法end----------------------------*/
/**--------------------------创建表格的方法start------------------- ---------*/
    /**
     * 创建默认列宽，指定列数、水平(居中、右、左)的表格
     * @param colNumber
     * @param align
     * @return
     */
    public static PdfPTable createTable(int colNumber, int align) {
        PdfPTable table = new PdfPTable(colNumber);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(align);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
    /**
     * 创建指定列宽、列数的表格
     * @param widths
     * @return
     */
    public static PdfPTable createTable(float[] widths) {
        PdfPTable table = new PdfPTable(widths);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
    /**
     * 创建空白的表格
     * @return
     */
    public static PdfPTable createBlankTable() {
        PdfPTable table = new PdfPTable(1);
        table.getDefaultCell().setBorder(0);
        table.addCell(createCell("", keyfont));
        table.setSpacingAfter(20.0f);
        table.setSpacingBefore(20.0f);
        return table;
    }
/**--------------------------创建表格的方法end------------------- ---------*/

}
