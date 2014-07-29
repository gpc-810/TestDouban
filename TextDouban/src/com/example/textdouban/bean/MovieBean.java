package com.example.textdouban.bean;

public class MovieBean {
	
	private String title;
	private String average;
	private String year;
	private String imageUrl;
	private String _id;
	private String tag;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAverage() {
		return average;
	}
	public void setAverage(String average) {
		this.average = average;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "MovieBean [title=" + title + ", average=" + average + ", year="
				+ year + ", imageUrl=" + imageUrl + ", _id=" + _id + ", tag="
				+ tag + "]";
	}
	public MovieBean(String title, String average, String year,
			String imageUrl, String _id, String tag) {
		super();
		this.title = title;
		this.average = average;
		this.year = year;
		this.imageUrl = imageUrl;
		this._id = _id;
		this.tag = tag;
	}
	public MovieBean() {
		super();
	}
	
	
	
	

}
