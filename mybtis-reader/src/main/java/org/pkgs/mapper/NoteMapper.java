package org.pkgs.mapper;

import org.pkgs.bean.NoteBean;

/**
 * 
 * @author root
 *
 */
public interface NoteMapper {

	/**
	 * 根据Id获取Note
	 * 
	 * @param id
	 *            id
	 * @return {@link NoteBean}
	 */
	NoteBean selectNote(Integer id);

}
