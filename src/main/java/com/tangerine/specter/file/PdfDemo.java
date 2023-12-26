package com.tangerine.specter.file;

import cn.hutool.core.collection.CollUtil;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class PdfDemo {

    public static void main(String[] args) {
        String pdfFilePath = "";
        String dir = "";

        try {
            PDDocument document = Loader.loadPDF(new File(pdfFilePath));
            // 名为PDFRenderer的类将PDF文档呈现为AWT BufferedImage 。
            PDFRenderer renderer = new PDFRenderer(document);
            int totalPages = CollUtil.size(document.getPages());
            for (int i = 0; i < totalPages; i++) {
                BufferedImage image = renderer.renderImage(i, 2);
                ImageIO.write(image, "JPEG",new File(dir + "page-" + i +".jpg"));
                System.out.println("Image created");
            }
            //Closing the document
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
