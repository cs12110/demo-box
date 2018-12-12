package com.dic.eng.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dic.eng.entity.WordEntity;

public class JSONUtil {

	public static void main(String[] args) {
		toJSONFile("d://remain-words.txt", "d://more-json.js");
	}

	public static void toJSONFile(String wordsFilePath, String jsonFilePath) {
		try {
			File file = new File(wordsFilePath);
			File allCet4File = new File(jsonFilePath);

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(allCet4File));

			String line;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			Map<String, WordEntity> map = new HashMap<String, WordEntity>();

			while (null != (line = bufferedReader.readLine())) {
				JSONObject json = (JSONObject) JSON.parse(line);
				WordEntity entity = JSON.toJavaObject(json, WordEntity.class);
				map.put(entity.word, entity);
			}
			bufferedReader.close();

			bufferedWriter.write(JSON.toJSONString(map));
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
