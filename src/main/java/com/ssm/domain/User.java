package com.ssm.domain;

public class User {
	private Integer id;
	private String nickname;
	private String number;
	private String password;
	private String address;
	private String time;
	private Integer sex;
	private String openid;
	
//	实例化对象,通过new User(),不需要这个？？？有这个会出错。。
//    public User(Integer id,String nickname,String number,String password,String address) {
//        super();
//        this.id = id;
//        this.nickname = nickname;
//        this.number = number;
//        this.password = password;
//        this.address = address;
//    }
//   使用快捷键 art+shirft+s+r生成构造方法
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
}
