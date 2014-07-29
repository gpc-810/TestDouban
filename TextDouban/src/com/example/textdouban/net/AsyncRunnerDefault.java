package com.example.textdouban.net;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.util.Log;
/**
 * 异步请求
 * @author guopengchao  2014年7月29日 下午1:40:43
 *
 */
public class AsyncRunnerDefault {
	public static void request(final int tag, final String url,
			final ParametersDefault params, final String method,
			final RequestListener listener) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		es.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					InputStream input = null;
					if (params != null) {
						String avatars = params.getValue("pic");
						if (avatars != null && !"".equals(avatars)) {
							input = new ByteArrayInputStream(
									ParametersDefault.avatar);

						}

					}
					String json = HttpManager.openUrl(url, method, params,
							input);
					Log.d("JSON===", json);
					listener.onComplete(tag, json);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					listener.onException(e.toString());
				}
			}
		});
	}
}
