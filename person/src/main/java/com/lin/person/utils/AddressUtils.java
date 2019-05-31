package com.lin.person.utils;

import com.google.gson.Gson;
import com.lin.baselib.base.BaseApplication;
import com.lin.person.entity.ProvinceBean;

import org.json.JSONArray;


import java.util.ArrayList;


/*从地址JSON文件解析得到省份、城市、区域信息*/

public final class AddressUtils {
	private static String m_JSONFileName = "address3.json";
	//省份
	private static ArrayList<ProvinceBean> mProvinces = new ArrayList<ProvinceBean>();
	//城市
	private static ArrayList<ArrayList<String> > mCitys = new ArrayList<ArrayList<String>>();
	//区
	private static ArrayList<ArrayList<ArrayList<String> > > mAreas = new ArrayList<ArrayList<ArrayList<String>>>();

	private static AddressUtils instance = new AddressUtils();
	private AddressUtils() {
		load();
	}

	private static void load() {
		mProvinces.clear();
		mCitys.clear();
		mAreas.clear();
		initJsonData();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				initJsonData();
//			}
//		}).start();
	}

	private static ArrayList<ProvinceBean> parseData(String result) {//Gson 解析
		ArrayList<ProvinceBean> detail = new ArrayList<ProvinceBean>();
		try {
			JSONArray data = new JSONArray(result);
			Gson gson = new Gson();
			for (int i = 0; i < data.length(); i++) {
				ProvinceBean entity = gson.fromJson(data.optJSONObject(i).toString(), ProvinceBean.class);
				detail.add(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
	}

	private static void initJsonData() {
		String JsonData =  GetJsonDataUtil.getJson(BaseApplication.getInstance(),"address3.json");
		//Log.i("json", JsonData);
		mProvinces = parseData(JsonData);

		for (int i=0;i<mProvinces.size();i++){//遍历省份
			ArrayList<String> CityList = new ArrayList<String>();//该省的城市列表（第二级）
			ArrayList<ArrayList<String> > Province_AreaList = new ArrayList<ArrayList<String> >();//该省的所有地区列表（第三极）

			for (int c=0; c<mProvinces.get(i).getCityList().size(); c++){//遍历该省份的所有城市
				String CityName = mProvinces.get(i).getCityList().get(c).getName();
				CityList.add(CityName);//添加城市

				ArrayList<String> City_AreaList = new ArrayList<String>();//该城市的所有地区列表

				//如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
				if (mProvinces.get(i).getCityList().get(c).getArea() == null
						||mProvinces.get(i).getCityList().get(c).getArea().size()==0) {
					City_AreaList.add("");
				}else {

					for (int d=0; d < mProvinces.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
						String AreaName = mProvinces.get(i).getCityList().get(c).getArea().get(d);

						City_AreaList.add(AreaName);//添加该城市所有地区数据
					}
				}
				Province_AreaList.add(City_AreaList);//添加该省所有地区数据
			}

			/**
			 * 添加城市数据
			 */
			mCitys.add(CityList);

			/**
			 * 添加地区数据
			 */
			mAreas.add(Province_AreaList);
		}
	}

	public static AddressUtils getInstance() {
		return instance;
	}

	public static ArrayList<ProvinceBean> getProvinces() {
		return mProvinces;
	}

	public static ArrayList<ArrayList<String> > getCitys() {
		return mCitys;
	}

	public static ArrayList<ArrayList<ArrayList<String> > > getAreas() {
		return mAreas;
	}
}
