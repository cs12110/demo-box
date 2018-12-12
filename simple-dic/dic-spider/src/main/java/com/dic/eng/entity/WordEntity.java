package com.dic.eng.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 单词实体类
 *
 * <p>
 * 
 * @author huanghuapeng 2017年12月19日下午4:03:48
 *
 */
public class WordEntity {

	/**
	 * 单词
	 */
	public String word;

	/**
	 * 英式读音
	 */
	public String eng;

	/**
	 * 美式读音
	 */
	public String usa;

	/**
	 * 单词意思
	 */
	public List<String> means;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
