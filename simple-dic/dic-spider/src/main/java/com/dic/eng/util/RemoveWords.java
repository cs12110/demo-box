package com.dic.eng.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class RemoveWords {

	public static void main(String[] args) {
		Set<String> wordset = new HashSet<String>();

		try {
			File file = new File("D:/ProTools/Code/dic-spider/src/main/resources/cet4/cet4-origin.txt");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

			String each = "";

			while (null != (each = bufferedReader.readLine())) {
				// System.out.println("\"word\":\"" + each + "\"");
				wordset.add("\"word\":\"" + each + "\"");
			}

			bufferedReader.close();

			file = new File("D:/tmp/40k-words.txt");
			bufferedReader = new BufferedReader(new FileReader(file));

			int num = 0;
			int total = 0;

			BufferedWriter bf = new BufferedWriter(new FileWriter("d:/remain-words.txt"));

			while (null != (each = bufferedReader.readLine())) {
				int index = each.lastIndexOf(",");
				String target = each.substring(index + 1, each.length() - 1);
				total++;
				if (wordset.add(target)) {
					num++;
					bf.write(each + "\n");

				}
				System.out.println(target);
			}

			bf.flush();
			bf.close();

			System.out.println(num + "/" + total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
