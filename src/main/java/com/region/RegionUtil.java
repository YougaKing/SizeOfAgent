package com.region;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.region.RegionForm.Area;
import com.region.RegionForm.City;
import com.region.RegionForm.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * author: zjx
 * created on: 2018/02/27 16:42
 * description: 地址相关的数据处理
 */
public class RegionUtil {

    public ArrayList<String> mProvinceList = new ArrayList<>();
    public ArrayList<ArrayList<String>> mCityList = new ArrayList<>();
    public ArrayList<ArrayList<ArrayList<String>>> mAreaList = new ArrayList<>();
    public int mProvincePosition;
    public int mCityPosition;
    public int mAreaPosition;
    public List<Province> mProvinceModelList;

    public RegionUtil() {
        String json = FileUtils.readAssetJson();
        mProvinceModelList = new Gson().fromJson(json, new TypeToken<List<Province>>() {
        }.getType());

        if (mProvinceModelList == null) return;

        Province province;
        City city;
        Area area;
        for (int i = 0; i < mProvinceModelList.size(); i++) {
            province = mProvinceModelList.get(i);
            mProvinceList.add(province.pname);

            ArrayList<String> cityList = new ArrayList<>();
            mCityList.add(cityList);

            ArrayList<ArrayList<String>> areaListList = new ArrayList<>();
            mAreaList.add(areaListList);

            for (int j = 0; j < province.getCitylist().size(); j++) {
                city = province.getCitylist().get(j);
                cityList.add(city.cname);

                ArrayList<String> areaList = new ArrayList<>();
                areaListList.add(areaList);

                for (int k = 0; k < city.getAreaList().size(); k++) {
                    area = city.getAreaList().get(k);
                    areaList.add(area.aname);
                }
            }
        }
    }

    public Province findProvinceById(int provinceId) {
        if (mProvinceModelList == null) return null;
        for (int i = 0; i < mProvinceModelList.size(); i++) {
            Province province = mProvinceModelList.get(i);
            if (province.pid == provinceId) {
                mProvincePosition = i;
                return province;
            }
        }
        return null;
    }

    public City findCityById(Province province, int cityId) {
        if (province == null || province.getCitylist() == null) return null;
        for (int i = 0; i < province.getCitylist().size(); i++) {
            City city = province.getCitylist().get(i);
            if (city.cid == cityId) {
                mCityPosition = i;
                return city;
            }
        }
        return null;
    }


    private Area findAreaById(City city, int areaId) {
        if (city == null || city.getAreaList() == null) return null;
        for (int i = 0; i < city.getAreaList().size(); i++) {
            Area area = city.getAreaList().get(i);
            if (area.aid == areaId) {
                mAreaPosition = i;
                return area;
            }
        }
        return null;
    }


    public String getAddressById(int provinceId, int cityId, int areaId) {
        if (provinceId == 0 || cityId == 0 || areaId == 0) {
            return null;
        }
        Province province = findProvinceById(provinceId);
        City city = findCityById(province, cityId);
        Area area = findAreaById(city, areaId);
        if (province == null || TextUtils.isEmpty(province.pname)
                || city == null || TextUtils.isEmpty(city.cname)
                || area == null || TextUtils.isEmpty(area.aname)) {
            return null;
        } else {
            return String.format("%s-%s市-%s", province.pname, city.cname, area.aname);
        }
    }


    public Province findProvinceByPosition(int provincePosition) {
        if (mProvinceModelList == null) return null;
        if (provincePosition < 0 || provincePosition >= mProvinceModelList.size()) {
            return null;
        }
        return mProvinceModelList.get(provincePosition);
    }

    public City findCityByPosition(Province province, int cityPosition) {
        if (province == null || province.getCitylist() == null) return null;
        if (cityPosition < 0 || cityPosition >= province.getCitylist().size()) {
            return null;
        }
        return province.getCitylist().get(cityPosition);
    }

    private Area findAreaByPosition(City city, int areaPosition) {
        if (city == null || city.getAreaList() == null) return null;
        if (areaPosition < 0 || areaPosition >= city.getAreaList().size()) {
            return null;
        }
        return city.getAreaList().get(areaPosition);
    }

    public String getAddressByPosition(int provincePosition, int cityPosition, int areaPosition, Action action) {
        Province province = findProvinceByPosition(provincePosition);
        City city = findCityByPosition(province, cityPosition);
        Area area = findAreaByPosition(city, areaPosition);

        if (province == null || TextUtils.isEmpty(province.pname)) {
            return "";
        }
        int provinceId, cityId = 0, areaId = 0;
        StringBuilder builder = new StringBuilder();
        builder.append(province.pname);
        provinceId = province.pid;

        if (city != null && !TextUtils.isEmpty(city.cname)) {
            builder.append("-")
                    .append(city.cname)
                    .append("市");
            cityId = city.cid;
        }
        if (area != null && !TextUtils.isEmpty(area.aname)) {
            builder.append("-")
                    .append(area.aname);
            areaId = area.aid;
        }
        if (action != null) action.call(provinceId, cityId, areaId);
        return builder.toString();
    }

    public interface Action {
        void call(int provinceId, int cityId, int areaId);
    }
}
