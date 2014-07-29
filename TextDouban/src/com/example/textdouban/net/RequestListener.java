package com.example.textdouban.net;
/**
 * 数据请求接口
 * @author junhua
 * 
 * 游戏中心6  2014-5-22下午5:43:30
 */
public interface RequestListener {
	public void onComplete(int tag,String json);

	public void onException(String json);
}
