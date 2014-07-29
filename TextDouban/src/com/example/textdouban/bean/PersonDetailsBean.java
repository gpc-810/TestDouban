package com.example.textdouban.bean;

public class PersonDetailsBean {
	
	private String id;
	private String imgUrl;
	private String zh_name;
	private String eg_name;
	private String adds;
	private String date="";
	private String sex;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZh_name() {
		return zh_name;
	}
	public void setZh_name(String zh_name) {
		this.zh_name = zh_name;
	}
	public String getEg_name() {
		return eg_name;
	}
	public void setEg_name(String eg_name) {
		this.eg_name = eg_name;
	}
	public String getAdds() {
		return adds;
	}
	public void setAdds(String adds) {
		this.adds = adds;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public PersonDetailsBean(String id, String imgUrl, String zh_name,
			String eg_name, String adds, String date, String sex) {
		super();
		this.id = id;
		this.imgUrl = imgUrl;
		this.zh_name = zh_name;
		this.eg_name = eg_name;
		this.adds = adds;
		this.date = date;
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "PersonDetailsBean [id=" + id + ", imgUrl=" + imgUrl
				+ ", zh_name=" + zh_name + ", eg_name=" + eg_name + ", adds="
				+ adds + ", date=" + date + ", sex=" + sex + "]";
	}
	public PersonDetailsBean() {
		super();
	}
	

}
