package com.example.textdouban.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
/**
 * 判断网络是否连接，成功连接返回true；
 * @author guopengchao  2014年7月29日 下午1:55:50
 *
 */
public class NetworkUtil {

	public static boolean isNetwork(Context context){
		ConnectivityManager conn=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info=conn.getActiveNetworkInfo();
		if(info!=null&&info.isAvailable())
			return true;
		return false;
	}
	
	/**
	 * 打开网络对话框
	 */
	public static void whetherOpenNet(final Context context) {
		new AlertDialog.Builder(context)
				.setTitle("网络没有连接！")
				.setMessage("是否打开网络连接？")
				.setPositiveButton(android.R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								context.startActivity(new Intent(
										Settings.ACTION_WIRELESS_SETTINGS));
							}
						}).setNeutralButton(android.R.string.cancel, null)
				.show();
	}
}