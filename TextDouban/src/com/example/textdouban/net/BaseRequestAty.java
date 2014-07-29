package com.example.textdouban.net;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.textdouban.net.RequestListener;
import com.example.textdouban.net.AsyncRunnerDefault;
import com.example.textdouban.net.ParametersDefault;

/**
 * 该类是针对“Activity类” 进行请求数据的基类
 * 
 * @author junhua
 * 
 *         游戏中心6 2014-5-22下午5:49:50
 */
public abstract class BaseRequestAty extends Activity implements
		RequestListener {

	public Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 225) {
				Toast.makeText(getApplicationContext(), "请求数据失败，请从新加载数据！",
						Toast.LENGTH_SHORT).show();
			} else
				onHandleMessage(msg);
		}
	};

	/**
	 * 处理请求来的数据的抽象方法
	 * 
	 */
	public abstract void onHandleMessage(android.os.Message msg);

	@Override
	public void onComplete(int tag, String json) {
		// TODO Auto-generated method stub
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
		mHandler.sendEmptyMessage(225);
		try {
			throw new Exception("aty基类，请求数据失败" + "json==" + json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 向服务器请求数据的方法 aty基类
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
