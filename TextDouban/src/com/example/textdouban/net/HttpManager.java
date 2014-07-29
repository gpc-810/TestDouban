package com.example.textdouban.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;


public class HttpManager {
	// final String BOUNDARY = "---------------------------7da2137580612";
	// //数据分隔线
	// final String endline = "--" + BOUNDARY + "--\r\n";//数据结束标志

	private static final String BOUNDARY = UUID.randomUUID().toString();
	private static final String MP_BOUNDARY = "--" + BOUNDARY;
	private static final String END_MP_BOUNDARY = "--" + BOUNDARY + "--\r\n";
	private final static String UTF_8 = "UTF-8";
	private final static int TIMEOUT_CONNECTION = 8000;
	private final static int TIMEOUT_SOCKET = 8000;
	private final static int TIMEOUT_CONN = 8000;
	private final static int MAX_CONNECTION_COUNT = 400;
	static HttpParams param;

	static HttpClient getHttpCilent() {
		param = new BasicHttpParams();

		// 设置字符集
		HttpProtocolParams.setContentCharset(param, UTF_8);

		// 设置http协议版本
		HttpProtocolParams.setVersion(param, HttpVersion.HTTP_1_1);

		// 设置是否验证请求 设为true会导致两次连接请求
		HttpProtocolParams.setUseExpectContinue(param, false);

		// 设置连接池取连接超时时间
		ConnManagerParams.setTimeout(param, TIMEOUT_CONN);

		// 设置最大连接数
		ConnPerRouteBean cRouteBean = new ConnPerRouteBean(MAX_CONNECTION_COUNT);
		ConnManagerParams.setMaxConnectionsPerRoute(param, cRouteBean);

		// 设置连接超时
		HttpConnectionParams.setConnectionTimeout(param, TIMEOUT_CONNECTION);

		// 设置读取数据超时
		HttpConnectionParams.setSoTimeout(param, TIMEOUT_SOCKET);

		// 设置连接支持http与https两种协议
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory
				.getSocketFactory(), 443));
		// 使用线程安全的管理创建httpClient
		ClientConnectionManager ccm = new ThreadSafeClientConnManager(param,
				schemeRegistry);
		DefaultHttpClient hr = new DefaultHttpClient(ccm, param);
		return hr;

	}

	public static String openUrl(String url, String method,
			ParametersDefault params, InputStream input) throws Exception {
		String result = "";

		HttpClient client = getHttpCilent();
		HttpUriRequest request = null;
		ByteArrayOutputStream bos = null;

		if ("GET".equals(method)) {
			if (encodeUrl(params) != null) {
				url = url + (url.contains("?") ? "&" : "?") + encodeUrl(params);
			}
			Log.d("xxx", url);
			HttpGet get = new HttpGet(url);
			request = get;
		} else if ("POST".equals(method)) {

			HttpPost post = new HttpPost(url);
			// List<BasicNameValuePair> parameters = new
			// ArrayList<BasicNameValuePair>();
			// if (params != null)
			//
			// for (int i = 0; i < params.size(); i++) {
			//
			// parameters.add(new BasicNameValuePair(params.getKey(i),
			// params.getValue(i)));
			// }
			// HttpEntity entity = new UrlEncodedFormEntity(parameters);
			// post.setEntity(entity);

			byte[] data = null;
			bos = new ByteArrayOutputStream();
			if (input != null) {
				paramToUpload(bos, params);
				post.setHeader("Content-Type", "multipart/form-data; boundary="
						+ BOUNDARY);
				imageContentToUpload(bos, input);

				// SimpleMultipartEntity entity1 = new SimpleMultipartEntity();
				// for (int i = 0; i < params.size(); i++) {
				// if (params.getKey(i).equals("avatars")) {
				// entity1.addPart("avatars", "avatars.png", input, true);
				// continue;
				// }
				// entity1.addPart(params.getKey(i), params.getValue(i));
				// }
				//
				// post.setEntity(entity1);
				// request = post;
				// HttpResponse response = client.execute(post);
				// result = readHttpResponse(response);
				// return result;
			} else {
				post.setHeader("Content-Type",
						"application/x-www-form-urlencoded");
				String postParam = encodeParameters(params);
				data = postParam.getBytes("UTF-8");
				bos.write(data);
			}
			data = bos.toByteArray();
			bos.close();
			ByteArrayEntity formEntity = new ByteArrayEntity(data);
			post.setEntity(formEntity);
			request = post;
		} else {
			return "没有设置正确的请求链接模式";
		}
		request.setParams(param);
		HttpResponse response = client.execute(request);
		result = readHttpResponse(response);
		return result;

	}

	private static String readHttpResponse(HttpResponse response)
			throws Exception {
		String result = "";
		HttpEntity entity = response.getEntity();
		result = EntityUtils.toString(entity);
		return result;
	}

	public static String encodeUrl(ParametersDefault parameters) {
		if (parameters == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (int loc = 0; loc < parameters.size(); loc++) {
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			// sb.append("&");
			String _key = parameters.getKey(loc);
			String _value = parameters.getValue(_key);
			if (_value == null) {
				Log.i("encodeUrl", "key:" + _key + " 's value is null");
			} else {
				sb.append(URLEncoder.encode(parameters.getKey(loc)) + "="
						+ URLEncoder.encode(parameters.getValue(loc)));
			}
			Log.i("encodeUrl", sb.toString());
		}
		return sb.toString();
	}

	private static void paramToUpload(OutputStream baos,
			ParametersDefault params)

	{
		String key = "";
		for (int loc = 0; loc < params.size(); loc++) {
			if (params.getKey(loc).equals("pic")) {
				continue;
			}
			key = params.getKey(loc);
			StringBuilder temp = new StringBuilder(10);
			temp.setLength(0);
			temp.append(MP_BOUNDARY).append("\r\n");
			temp.append("content-disposition: form-data; name=\"").append(key)
					.append("\"\r\n\r\n");
			temp.append(params.getValue(key)).append("\r\n");
			byte[] res = temp.toString().getBytes();
			try {
				baos.write(res);
			} catch (IOException e) {
				Log.d("xxx", "paramToUpload+++" + e.toString());
			}
		}
	}

	private static void imageContentToUpload(OutputStream out, InputStream input) {
		if (input == null) {
			return;
		}
		StringBuilder temp = new StringBuilder();

		temp.append(MP_BOUNDARY).append("\r\n");
		temp.append("Content-Disposition: form-data; name=\"pic\"; filename=\"")
				.append("pic.jpg").append("\"\r\n");
		String filetype = "application/octet-stream";
		temp.append("Content-Type: ").append(filetype).append("\r\n\r\n");

		byte[] res = temp.toString().getBytes();
		try {
			String valu = "";
			out.write(res);
			byte[] buffer = new byte[51200];
			while (true) {
				int count = input.read(buffer);
				if (count == -1) {
					break;
				}
				valu += new String(buffer, 0, count);
				out.write(buffer, 0, count);
			}
			out.write(("\r\n" + END_MP_BOUNDARY).getBytes());
		} catch (IOException e) {
			Log.d("xxx", "IOException+++" + e.toString());
		} finally {
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					Log.d("xxx", "IOException+++" + e.toString());
				}
		}
	}

	public static String encodeParameters(ParametersDefault httpParams) {
		if ((httpParams == null) || (httpParams.size() < 1)) {
			return "";
		}
		StringBuilder buf = new StringBuilder();
		int j = 0;
		for (int loc = 0; loc < httpParams.size(); loc++) {
			String key = httpParams.getKey(loc);
			if (j != 0)
				buf.append("&");
			try {
				buf.append(URLEncoder.encode(key, "UTF-8"))
						.append("=")
						.append(URLEncoder.encode(httpParams.getValue(key),
								"UTF-8"));
			} catch (Exception e) {
			}
			j++;
		}
		return buf.toString();
	}
}
