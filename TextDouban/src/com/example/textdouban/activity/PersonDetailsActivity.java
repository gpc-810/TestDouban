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
import com.example.textdouban.view.HorzontialListview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonDetailsActivity extends BaseActivity {

	private TextView mZhName, mEgName, mDate, mAdds, mSex;
	private ImageView mImg;
	private HorzontialListview mWorks;

	private ArrayList<PersonBean> worksList = new ArrayList<PersonBean>();
	private PersonAdapter worksAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_details_activity);
		setin();

		worksAdapter = new PersonAdapter(this, worksList);
		mWorks.setAdapter(worksAdapter);

		Intent intent = getIntent();
		String id = intent.getStringExtra("id");

		ParametersDefault params = new ParametersDefault();
		String url = "https://api.douban.com/v2/movie/celebrity/1054395";
		getResponseData(102, url, params, "GET");
	}

	private void setin() {
		// TODO Auto-generated method stub
		mZhName = (TextView) findViewById(R.id.person_details_zh_name);
		mEgName = (TextView) findViewById(R.id.person_details_eg_name);
		mDate = (TextView) findViewById(R.id.person_details_deta);
		mAdds = (TextView) findViewById(R.id.person_details_adds);
		mSex = (TextView) findViewById(R.id.person_details_sex);
		mImg = (ImageView) findViewById(R.id.person_details_img);
		mWorks = (HorzontialListview) findViewById(R.id.person_details_listview);

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
		mDate.setText("出生日期" + personDetailsBean.getDate());
		mAdds.setText("出生地" + personDetailsBean.getAdds());
		mSex.setText(personDetailsBean.getSex());
		ImageLoad.setImage(personDetailsBean.getImgUrl(), mImg);
	}

}
