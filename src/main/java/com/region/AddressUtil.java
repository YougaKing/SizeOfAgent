package com.region;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.region.Province.City;
import com.region.Province.Area;
import com.region.RegionUtil.Action;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * author: zjx
 * created on: 2018/02/27 16:42
 * description: 地址相关的数据处理
 */
public class AddressUtil {

    public ArrayList<Province> mProvinceList = new ArrayList<>();

    public ArrayList<String> mProvinceNameList = new ArrayList<>();
    public ArrayList<ArrayList<String>> mCityNameList = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<String>>> mAreaNameList = new ArrayList<>();
    public HashMap<String, String> mProvinceIdName = new HashMap<>();
    public HashMap<String, String> mCityIdName = new HashMap<>();
    public HashMap<String, String> mAreaIdName = new HashMap<>();
    public HashMap<String, Integer> mProvinceIdIndex = new HashMap<>();
    public HashMap<String, Integer> mCityIdIndex = new HashMap<>();
    public HashMap<String, Integer> mAreaIdIndex = new HashMap<>();
    public HashMap<String, String> mProvinceNameId = new HashMap<>();
    public HashMap<String, String> mCityNameId = new HashMap<>();
    public HashMap<String, String> mAreaNameId = new HashMap<>();
    public HashMap<String, ArrayList<Area>> mAreaListIdName = new HashMap<>();


    public int mProvinceSelect = 0;
    public int mCitySelect = 0;
    public int mAreaSelect = 0;


    public AddressUtil() {
        String area_str = FileUtils.readAssetJson();
        try {
            mProvinceList = new Gson().fromJson(area_str, new TypeToken<ArrayList<Province>>() {
            }.getType());
            // 初始化PickerView数据
            ArrayList<City> city;
            ArrayList<Area> area;
            // 循环省份
            for (int i = 0; i < mProvinceList.size(); i++) {
                // 存入省份名字
                mProvinceNameList.add(mProvinceList.get(i).getPname());
                // 省份 key name， value id
                mProvinceNameId.put(mProvinceList.get(i).getPname(), mProvinceList.get(i).getPid());
                // 省份 key id， value name
                mProvinceIdName.put(mProvinceList.get(i).getPid(), mProvinceList.get(i).getPname());
                // 省份位置 key id, value index
                mProvinceIdIndex.put(mProvinceList.get(i).getPid(), i);
                // 获取城市对应列表
                city = mProvinceList.get(i).getCityList();
                // 创建一个存储城市的list
                ArrayList<String> city_of_province = new ArrayList<>();
                // 创建一个存储改城市内所有区的
                ArrayList<ArrayList<String>> area_list = new ArrayList<>();
                // 循环城市
                for (int j = 0; j < city.size(); j++) {
                    // 存入城市名字
                    city_of_province.add(city.get(j).getCname());
                    // 城市 key name, value id
                    mCityNameId.put(city.get(j).getCname(), city.get(j).getCid());
                    // 城市 key id, value name
                    mCityIdName.put(city.get(j).getCid(), city.get(j).getCname());
                    // 城市位置 key id, value index
                    mCityIdIndex.put(city.get(j).getCid(), j);
                    // 城市对应的区列表
                    mAreaListIdName.put(city.get(j).getCid(), city.get(j).getAreaList());
                    // 获取区对应列表
                    area = city.get(j).getAreaList();
                    // 创建一个存储区的list
                    ArrayList<String> area_of_city = new ArrayList<>();
                    // 循环区
                    for (int k = 0; k < area.size(); k++) {
                        // 存入区名字
                        area_of_city.add(area.get(k).getAname());
                        // 区 key name, value id
                        mAreaNameId.put(area.get(k).getAname(), area.get(k).getAid());
                        // 区 key id, value name
                        mAreaIdName.put(area.get(k).getAid(), area.get(k).getAname());
                        // 区位置 key id, value index
                        mAreaIdIndex.put(area.get(k).getAid(), k);
                    }
                    // 创建一个存储城市对应区的列表
                    area_list.add(area_of_city);
                }
                mCityNameList.add(city_of_province);
                mAreaNameList.add(area_list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取地址信息
     *
     * @param provinceId 省份id
     * @param cityId     城市id
     * @param areaId     区id
     * @param interval   插入字符串 example: interval="-" ----> 陕西省-西安市-高新区
     * @return 地址信息
     */
    public String getAddress(String provinceId, String cityId, String areaId, String interval) {
        return mProvinceIdName.get(provinceId) + interval + mCityIdName.get(cityId) + "市" + interval + mAreaIdName.get(areaId);
    }

    /**
     * 获取默认选择的位置
     *
     * @param provinceId 省份id
     * @param cityId     城市id
     * @param areaId     区id
     */
    public void getAddressSelect(String provinceId, String cityId, String areaId) {
        mProvinceSelect = mProvinceIdIndex.get(provinceId) == null ? 0 : mProvinceIdIndex.get(provinceId);
        mCitySelect = mCityIdIndex.get(cityId) == null ? 0 : mCityIdIndex.get(cityId);
        mAreaSelect = mAreaIdIndex.get(areaId) == null ? 0 : mAreaIdIndex.get(areaId);
    }

    public String getFirstAreaId(String cityId) {
        ArrayList<Area> list = mAreaListIdName.get(cityId);
        if (list != null && list.size() > 0) {
            return list.get(0).getAid();
        }
        return "";
    }

    public String getFirstAreaName(String cityId) {
        ArrayList<Area> list = mAreaListIdName.get(cityId);
        if (list != null && list.size() > 0) {
            return list.get(0).getAname();
        }
        return "";
    }

    public String getAddressByPosition(int provincePosition, int cityPosition, int areaPosition, Action action) {

        if (provincePosition < 0 || provincePosition >= mProvinceNameList.size()) {
            return null;
        }
        String province = mProvinceNameList.get(24);
        if (cityPosition < 0 || cityPosition >= mCityNameList.get(24).size()) {
            return null;
        }
        String city = mCityNameList.get(24).get(3);
        if (areaPosition < 0 || areaPosition >= mAreaNameList.get(24).get(3).size()) {
            return null;
        }
        String area = mAreaNameList.get(24).get(3).get(0);

        String provinceId = mProvinceNameId.get(province);
        String cityId = mCityNameId.get(city);
        String areaId = mAreaNameId.get(area);

        if (!TextUtils.isEmpty(province)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(province);

        if (!TextUtils.isEmpty(city)) {
            builder.append("-")
                    .append(city)
                    .append("市");
        }
        if (area != null && !TextUtils.isEmpty(area)) {
            builder.append("-")
                    .append(area);
        }
//        if (action != null) action.call(provinceId, cityId, areaId);
        return builder.toString();
    }
}
