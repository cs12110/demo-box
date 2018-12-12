package com.dic.eng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.dic.eng.task.SpiderTask;
import com.dic.eng.util.MergeFileUtil;

/**
 * 运行主方法
 *
 * <p>
 * 
 * @author huanghuapeng 2017年12月19日下午4:32:13
 *
 */
public class SpiderApp {

	/**
	 * 纯单词数据文本文件路径
	 */
	private static final String CET4_ORIGIN_FILE_PATH = "d://tmp/";

	/**
	 * 爬取数据库保存临时文件路径
	 */
	private static final String CET4_SPIDER_FILE_PATH = "d://tmp/";

	public static void main(String[] args) {
		List<String> words = getOriginWords();

		int times = 40;
		int size = words.size();
		int take = size % times == 0 ? (size / times) : (size / times + 1);

		ExecutorService executorService = new ThreadPoolExecutor(times, times * 2, 0, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(1280));

		for (int index = 0; index < times; index++) {
			int startIndex = take * index;
			int endIndex = take * (index + 1);

			if (endIndex > size) {
				endIndex = size;
			}

			List<String> subWords = words.subList(startIndex, endIndex);
			executorService.execute(new SpiderTask(CET4_SPIDER_FILE_PATH + "dic-" + index + ".txt", subWords));

		}

		/**
		 * 拒绝新任务,等待线程池线程全部执行完成
		 */
		executorService.shutdown();
		while (!executorService.isTerminated()) {

		}

		MergeFileUtil.merge(CET4_SPIDER_FILE_PATH, "d://tmp/40k-words.txt");
	}

	/**
	 * 获取所有纯单词的字符串
	 * 
	 * @return List
	 */
	private static List<String> getOriginWords() {
		List<String> words = new ArrayList<String>();
		try {
			File file = new File(CET4_ORIGIN_FILE_PATH);

			for (File eachFile : file.listFiles()) {
				FileReader fileReader = new FileReader(eachFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				String each = "";
				while (null != (each = bufferedReader.readLine())) {
					if (each.matches("^[a-zA-Z]+$"))
						words.add(each);
				}
				fileReader.close();
				bufferedReader.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return words;
	}

}
