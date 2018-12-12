package com.dic.eng.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 合并文件
 *
 * <p>
 * 
 * @author huanghuapeng 2017年12月19日下午5:35:29
 *
 */
public class MergeFileUtil {

	/**
	 * 合并文件
	 * 
	 * @param mergePath
	 *            合并目录
	 * @param savePath
	 *            存放合并后内容
	 */
	public static void merge(String mergePath, String savePath) {
		try {
			File file = new File(mergePath);
			File allCet4File = new File(savePath);

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(allCet4File));

			File[] files = file.listFiles();
			for (File each : files) {
				if (!each.getName().startsWith("dic-")) {
					continue;
				}
				BufferedReader bufferedReader = new BufferedReader(new FileReader(each));
				String line;
				while (null != (line = bufferedReader.readLine())) {
					bufferedWriter.write(line);
					bufferedWriter.write(System.lineSeparator());
				}
				bufferedWriter.flush();
				bufferedReader.close();

				each.delete();
			}

			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
