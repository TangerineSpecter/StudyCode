package com.tangerine.specter.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.*;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.tangerine.specter.util.model.SimpleExcelHeader;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
        String path = "/Users/zhouliangjun/Desktop/";
        simpleWrite(path, fileName, ExcelTypeEnum.XLSX, list, SimpleExcelHeader.class);
        //添加密码
//        String password = "123456";
//        encryptExcel(path + fileName + ".xlsx", path+ "simpleExcel" + "-encrypt.xlsx", password);
        setExcelReadOnly(path + fileName + ".xlsx", path+ "simpleExcel" + "-OnlyRead.xlsx");
    }

    public static void setExcelReadOnly(String inputFilePath, String outputFilePath) {
        try (Workbook workbook = new XSSFWorkbook(inputFilePath);
             FileOutputStream fileOut = new FileOutputStream(outputFilePath)) {

            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            // 设置工作表为只读
            sheet.protectSheet(""); // 你可以设置一个密码，也可以不设置

            // 将工作簿写入输出流
            workbook.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * excel添加密码
     * @param inputFilePath
     * @param outputFilePath
     * @param password
     */
    public static void encryptExcel(String inputFilePath, String outputFilePath, String password) {
        try (InputStream is = new FileInputStream(inputFilePath);
             OutputStream os = new FileOutputStream(outputFilePath)) {

            // 读取 Excel 文件
            OPCPackage opcPackage = OPCPackage.open(is);
            Workbook workbook = new XSSFWorkbook(opcPackage);

            // 创建加密文件系统
            POIFSFileSystem fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
            Encryptor encryptor = info.getEncryptor();
            encryptor.confirmPassword(password);

            // 将 Workbook 写入加密输出流
            try (OutputStream encryptedOut = encryptor.getDataStream(fs)) {
                workbook.write(encryptedOut);
            }

            // 将加密文件系统写入输出流
            fs.writeFilesystem(os);

            // 关闭资源
            workbook.close();
            opcPackage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
