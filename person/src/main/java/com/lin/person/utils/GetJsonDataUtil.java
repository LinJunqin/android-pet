package com.lin.person.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetJsonDataUtil {
	/**
	 * 读取json文件的数据并转化成字符串
	 * */
	public static String getJson(Context context,String fileName) {

		StringBuilder stringBuilder = new StringBuilder();
		try {
			AssetManager assetManager = context.getAssets();
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					assetManager.open(fileName)), 1024 * 1024);
			String line;
			while ((line = bf.readLine()) != null) {

				stringBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Log.i("Builder",stringBuilder.length()+"");
		return stringBuilder.toString();
	}
}
