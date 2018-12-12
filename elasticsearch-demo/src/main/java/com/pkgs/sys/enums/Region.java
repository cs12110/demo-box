package com.pkgs.sys.enums;

/**
 * 定义vod发布地区枚举类型
 *
 * <p>
 * 10100: 大陆<br/>
 * 10200: 港台<br/>
 * 10300: 欧美<br/>
 * 10400: 日韩<br/>
 * 10500: 东南亚<br/>
 * 10600: 其他<br/>
 * 
 * @author huanghuapeng 2017年3月27日
 * @see
 * @since 1.0
 */
public enum Region {

	/**
	 * 中国大陆
	 */
	CHINA("中国", 10100),

	/**
	 * 香港台湾
	 */
	HK_AND_TW("港台", 10200),

	/**
	 * 欧美
	 */
	EOURPE_AND_AMERICA("欧美", 10300),

	/**
	 * 日韩
	 */
	JAPAN_AND_SOUTHKOREA("日韩", 10400),

	/**
	 * 东南亚
	 */
	SOURTHEAST_ASIA("东南亚", 10500),

	/**
	 * 其他
	 */
	OTHERS("其他", 10600);

	private Region(String key, int value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * 根据id获取地区名称
	 * 
	 * @param regionId
	 *            地区id
	 * @return String
	 */
	public static String getRegionNameById(Integer regionId) {
		for (Region e : Region.values()) {
			if (e.getValue() == regionId) {
				return e.getKey();
			}
		}
		return Region.OTHERS.getKey();
	}

	/**
	 * 
	 * @param regionCode
	 * @return
	 */
	public static String getMap(int regionCode) {
		return getRegionNameById(regionCode);
	}
	/*
	 * i18n key
	 */
	private String key;
	/*
	 * 值
	 */
	private int value;

	public String getKey() {
		return key;
	}
	public int getValue() {
		return value;
	}

}
