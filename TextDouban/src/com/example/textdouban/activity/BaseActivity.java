package com.example.textdouban.activity;

import org.json.JSONObject;

import com.example.textdouban.net.AsyncRunnerDefault;
import com.example.textdouban.net.ParametersDefault;
import com.example.textdouban.net.AsyncRunnerDefault;
import com.example.textdouban.net.ParametersDefault;
import com.example.textdouban.net.RequestListener;
import com.example.textdouban.utils.NetworkUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author guopengchao 2014年7月29日 上午9:12:49
 * 
 */
public abstract class BaseActivity extends Activity implements
RequestListener{
	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			onHandleMessag(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	/**
	 * 需要重写来获取json数据的抽象方法
	 * 
	 * @param msg
	 */
	public abstract void onHandleMessag(android.os.Message msg);

	@Override
	public void onComplete(int tag, String json) {
		Message msg = mHandler.obtainMessage();
		msg.what = tag;
		Bundle data = new Bundle();
		data.putString("json", json);
		msg.setData(data);
		mHandler.sendMessage(msg);
	}

	@Override
	public void onException(String json) {
		// TODO Auto-generated method stub
		Log.d("vv", "BaseActivity+onException=="+json);
		Toast.makeText(getApplicationContext(), "服务器请求数据异常。。。", 500).show();
	}

	/**
	 * 通过该方法请求服务器数据
	 * 
	 * @param tag
	 * @param url
	 * @param params
	 * @param method
	 */
	public void getResponseData(int tag, String url, ParametersDefault params,
			String method) {

		AsyncRunnerDefault.request(tag, url, params, method, this);
	}
}
