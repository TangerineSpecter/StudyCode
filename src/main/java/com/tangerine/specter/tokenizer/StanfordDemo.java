package com.tangerine.specter.tokenizer;

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
 * Stanford分词器
 */
public class StanfordDemo implements IWordSegmenter {
	
	private static final StanfordCoreNLP CTB = new StanfordCoreNLP("StanfordCoreNLP-chinese-ctb");
	private static final StanfordCoreNLP PKU = new StanfordCoreNLP("StanfordCoreNLP-chinese-pku");
	private static final PrintStream NULL_PRINT_STREAM = new PrintStream(new NullOutputStream(), false);
	
	@Override
	public Map<String, String> segMore(String text) {
		Map<String, String> map = new HashMap<>();
		map.put("Stanford Beijing University segmentation", seg(PKU, text));
		map.put("Stanford Chinese Treebank segmentation", seg(CTB, text));
		return map;
	}
	
	private static String seg(StanfordCoreNLP stanfordCoreNLP, String text) {
		PrintStream err = System.err;
		System.setErr(NULL_PRINT_STREAM);
		Annotation document = new Annotation(text);
		stanfordCoreNLP.annotate(document);
		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
		StringBuilder result = new StringBuilder();
		for (CoreMap sentence : sentences) {
			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
				String word = token.get(CoreAnnotations.TextAnnotation.class);
				result.append(word).append(" ");
			}
		}
		System.setErr(err);
		return result.toString();
	}
	
	public static Map<String, Set<String>> contrast(String text) {
		Map<String, Set<String>> map = new LinkedHashMap<>();
		map.put("Stanford分词器", new StanfordDemo().seg(text));
		return map;
	}
	
	public static Map<String, Map<String, String>> contrastMore(String text) {
		Map<String, Map<String, String>> map = new LinkedHashMap<>();
		map.put("Stanford分词器", new StanfordDemo().segMore(text));
		return map;
	}
	
	public static void show(Map<String, Set<String>> map) {
		map.keySet().forEach(k -> {
			System.out.println(k + " 的分词结果：");
			AtomicInteger i = new AtomicInteger();
			map.get(k).forEach(v -> {
				System.out.println("\t" + i.incrementAndGet() + " 、" + v);
			});
		});
	}
	
	public static void showMore(Map<String, Map<String, String>> map) {
		map.keySet().forEach(k -> {
			System.out.println(k + " 的分词结果：");
			AtomicInteger i = new AtomicInteger();
			map.get(k).keySet().forEach(a -> System.out.println("\t" + i.incrementAndGet() + " 、【" + a + "】\t" + map.get(k).get(a)));
		});
	}
	
	public static void main(String[] args) {
		show(contrast("我有一只大花猫"));
		showMore(contrastMore("我有一只大花猫"));
	}
}
