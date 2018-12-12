package org.md.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射工具类
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午9:15:13
 *
 */
public class RefUtil {

	/**
	 * 反射缓存
	 */
	private static Map<String, List<Field>> REFLECT_CACHE = new HashMap<String, List<Field>>();

	/**
	 * 转换成bean
	 * 
	 * @param clazz
	 *            实体类对象
	 * @param parameterMap
	 *            request的值
	 * @return T
	 */
	public static <T> T parseToBean(Class<T> clazz, Map<String, String[]> parameterMap) {
		List<Field> fiedList = getAllFieldFromClass(clazz);
		T instance = null;
		try {
			instance = clazz.newInstance();
			for (int index = 0, size = fiedList.size(); index < size; index++) {
				Field f = fiedList.get(index);
				String key = f.getName();
				String[] valArr = parameterMap.get(key);
				String firstVal = valArr != null && valArr.length > 0 ? valArr[0].trim() : null;
				if (null != firstVal) {
					f.set(instance, str2Any(f.getType(), firstVal));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}

	/**
	 * 将字符串转成实际类型
	 * 
	 * @param realType
	 *            类型class
	 * @param origin
	 *            字符串
	 * @return Object
	 */
	private static Object str2Any(Class<?> realType, String origin) {
		if (Integer.class == realType) {
			return "".equals(origin) ? null : Integer.parseInt(origin);
		}
		return origin;
	}

	/**
	 * 获取实体类里面所有的声明属性
	 * 
	 * @param clazz
	 *            clazz
	 * @return List
	 */
	private static List<Field> getAllFieldFromClass(Class<?> clazz) {
		List<Field> fieldList = REFLECT_CACHE.get(clazz.getName());
		if (null != fieldList) {
			return fieldList;
		}
		fieldList = new ArrayList<Field>();
		try {
			Class<?> tmp = clazz;
			while (tmp != Object.class) {
				for (Field f : clazz.getDeclaredFields()) {
					f.setAccessible(true);
					fieldList.add(f);
				}
				tmp = clazz.getSuperclass();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		REFLECT_CACHE.put(clazz.getName(), fieldList);
		return fieldList;
	}

}
