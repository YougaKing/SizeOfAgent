package com.region;

import java.util.ArrayList;

public class Province {


	public String pid;
	public String pname;
	public ArrayList<City> citylist;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public ArrayList<City> getCityList() {
		return citylist;
	}

	public void setCityList(ArrayList<City> cityList) {
		this.citylist = cityList;
	}

	public static class City{

        public String cid;
        public String cname;
        public ArrayList<Area> areaList;

        public String getCid() {
            return cid;
        }
        public void setCid(String cid) {
            this.cid = cid;
        }
        public String getCname() {
            return cname;
        }
        public void setCname(String cname) {
            this.cname = cname;
        }

        public ArrayList<Area> getAreaList() {
            return areaList;
        }

        public void setAreaList(ArrayList<Area> areaList) {
            this.areaList = areaList;
        }



    }

	/**
     * Created by zjx on 2017/11/20
     */

    public static class Area {

        public String aid;
        public String aname;

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getAname() {
            return aname;
        }

        public void setAname(String aname) {
            this.aname = aname;
        }
    }
}
