package com.example.textdouban.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.example.textdouban.bean.MovieBean;

public class JsonUtil {

	public static ArrayList<MovieBean> parseJsonSousuo(String json) {
		

		ArrayList<MovieBean> movieList = null;
		try {
			JSONObject jsonobject = new JSONObject(json);
			if (jsonobject.getInt("total") != 0) {

				movieList = new ArrayList<MovieBean>();
				JSONArray jsonArray = jsonobject.getJSONArray("subjects");
				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonItem = jsonArray.getJSONObject(i);
					String name = jsonItem.getString("title");// 电影标题中文名
					String year = jsonItem.getString("year");// 电影年代
					JSONObject rating = jsonItem.getJSONObject("rating");// 评分

					double pingfen = rating.getDouble("average");
					JSONObject images = jsonItem.getJSONObject("images");
					String imageUrl = images.getString("small");
					movieList.add(new MovieBean(name, String.valueOf(pingfen),
							year, imageUrl));

				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movieList;

	}

	public static String removeBOM(String data) {
		if (TextUtils.isEmpty(data)) {
			return data;
		}

		if (data.startsWith("\ufeff")) {
			return data.substring(1);
		} else {
			return data;
		}
	}

}
