package com.ssm.domain;

public class User {
	private Integer id;
	private Integer cid;
	private String fans_id;
	private String focus_id;
	private String nickname;
	private String headimg;
	private String open_id;
	
	public String getName() {
		return nickname;
	}
	public void setName(String name) {
		this.nickname = name;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getFansid() {
		return fans_id;
	}
	public void setFansid(String fans_id) {
		this.fans_id = fans_id;
	}
	public String getFocusid() {
		return focus_id;
	}
	public void setFocusid(String focus_id) {
		this.focus_id = focus_id;
	}
	public String getOpenid() {
		return open_id;
	}
	public void setOpenid(String open_id) {
		this.open_id = open_id;
	}

}
