package com.tangerine.specter.tokenizer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.alibaba.fastjson.JSONObject;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import com.tangerine.specter.pojo.FundDataDTO;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * HanLp分词器
 */
public class HandLpDemo implements IWordSegmenter {

    @Override
    public Set<String> seg(String text) {
        return IWordSegmenter.super.seg(text);
    }

    @Override
    public Map<String, String> segMore(String text) {
        //标准分词
        List<Term> termList = StandardTokenizer.segment(text);
//        System.out.println(termList);
        //标准分词封装
        final List<Term> segment = HanLP.segment(text);
//        segment.stream().forEach(term -> {
//            if(term.nature.equals(Nature.w) ) {
//                System.out.println("jump");
//                return;
//            }
////            System.out.println(term.nature);
////            System.out.println(term.word);
//        });
        final Segment sg = HanLP.newSegment();
//        System.out.println(sg.seg(text));
        System.out.println("---关键词---");
        System.out.println(HanLP.extractKeyword(text, 10));
        System.out.println("---内容摘要---");
        HanLP.extractSummary(text, 10).forEach(System.out::println);
        sg.enableNameRecognize(true);
        sg.enablePartOfSpeechTagging(false);
        sg.seg(text).forEach(term -> {
//            System.out.println(term);
//			System.out.println(term.word);
        });
        return MapUtil.newHashMap();
    }

    public static void main(String[] args) {
        HandLpDemo demo = new HandLpDemo();
//        String text = "你好，我是高启强，我已顺利越狱，现在外面躲风头。如果你愿意在困难时期给我一点支持，等我回去东山再起，就把白金瀚送给你，外加强盛集团20%的股份。V我50请我吃一顿肯德基疯狂星期四套餐，我们一起打江山。";
//        String text1 = "我是流浪地球导演郭帆，现在系列第三部紧张筹备中，剧组赤字严重，急需用钱！需要你V我50，我给你一个KFC群演";
//        final FileReader fileReader = new FileReader("/Users/zhouliangjun/Documents/纪要录制内容文本过滤.txt");
        final FileReader fileReader = new FileReader("/Users/zhouliangjun/Documents/腐败持久战.txt");
//        final Map<String, String> stringStringMap = demo.segMore(fileReader.readString());
//        final List<String> strings = HanLP.extractSummary(fileReader.readString(), 10);
//        System.out.println(stringStringMap);
        System.out.println("=====分割线=====");

        final FileReader fileReader1 = new FileReader("/Users/zhouliangjun/Documents/雪球数据/雪球基金文章数据.json");
        final String jsonData = fileReader1.readString();
        final FundDataDTO fundDataVo = JSONObject.parseObject(jsonData, FundDataDTO.class);
//        System.out.println(fundDataVo);
        final List<FundDataDTO.DataFunDataDTO> data = fundDataVo.getData();
        for (FundDataDTO.DataFunDataDTO d : data) {
            System.out.println("文章标题：" + d.getTitle());
            System.out.println("音频地址：" + d.getFilename());
            System.out.println("文章内容：" + HtmlUtil.cleanHtmlTag(d.getContent()));

            String fileName = StrUtil.cleanBlank(d.getTitle());

            // 使用 FileUtil 创建文件
            File file = FileUtil.touch("/Users/zhouliangjun/Documents/雪球数据/" + fileName + ".txt");

            // 使用 BufferedWriter 写入文件内容
            BufferedWriter writer = null;
            try {
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(d.getContent());
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IoUtil.close(writer);
            }

            try {
                String comman = "pandoc {} -o {}";
                String path = "/Users/zhouliangjun/Documents/雪球数据/";
                //执行shell指令
                Process process = Runtime.getRuntime().exec(StrUtil.format(comman, path + fileName + ".txt", path + fileName + ".html"));

                // 读取命令输出
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                // 等待命令执行完成
                int exitCode = process.waitFor();
                System.out.println("命令执行完毕，退出码：" + exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
