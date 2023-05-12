package com.tangerine.specter.tokenizer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.file.FileReader;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.TextRankKeyword;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import edu.stanford.nlp.io.NullOutputStream;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
        segment.stream().forEach(term -> {
            if(term.nature.equals(Nature.w) ) {
                System.out.println("jump");
                return;
            }
//            System.out.println(term.nature);
            System.out.println(term.word);
        });
        final Segment sg = HanLP.newSegment();
//        System.out.println(sg.seg(text));
//        System.out.println("---关键词---");
//        System.out.println(HanLP.extractKeyword(text, 10));
//        System.out.println("---内容摘要---");
//        HanLP.extractSummary(text, 10).forEach(System.out::println);
//        sg.enableNameRecognize(true);
        sg.enablePartOfSpeechTagging(false);
        sg.seg(text).forEach(term -> {
//            System.out.println(term);
//			System.out.println(term.word);
		});
        return CollUtil.newHashMap();
    }

    public static void main(String[] args) {
        HandLpDemo demo = new HandLpDemo();
//        String text = "你好，我是高启强，我已顺利越狱，现在外面躲风头。如果你愿意在困难时期给我一点支持，等我回去东山再起，就把白金瀚送给你，外加强盛集团20%的股份。V我50请我吃一顿肯德基疯狂星期四套餐，我们一起打江山。";
//        String text1 = "我是流浪地球导演郭帆，现在系列第三部紧张筹备中，剧组赤字严重，急需用钱！需要你V我50，我给你一个KFC群演";
//        final FileReader fileReader = new FileReader("/Users/zhouliangjun/Documents/纪要录制内容文本过滤.txt");
        final FileReader fileReader = new FileReader("/Users/zhouliangjun/Documents/腐败持久战.txt");
        final Map<String, String> stringStringMap = demo.segMore(fileReader.readString());
        System.out.println(stringStringMap);
    }

}
