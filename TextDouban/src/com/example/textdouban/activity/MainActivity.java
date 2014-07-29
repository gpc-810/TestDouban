package com.example.textdouban.activity;

import java.util.ArrayList;

import com.example.textdouban.R;
import com.example.textdouban.adapter.OneListAdapter;
import com.example.textdouban.bean.MovieBean;
import com.example.textdouban.net.ParametersDefault;
import com.example.textdouban.utils.JsonUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnClickListener {

	private EditText mSousuoText;
	private TextView mGoText;
	private ListView mOneActivityList;
	private ArrayList<MovieBean> mMovieList = new ArrayList<MovieBean>();
	private OneListAdapter mMovieAdapter;

	private String url = "https://api.douban.com/v2/movie/search?";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		setin();
		mMovieAdapter = new OneListAdapter(this, mMovieList);
		mOneActivityList.setAdapter(mMovieAdapter);
		
		mOneActivityList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.putExtra("_id", mMovieList.get(arg2).get_id());
				intent.setClass(MainActivity.this, MovieDetailsActivity.class);
				startActivity(intent);
				
			}
			
		});

	}

	private void setin() {
		// TODO Auto-generated method stub
		mSousuoText = (EditText) findViewById(R.id.sousuo_et);
		mGoText = (TextView) findViewById(R.id.sousuo_go);
		mGoText.setOnClickListener(this);
		mOneActivityList = (ListView) findViewById(R.id.one_activity_lv);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.sousuo_go:

			 ParametersDefault params = new ParametersDefault();
			 params.add("q", mSousuoText.getText().toString());
			getResponseData(100, url, params, "GET");
			

			break;

		default:
			break;
		}
	}

	@Override
	public void onHandleMessag(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case 100:
			Bundle bundle = msg.getData();
			String json = bundle.getString("json");
			Log.d("json==Message======", json);
			mMovieList.clear();
			mMovieList = JsonUtil.parseJsonSousuo(json);
			mMovieAdapter.clearData();
			mMovieAdapter.addData(mMovieList);
			mMovieAdapter.notifyDataSetChanged();// 更新数据
		}
	}

}
