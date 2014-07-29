package com.example.textdouban.net;

import java.io.InputStream;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageLoad {
	
	public static void setImage(String _url,ImageView imageView){
		// 定义一个URL
				try {
					URL url = new URL(_url);
					// 打开盖URL对应的资源的输入流
					InputStream is = url.openStream();
					// 从inputstream中解析图片
					Bitmap bitmap = BitmapFactory.decodeStream(is);
					// 使用 imageview显示图片
					imageView.setImageBitmap(bitmap);

					is.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
	}

}
