package com.example.textdouban.interfaces;

public interface RequestListener {
	public void onComplete(int tag, String json);

	public void onException(String json);
}
