package com.example.textdouban.bean;

public class MovieBean {
	
	private String title;
	private String average;
	private String year;
	private String imageUrl;
	public MovieBean(String title, String average, String year, String imageUrl) {
		super();
		this.title = title;
		this.average = average;
		this.year = year;
		this.imageUrl = imageUrl;
	}
	public MovieBean() {
		super();
	}
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
	@Override
	public String toString() {
		return "MovieBean [title=" + title + ", average=" + average + ", year="
				+ year + ", imageUrl=" + imageUrl + "]";
	}
	
	
	

}
