package org.zucc.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userId;

    private String userName;

    private String userPassword;

    private String userTelphone;

    private String userRealname;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public User(String userName, String userPassword, String userTelphone,
			String userRealname) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userTelphone = userTelphone;
		this.userRealname = userRealname;
	}

	public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserTelphone() {
        return userTelphone;
    }

    public void setUserTelphone(String userTelphone) {
        this.userTelphone = userTelphone == null ? null : userTelphone.trim();
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname == null ? null : userRealname.trim();
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userRealname="
				+ userRealname + ", userTelphone=" + userTelphone + "]";
	}

	public User() {
		super();
	}
}