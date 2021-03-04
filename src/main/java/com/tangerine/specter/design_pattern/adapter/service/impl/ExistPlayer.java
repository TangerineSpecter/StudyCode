package com.tangerine.specter.design_pattern.adapter.service.impl;

public class ExistPlayer {

    /**
     * 播放MP3
     *
     * @param filename 文件
     */
    public void playMp3(String filename) {
        System.out.println("play mp3 : " + filename);
    }

    /**
     * 播放Wma
     *
     * @param filename 文件
     */
    public void playWma(String filename) {
        System.out.println("play wma : " + filename);
    }
}
