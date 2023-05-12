package com.tangerine.specter.util.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleExcelHeader {

    /**
     * ExcelProperty 设置excel表头
     * index设置写入的列
     * 复杂表头使用 数组的形式，同名同索引 会合并
     */
    @ExcelProperty(value = {"基本信息", "名字"}, index = 0)
    private String name;
    @ExcelProperty(value = {"基本信息", "年龄"}, index = 1)
    @ContentStyle(dataFormat = 1)
    private Integer age;

    /**
     * 数字格式化 NumberFormat
     */
    @NumberFormat("#.##%")
    @ExcelProperty("占比")
    private Double percent;

    /**
     * 时间格式化 DateTimeFormat
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty("日期")
    private Date date;


    @ExcelProperty("超链接")
    private WriteCellData<String> hyperlink;

    @ExcelProperty("备注")
    private WriteCellData<String> commentData;

    @ExcelProperty("公式")
    private WriteCellData<String> formulaData;

    @ExcelProperty("单元格样式")
    private WriteCellData<String> cellStyle;

    @ExcelProperty("单元格多样式")
    private WriteCellData<String> richText;

    /**
     * ExcelIgnore 忽略字段
     */
    @ExcelIgnore
    private String ignoreField;
}
