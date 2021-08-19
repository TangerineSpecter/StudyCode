package com.tangerine.specter.util;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * 谷歌thumbnailator 图片处理工具包
 */
public class ThumbnailatorUtils {


    public static void main(String[] args) throws IOException {
        thumbnailPic();
    }

    /**
     * 缩略图演示
     * @throws IOException
     */
    public static void thumbnailPic() throws IOException {
        Thumbnails.of(new File("D:\\photo.jpg"))
                .size(160, 160)
                .toFile(new File("D:\\newPhoto.jpg"));
    }

}
