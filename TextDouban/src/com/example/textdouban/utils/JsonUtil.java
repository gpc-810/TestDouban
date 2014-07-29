package com.example.textdouban.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import android.util.Log;

import com.example.textdouban.bean.MovieBean;
import com.example.textdouban.bean.PersonBean;

/**
 * json解析
 * 
 * @author guopengchao 2014年7月29日 下午1:55:19
 * 
 */
public class JsonUtil {

	/**
	 * 搜索json解析
	 * 
	 * @param json
	 * @return return ArrayList<MovieBean>
	 */
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
					String id = jsonItem.getString("id");

					movieList.add(new MovieBean(name, String.valueOf(pingfen),
							year, imageUrl, id,""));

				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movieList;

	}

	/**
	 * 电影信息解析
	 * @param json
	 * @param directorList
	 * @param castsList
	 * @param movieBean
	 * return void
	 */
	public static void parseJsonMovie(String json,
			ArrayList<PersonBean> directorList,
			ArrayList<PersonBean> castsList, MovieBean movieBean) {

		try {
			JSONObject jsonObject=new JSONObject(json);
			JSONObject rating = jsonObject.getJSONObject("rating");// 评分
			double pingfen = rating.getDouble("average");
			movieBean.setAverage(String.valueOf(pingfen));
			String year=jsonObject.getString("year");
			movieBean.setYear(year);
			JSONObject images = jsonObject.getJSONObject("images");
			String imageUrl = images.getString("small");
			movieBean.setImageUrl(imageUrl);
			String name=jsonObject.getString("title");//影片名字
			movieBean.setTitle(name);
			String _id=jsonObject.getString("id");
			movieBean.set_id(_id);
			JSONArray genres=jsonObject.getJSONArray("genres");
			String tag=genres.toString();
			Log.d("tag=====", tag);
			movieBean.setTag(tag);
//			movieBean=new MovieBean(name, String.valueOf(pingfen), year, imageUrl, _id, tag);
			JSONArray castsArray=jsonObject.getJSONArray("casts");
			for (int i = 0; i < castsArray.length(); i++) {
				JSONObject jsonCastsItem=castsArray.getJSONObject(i);
				JSONObject avatars=jsonCastsItem.getJSONObject("avatars");
				String castsImgUrl=avatars.getString("small");
				String castsName=jsonCastsItem.getString("name");
				String id=jsonCastsItem.getString("id");
				
				castsList.add(new PersonBean(id, castsName, castsImgUrl));
				
			}
			
			JSONArray directorArray=jsonObject.getJSONArray("directors");
			for (int i = 0; i < directorArray.length(); i++) {
				JSONObject jsonDirectorItem=directorArray.getJSONObject(i);
				JSONObject avatars=jsonDirectorItem.getJSONObject("avatars");
				String directorImgUrl=avatars.getString("small");
				String directorName=jsonDirectorItem.getString("name");
				String id=jsonDirectorItem.getString("id");
				
				directorList.add(new PersonBean(id, directorName, directorImgUrl));
				
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
