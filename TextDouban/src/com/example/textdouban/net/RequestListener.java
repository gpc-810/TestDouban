package com.example.textdouban.net;
/**
 * 数据请求接口
 * @author guopengchao  2014年7月29日 下午1:54:46
 *
 */
public interface RequestListener {
	public void onComplete(int tag,String json);

	public void onException(String json);
}
