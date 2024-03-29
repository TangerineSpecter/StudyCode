package com.tangerine.specter.util;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.metadata.data.*;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.tangerine.specter.util.model.SimpleExcelHeader;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.util.Date;
import java.util.List;

/**
 * excel工具类
 */
public class ExcelUtils {

    /**
     * 简单写入excel
     *
     * @param path
     */
    public static <T> void simpleWrite(String path, String fileName, ExcelTypeEnum excelType, List<T> data, Class<T> clazz) {
        //通过includeColumnFieldNames 指定只返回的字段名列
        //includeColumnIndexes 指定只返回的索引列
        EasyExcelFactory.write(path + fileName + excelType.getValue(), clazz)
                .excelType(excelType).inMemory(true).sheet("标签1").doWrite(data);
    }

    public static void main(String[] args) {
        List<SimpleExcelHeader> list = CollUtil.newArrayList();
        SimpleExcelHeader data = new SimpleExcelHeader();
        data.setAge(11);
        data.setName("张三");
        data.setDate(new Date());
        data.setPercent(0.34567);
        //设置超链接
        WriteCellData<String> hyperlink = new WriteCellData<>("百度网站");
        HyperlinkData hyperlinkData = new HyperlinkData();
        hyperlink.setHyperlinkData(hyperlinkData);
        data.setHyperlink(hyperlink);

        hyperlinkData.setAddress("https://www.baidu.com");
        hyperlinkData.setHyperlinkType(HyperlinkData.HyperlinkType.URL);


        //设置备注
        WriteCellData<String> comment = new WriteCellData<>("备注单元格信息");
        CommentData commentData = new CommentData();
        comment.setCommentData(commentData);
        data.setCommentData(comment);

        commentData.setAuthor("李四");
        commentData.setRichTextStringData(new RichTextStringData("这是一个备注"));
        commentData.setRelativeLastColumnIndex(1);
        commentData.setRelativeLastRowIndex(1);

        //设置公式
        WriteCellData<String> formula = new WriteCellData<>();
        FormulaData formulaData = new FormulaData();
        formula.setFormulaData(formulaData);
        data.setFormulaData(formula);

        formulaData.setFormulaValue("REPLACE(123456789,1,1,2)");

        //设置单元格样式
        WriteCellData<String> cellStyle = new WriteCellData<>("单元格样式");
        cellStyle.setType(CellDataTypeEnum.STRING);
        WriteCellStyle writeCellStyle = new WriteCellStyle();
        cellStyle.setWriteCellStyle(writeCellStyle);
        data.setCellStyle(cellStyle);

        //这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色
        writeCellStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        writeCellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());

        //设置单元格多样式，必须开启内存模式才可生效
        WriteCellData<String> richText = new WriteCellData<>();
        richText.setType(CellDataTypeEnum.RICH_TEXT_STRING);
        data.setRichText(richText);
        RichTextStringData richTextStringData = new RichTextStringData();
        richText.setRichTextStringDataValue(richTextStringData);
        richTextStringData.setTextString("红色绿色默认");

        //设置字体红色
        WriteFont redFont = new WriteFont();
        redFont.setColor(IndexedColors.RED.getIndex());
        richTextStringData.applyFont(0, 2, redFont);
        //设置字体绿色
        WriteFont greenFont = new WriteFont();
        greenFont.setColor(IndexedColors.GREEN.getIndex());
        richTextStringData.applyFont(2, 4, greenFont);

        list.add(data);
        String fileName = "simpleExcel";
        simpleWrite("/Users/zhouliangjun/Desktop/", fileName, ExcelTypeEnum.XLSX, list, SimpleExcelHeader.class);
    }
}
