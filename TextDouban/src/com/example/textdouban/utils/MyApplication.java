package com.example.textdouban.utils;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {

	private List activitys = null;
	private static MyApplication instance;

	private MyApplication() {
		activitys = new LinkedList();
	}

	/**
	 * 单例模式中获取唯一的MyApplication实例
	 * 
	 * @return
	 */
	public static MyApplication getInstance() {
		if (null == instance) {
			instance = new MyApplication();
		}
		return instance;

	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		if (activitys != null && activitys.size() > 0) {
			if (!activitys.contains(activity)) {
				activitys.add(activity);
			}
		} else {
			activitys.add(activity);
		}

	}

	// 遍历所有Activity并finish
	public void exit() {
		if (activitys != null && activitys.size() > 0) {
			for (int i = 0; i < activitys.size(); i++) {
				Activity aty = (Activity) activitys.get(i);
				aty.finish();
			}
		}
		System.exit(0);
	}

	public void changeOne() {
		if (activitys != null && activitys.size() > 1) {
			for (int i = 1; i < activitys.size(); i++) {
				Activity aty = (Activity) activitys.get(i);
				aty.finish();
				activitys.remove(i);
			}
		}

	}

	public void finishTop() {
		if (activitys != null && activitys.size() > 0) {

			Activity aty = (Activity) activitys.get(activitys.size() - 1);
			aty.finish();
			activitys.remove(activitys.size()-1);
		}

	}

}
