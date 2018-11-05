package com.region;

import java.util.ArrayList;
import java.util.List;

/**
 * author: YougaKingWu@gmail.com
 * created on: 2018/10/31 16:19
 * description:
 */
public class RegionForm {

    public int version;
    public List<Province> region;

    public static class Province {
        public int pid;
        public String pname;
        private ArrayList<City> citylist;

        public ArrayList<City> getCitylist() {
            return citylist == null ? citylist = new ArrayList<>() : citylist;
        }
    }

    public static class City {
        public int cid;
        public String cname;
        private ArrayList<Area> areaList;

        public ArrayList<Area> getAreaList() {
            return areaList == null ? areaList = new ArrayList<>() : areaList;
        }
    }

    public static class Area {
        public int aid;
        public String aname;
    }
}
