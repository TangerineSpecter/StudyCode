package com.tangerine.specter.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 谷歌thumbnailator 图片处理工具包
 */
public class ThumbnailatorUtils {


    public static void main(String[] args) throws IOException {
        String sourcePic = "D:\\photo.jpg";
        String targetPic = "D:\\newPhoto.jpg";
        thumbnailPic(sourcePic, targetPic + "1");
        scalePic(sourcePic, targetPic + "2", 0.25f);
        rotatePic(sourcePic, targetPic + "3", 180);
        regionCenterPic(sourcePic, targetPic + "4", Positions.CENTER, 400, 400);
    }

    /**
     * 缩略图演示
     *
     * @param sourcePic 源图片地址
     * @param targetPic 目标图片地址
     */
    public static void thumbnailPic(String sourcePic, String targetPic) throws IOException {
        Thumbnails.of(new File(sourcePic))
                .size(160, 160)
                .toFile(new File(targetPic));
    }


    /**
     * 按照比例进行缩放
     *
     * @param sourcePic 源图片地址
     * @param targetPic 目标图片地址
     * @param scale     比例
     */
    public static void scalePic(String sourcePic, String targetPic, Float scale) throws IOException {
        Thumbnails.of(new File(sourcePic))
                .scale(scale)
                .toFile(new File(targetPic));
    }

    /**
     * 旋转图片缩放
     *
     * @param sourcePic 源图片地址
     * @param targetPic 目标图片地址
     * @param rotate    角度 正数顺时针，负数逆时针
     */
    public static void rotatePic(String sourcePic, String targetPic, Integer rotate) throws IOException {
        Thumbnails.of(new File(sourcePic))
                .rotate(rotate)
                .toFile(new File(targetPic));
    }

    /**
     * 图片添加水印
     *
     * @param sourcePic    源图片地址
     * @param targetPic    目标图片地址
     * @param watermarkPic 水印图
     * @param position     位置
     * @param opacity      透明度
     */
    public static void watermarkPic(String sourcePic, String targetPic, Position position, BufferedImage watermarkPic, float opacity) throws IOException {
        Thumbnails.of(new File(sourcePic))
                .watermark(Positions.CENTER, watermarkPic, 0.5f)
                .toFile(new File(targetPic));
    }

    /**
     * 根据位置裁剪图片
     *
     * @param sourcePic 源图片地址
     * @param targetPic 目标图片地址
     * @param position  位置
     * @param width     宽度
     * @param height    高度
     */
    public static void regionCenterPic(String sourcePic, String targetPic, Position position, Integer width, Integer height) throws IOException {
        Thumbnails.of(new File(sourcePic))
                .sourceRegion(position, width, height)
                .toFile(new File(targetPic));
    }

    /**
     * 转换图片格式
     *
     * @param sourcePic 源图片地址
     * @param targetPic 目标图片地址
     * @param format    压缩格式
     */
    public static void formatPic(String sourcePic, String targetPic, String format) throws IOException {
        Thumbnails.of(new File(sourcePic))
                .outputFormat(format)
                .toFile(new File(targetPic));
    }

    /**
     * 输出到OutputStream
     *
     * @param sourcePic 源图片地址
     * @param targetPic 目标图片地址
     */
    public static OutputStream outputStreamPic(String sourcePic, String targetPic) throws IOException {
        OutputStream os = new FileOutputStream(new File(targetPic));
        Thumbnails.of(new File(sourcePic)).size(1280, 1024).toOutputStream(os);
        return os;
    }

    /**
     * 输出到BufferedImage
     *
     * @param sourcePic 源图片地址
     * @param targetPic 目标图片地址
     */
    public static BufferedImage bufferedImagePic(String sourcePic, String targetPic) throws IOException {
        BufferedImage bufferedImage = Thumbnails.of(new File(sourcePic)).size(1280, 1024).asBufferedImage();
        ImageIO.write(bufferedImage, "jpg", new File(targetPic));
        return bufferedImage;
    }
}
