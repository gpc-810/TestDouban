package com.example.textdouban.bean;

public class PersonBean {
	private String id;
	private String name;
	private String imageUrl;
	public PersonBean(String id, String name, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.imageUrl = imageUrl;
	}
	public PersonBean() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Override
	public String toString() {
		return "PersonBean [id=" + id + ", name=" + name + ", imageUrl="
				+ imageUrl + "]";
	}
	
	

}
