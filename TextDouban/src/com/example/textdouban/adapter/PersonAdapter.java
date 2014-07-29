package com.example.textdouban.adapter;

import java.util.ArrayList;

import com.example.textdouban.R;
import com.example.textdouban.bean.PersonBean;
import com.example.textdouban.net.ImageLoad;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 图片，姓名 adapter
 * @author guopengchao  2014年7月29日 下午8:58:47
 *
 */
public class PersonAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<PersonBean> personList;
	private LayoutInflater inflater;

	public PersonAdapter(Context context, ArrayList<PersonBean> personList) {
		this.context = context;
		this.personList = personList;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return personList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return personList.get(arg0);
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
			convertView = inflater.inflate(R.layout.person_item, null);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.person_item_img);
			holder.name = (TextView) convertView
					.findViewById(R.id.person_item_name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		PersonBean bean = personList.get(arg0);
		holder.name.setText(bean.getName());
		Log.d("urlimg=====", bean.getImageUrl());
		ImageLoad.setImage(bean.getImageUrl(), holder.image);

		return convertView;
	}

	public void addData(ArrayList<PersonBean> personList) {
		this.personList = personList;
	}

	public void clearData() {
		this.personList.clear();
	}

	public static class ViewHolder {
		private ImageView image;
		private TextView name;

	}

}
