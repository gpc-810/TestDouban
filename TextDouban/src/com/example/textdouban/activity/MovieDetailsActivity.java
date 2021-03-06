package com.example.textdouban.activity;

import java.util.ArrayList;

import com.example.textdouban.R;
import com.example.textdouban.adapter.PersonAdapter;
import com.example.textdouban.bean.MovieBean;
import com.example.textdouban.bean.PersonBean;
import com.example.textdouban.net.ImageLoad;
import com.example.textdouban.net.ParametersDefault;
import com.example.textdouban.utils.JsonUtil;
import com.example.textdouban.utils.MyApplication;
import com.example.textdouban.view.HorzontialListview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 电影详情界面
 * 
 * @author guopengchao 2014年7月29日 下午8:58:14
 * 
 */
public class MovieDetailsActivity extends BaseActivity implements
		OnClickListener {
	private TextView mBackText, mBackOneText, mMovieName, mMovieYear,
			mMoviePingfen, mMovieTag;
	private HorzontialListview mDirectorListView, mCastsListView;
	private ImageView mMovieImage;

	private ArrayList<PersonBean> mDirectorList = new ArrayList<PersonBean>();
	private ArrayList<PersonBean> mCastsList = new ArrayList<PersonBean>();
	private PersonAdapter mDirectorAdapter, mCastsAdapter;

	private String url = "https://api.douban.com/v2/movie/subject/";

	private MyApplication myApp = MyApplication.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_details_activity);
		myApp.addActivity(this);
		setin();
		mDirectorAdapter = new PersonAdapter(this, mDirectorList);
		mDirectorListView.setAdapter(mDirectorAdapter);
		mCastsAdapter = new PersonAdapter(this, mCastsList);
		mCastsListView.setAdapter(mCastsAdapter);
		Intent intent = getIntent();
		String id = intent.getStringExtra("_id");
		ParametersDefault params = new ParametersDefault();

		getResponseData(101, url + id, params, "GET");

		// 导演详情监听
		mDirectorListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MovieDetailsActivity.this,
						PersonDetailsActivity.class);
				intent.putExtra("id", mDirectorList.get(arg2).getId());
				startActivity(intent);
			}
		});

		// 演员详情监听
		mCastsListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MovieDetailsActivity.this,
						PersonDetailsActivity.class);
				intent.putExtra("id", mCastsList.get(arg2).getId());
				startActivity(intent);
			}
		});

	}

	private void setin() {
		// TODO Auto-generated method stub
		mBackText = (TextView) findViewById(R.id.top_top_back);
		mBackText.setOnClickListener(this);
		mBackOneText = (TextView) findViewById(R.id.top_top_back_one);
		mBackOneText.setOnClickListener(this);

		mMovieImage = (ImageView) findViewById(R.id.movie_message_img);
		mMovieName = (TextView) findViewById(R.id.movie_message_title);
		mMovieYear = (TextView) findViewById(R.id.movie_message_year);
		mMoviePingfen = (TextView) findViewById(R.id.movie_message_pingfen);
		mMovieTag = (TextView) findViewById(R.id.movie_message_tag);

		mDirectorListView = (HorzontialListview) findViewById(R.id.director_listview);
		mCastsListView = (HorzontialListview) findViewById(R.id.casts_listview);

	}

	@Override
	public void onHandleMessag(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case 101:
			Bundle bundle = msg.getData();
			String json = bundle.getString("json");
			Log.d("json==Message======", json);
			mDirectorList.clear();
			mCastsList.clear();

			MovieBean bean = new MovieBean();
			JsonUtil.parseJsonMovie(json, mDirectorList, mCastsList, bean);
			Log.d("=========", mDirectorList.toString()
					+ "==--------------------------" + mCastsList.toString());
			setMovieMessage(bean);
			// mDirectorAdapter.clearData();
			mDirectorAdapter.addData(mDirectorList);
			mDirectorAdapter.notifyDataSetChanged();// 更新数据

			// mCastsAdapter.clearData();
			mCastsAdapter.addData(mCastsList);
			mCastsAdapter.notifyDataSetChanged();// 更新数据
		}
	}

	/**
	 * 设置电影信息
	 * 
	 * @param bean
	 *            return void
	 */
	private void setMovieMessage(MovieBean bean) {
		// TODO Auto-generated method stub
		Log.d("bean===", bean.toString());
		mMovieName.setText(bean.getTitle());
		mMovieYear.setText(bean.getYear());
		mMoviePingfen.setText(bean.getAverage());
		mMovieTag.setText(bean.getTag());
		ImageLoad.setImage(bean.getImageUrl(), mMovieImage);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.top_top_back:
		case R.id.top_top_back_img:
			myApp.finishTop();

			break;
		case R.id.top_top_back_one:
			myApp.changeOne();

			break;

		default:
			break;
		}
	}

}
