package org.md.service.note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.md.entity.FeedbackBean;
import org.md.entity.NoteBean;
import org.md.enums.StatusEnum;
import org.md.util.DateUtil;
import org.md.util.JdbcUtil;

/**
 * 笔记业务类
 *
 * <p>
 * 
 * @author cs12110 2018年3月30日上午11:01:48
 *
 */
public class NoteServiceImpl implements BasicService<NoteBean> {

	@Override
	public FeedbackBean add(NoteBean bean) {
		FeedbackBean feedback = new FeedbackBean();
		try {
			String now = DateUtil.getCurrentDateStr(null);
			String sql = "INSERT INTO note_t(title,note,crt_time,crt_user,upt_time) VALUES(?,?,?,?,?)";

			Connection conn = JdbcUtil.getDefConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, bean.getTitle());
			pstm.setString(2, bean.getNote());
			pstm.setString(3, now);
			pstm.setString(4, bean.getCreateUser());
			pstm.setString(5, now);

			if (pstm.executeUpdate() > 0) {
				feedback.setCode(StatusEnum.SUCCESS.getCode());
				feedback.setValue("Add note success");
			} else {
				feedback.setCode(StatusEnum.FAILURE.getCode());
				feedback.setValue("Add note failure");
			}
			JdbcUtil.fuckoff(conn, pstm, null);
		} catch (Exception e) {
			feedback.setCode(StatusEnum.FAILURE.getCode());
			feedback.setValue(e.getMessage());
			e.printStackTrace();
		}
		return feedback;
	}

	@Override
	public FeedbackBean delete(NoteBean bean) {
		FeedbackBean feedback = new FeedbackBean();
		try {
			String sql = "DELETE FROM note_t WHERE id = ? ";
			Connection conn = JdbcUtil.getDefConnection();

			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bean.getId());

			if (pstm.executeUpdate() > 0) {
				feedback.setCode(StatusEnum.SUCCESS.getCode());
			} else {
				feedback.setCode(StatusEnum.FAILURE.getCode());
				feedback.setValue("Delete note failure");
			}
			JdbcUtil.fuckoff(conn, pstm, null);
		} catch (Exception e) {
			feedback.setCode(StatusEnum.FAILURE.getCode());
			feedback.setValue(e.getMessage());
			e.printStackTrace();
		}
		return feedback;
	}

	@Override
	public FeedbackBean update(NoteBean bean) {
		FeedbackBean feedback = new FeedbackBean();
		try {
			String now = DateUtil.getCurrentDateStr(null);
			String sql = "UPDATE note_t SET title=?,note=?,upt_time=? WHERE id = ?";

			Connection conn = JdbcUtil.getDefConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, bean.getTitle());
			pstm.setString(2, bean.getNote());
			pstm.setString(3, now);
			pstm.setInt(4, bean.getId());

			if (pstm.executeUpdate() > 0) {
				feedback.setCode(StatusEnum.SUCCESS.getCode());
			} else {
				feedback.setCode(StatusEnum.FAILURE.getCode());
				feedback.setValue("Update note failure");
			}
			JdbcUtil.fuckoff(conn, pstm, null);
		} catch (Exception e) {
			feedback.setCode(StatusEnum.FAILURE.getCode());
			feedback.setValue(e.getMessage());
			e.printStackTrace();
		}
		return feedback;
	}

	@Override
	public FeedbackBean queryOne(Integer id) {
		FeedbackBean feedback = new FeedbackBean();
		try {
			String sql = "SELECT * FROM note_t WHERE id = ? ";
			Connection conn = JdbcUtil.getDefConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);

			ResultSet result = pstm.executeQuery();
			if (result.next()) {
				NoteBean bean = new NoteBean();
				bean.setId(id);
				bean.setNote(result.getString("note"));
				bean.setTitle(result.getString("title"));
				bean.setCreateUser(result.getString("crt_user"));
				bean.setCreateTime(result.getString("crt_time"));
				feedback.setCode(StatusEnum.SUCCESS.getCode());
				feedback.setValue(bean);
			}
			// JdbcUtil.fuckoff(conn, pstm, null);
		} catch (Exception e) {
			feedback.setCode(StatusEnum.FAILURE.getCode());
			feedback.setValue(e.getMessage());
			e.printStackTrace();
		}
		return feedback;
	}

	@Override
	public FeedbackBean queryList(NoteBean bean) {
		FeedbackBean feedback = new FeedbackBean();
		try {
			String sql = "SELECT id,title FROM note_t WHERE 1=1 ORDER BY upt_time DESC";
			List<NoteBean> beanList = new ArrayList<NoteBean>();

			Connection conn = JdbcUtil.getDefConnection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet result = pstm.executeQuery();
			while (result.next()) {
				NoteBean tmp = new NoteBean();
				tmp.setId(result.getInt("id"));
				tmp.setTitle(result.getString("title"));
				beanList.add(tmp);
			}
			pstm.close();
			// JdbcUtil.fuckoff(conn, pstm, null);
			feedback.setCode(StatusEnum.SUCCESS.getCode());
			feedback.setValue(beanList);
		} catch (Exception e) {
			feedback.setCode(StatusEnum.FAILURE.getCode());
			feedback.setValue(e.getMessage());
			e.printStackTrace();
		}
		return feedback;
	}

}
