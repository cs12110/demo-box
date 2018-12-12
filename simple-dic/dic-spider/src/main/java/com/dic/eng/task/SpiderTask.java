package com.dic.eng.task;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import com.dic.eng.entity.WordEntity;
import com.dic.eng.util.ProcessHtmlUtil;
import com.dic.eng.util.SpiderUtil;

/**
 * 多线程爬取数据
 *
 * <p>
 * 
 * @author huanghuapeng 2017年12月19日下午4:48:54
 *
 */
public class SpiderTask implements Runnable {

	/**
	 * 结果保存路径
	 */
	private String path;

	/**
	 * 处理单词
	 */
	private List<String> words;

	public SpiderTask(String path, List<String> words) {
		this.path = path;
		this.words = words;
	}

	public void run() {
		String tName = Thread.currentThread().getName();
		try {
			int index = 0;
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(path)));
			for (String each : words) {
				String html = SpiderUtil.spider(each);
				if (null == html) {
					continue;
				}
				WordEntity word = ProcessHtmlUtil.processHtml(each, html);
				out.write(word.toString().getBytes());
				out.write(System.lineSeparator().getBytes());
				if (index++ % 10 == 0) {
					System.out.println(tName + " process: " + index);
					out.flush();
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(tName + " all done");
	}

}
