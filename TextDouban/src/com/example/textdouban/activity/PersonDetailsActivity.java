package com.example.textdouban.activity;

import java.util.ArrayList;

import com.example.textdouban.R;
import com.example.textdouban.adapter.PersonAdapter;
import com.example.textdouban.bean.MovieBean;
import com.example.textdouban.bean.PersonBean;
import com.example.textdouban.bean.PersonDetailsBean;
import com.example.textdouban.net.ImageLoad;
import com.example.textdouban.net.ParametersDefault;
import com.example.textdouban.utils.JsonUtil;
import com.example.textdouban.utils.MyApplication;
import com.example.textdouban.view.HorzontialListview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 演员详情界面
 * 
 * @author guopengchao 2014年7月29日 下午8:57:45
 * 
 */
public class PersonDetailsActivity extends BaseActivity implements
		OnClickListener {

	private TextView mZhName, mEgName, mDate, mAdds, mSex;
	private ImageView mImg;
	private HorzontialListview mWorks;
	private TextView mBackText, mBackOneText;

	private ArrayList<PersonBean> worksList = new ArrayList<PersonBean>();
	private PersonAdapter worksAdapter;
	private String url = "https://api.douban.com/v2/movie/celebrity/";

	private MyApplication myApp = MyApplication.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_details_activity);
		myApp.addActivity(this);
		setin();

		worksAdapter = new PersonAdapter(this, worksList);
		mWorks.setAdapter(worksAdapter);

		Intent intent = getIntent();
		String id = intent.getStringExtra("id");

		ParametersDefault params = new ParametersDefault();

		getResponseData(102, url + id, params, "GET");

		// 演员作品监听
		mWorks.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("_id", worksList.get(arg2).getId());
				intent.setClass(PersonDetailsActivity.this,
						MovieDetailsActivity.class);
				startActivity(intent);

			}
		});
	}

	// 实例化控件
	private void setin() {
		// TODO Auto-generated method stub
		mZhName = (TextView) findViewById(R.id.person_details_zh_name);
		mEgName = (TextView) findViewById(R.id.person_details_eg_name);
		mDate = (TextView) findViewById(R.id.person_details_deta);
		mAdds = (TextView) findViewById(R.id.person_details_adds);
		mSex = (TextView) findViewById(R.id.person_details_sex);
		mImg = (ImageView) findViewById(R.id.person_details_img);
		mWorks = (HorzontialListview) findViewById(R.id.person_details_listview);

		mBackText = (TextView) findViewById(R.id.top_top_back);
		mBackText.setOnClickListener(this);
		mBackOneText = (TextView) findViewById(R.id.top_top_back_one);
		mBackOneText.setOnClickListener(this);

	}

	@Override
	public void onHandleMessag(Message msg) {
		// TODO Auto-generated method stub
		switch (msg.what) {
		case 102:
			Bundle bundle = msg.getData();
			String json = bundle.getString("json");
			Log.d("json==Message======", json);
			worksList.clear();

			PersonDetailsBean personDetailsBean = new PersonDetailsBean();
			JsonUtil.parseJsonMoviePerson(json, worksList, personDetailsBean);
			setPersonMessage(personDetailsBean);
			worksAdapter.addData(worksList);
			worksAdapter.notifyDataSetChanged();// 更新数据
		}
	}

	private void setPersonMessage(PersonDetailsBean personDetailsBean) {
		// TODO Auto-generated method stub
		mZhName.setText(personDetailsBean.getZh_name());
		mEgName.setText(personDetailsBean.getEg_name());
		mDate.setText("出生日期：" + personDetailsBean.getDate());
		mAdds.setText("出生地：" + personDetailsBean.getAdds());
		mSex.setText("性别：" + personDetailsBean.getSex());
		ImageLoad.setImage(personDetailsBean.getImgUrl(), mImg);
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
