package demo.entity;

import org.bson.Document;

public class User {
	/**
	 * 用户Id
	 */
	private String userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户电话
	 */
	private String phone;

	/**
	 * 将当前用户对象转化成document对象
	 * 
	 * @return Document
	 */
	public Document toDocument() {
		/*
		 * document对象和Java里面的map相似,都是按照key,value形式存放值的
		 */
		Document userDocument = new Document();

		userDocument.put("userId", userId);
		userDocument.put("userName", userName);
		userDocument.put("phone", phone);

		return userDocument;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", phone=" + phone + "]";
	}

}
