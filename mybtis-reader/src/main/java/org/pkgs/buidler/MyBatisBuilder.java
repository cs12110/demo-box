package org.pkgs.buidler;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.pkgs.bean.NoteBean;

/**
 * 创建mybatis
 * 
 * <p/>
 * 
 * 官网: http://www.mybatis.org/mybatis-3/getting-started.html
 * 
 * @author root
 *
 */
public class MyBatisBuilder {

	public static void main(String[] args) {
		try {

			String configXmlPath = "mybatis-config.xml";
			InputStream stream = Resources.getResourceAsStream(configXmlPath);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(stream);

			SqlSession session = sqlSessionFactory.openSession();
			// NoteMapper mapper = session.getMapper(NoteMapper.class);
			// NoteBean note = mapper.selectNote(1);

			NoteBean bean = (NoteBean) session.selectOne("org.pkgs.mapper.NoteMapper.selectNote", 1);

			// System.out.println(note);
			System.out.println(bean);
			session.close();

			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
