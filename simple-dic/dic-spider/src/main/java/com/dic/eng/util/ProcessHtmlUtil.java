package com.dic.eng.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.dic.eng.entity.WordEntity;

/**
 * 处理html辅助类
 *
 * <p>
 * 
 * @author huanghuapeng 2017年12月19日下午4:33:14
 *
 */
public class ProcessHtmlUtil {

	/**
	 * 转换Html为单词
	 * 
	 * @param word
	 *            单词
	 * @param html
	 *            单词html
	 * @return {@link WordEntity}
	 */
	public static WordEntity processHtml(String word, String html) {
		Document doc = Jsoup.parse(html);
		Elements select = doc.select("#phrsListTab");

		WordEntity wordEntity = new WordEntity();
		wordEntity.word = word;

		for (Element wordElement : select) {
			Elements spells = wordElement.select(".pronounce");
			int index = 0;
			for (Element spellElement : spells) {
				if (index++ == 0) {
					String eng = spellElement.text();
					wordEntity.eng = eng.replace("英", "");
				} else {
					String usa = spellElement.text();
					wordEntity.usa = usa.replace("美", "");
				}
			}
			List<String> means = new ArrayList<String>();
			Elements meaningElement = wordElement.select("li");
			for (Element mean : meaningElement) {
				means.add(mean.text().replace("；", ";").replace("，", ","));
			}
			wordEntity.means = means;
		}

		return wordEntity;
	}

}
