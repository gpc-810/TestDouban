package com.example.textdouban.adapter;

import java.util.ArrayList;

import com.example.textdouban.R;
import com.example.textdouban.bean.MovieBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OneListAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<MovieBean> movieList = new ArrayList<MovieBean>();

	private LayoutInflater inflater;

	public OneListAdapter(Context context, ArrayList<MovieBean> movieList) {

		this.context = context;
		this.movieList = movieList;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return movieList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return movieList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.one_listview_item, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.oneactivity_movie_name);
			holder.pingfen = (TextView) convertView
					.findViewById(R.id.oneactivity_movie_pingfen);
			holder.year = (TextView) convertView
					.findViewById(R.id.oneactivity_movie_year);
			holder.image = (ImageView) convertView
					.findViewById(R.id.oneactivity_movie_img);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		MovieBean bean = movieList.get(arg0);

		holder.name.setText(bean.getTitle());
		holder.year.setText(bean.getYear());
		holder.pingfen.setText(bean.getAverage());

		return convertView;
	}

	public void addData(ArrayList<MovieBean> movieList) {
		this.movieList.addAll(movieList);
	}

	public void clearData() {
		this.movieList.clear();
	}

	private static class ViewHolder {
		private TextView name;
		private TextView pingfen;
		private TextView year;
		private ImageView image;

	}

}
