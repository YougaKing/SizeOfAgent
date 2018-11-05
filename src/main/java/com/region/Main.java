package com.region;

import org.apache.lucene.util.RamUsageEstimator;

public class Main {

    public static void main(String[] args) {


        RegionUtil regionUtil = new RegionUtil();
        AddressUtil addressUtil = new AddressUtil();

        String[] header = new String[]{"", "TotalSize", "mProvinceList", "mProvinceNameList", "mCityNameList", "mAreaNameList",
                "mProvinceIdName", "mCityIdName", "mAreaIdName",
                "mProvinceIdIndex", "mCityIdIndex", "mAreaIdIndex",
                "mProvinceNameId", "mCityNameId", "mAreaNameId",
                "mAreaListIdName",
        };

        String[] row1 = sizeOfAddressUtil(addressUtil);
        String[] row2 = sizeOfRegionUtil(regionUtil);

        String[][] rows = new String[][]{
                row1, row2
        };
        System.out.println(new TextTable(header, rows));


        header = new String[]{"", "CreateTime", "AddressForIdTime", "AddressForPositionTime"
        };

        row1 = timeOfAddressUtil(addressUtil);
        row2 = timeOfRegionUtil(regionUtil);

        rows = new String[][]{
                row1, row2
        };
        System.out.println(new TextTable(header, rows));
    }

    public static String[] timeOfAddressUtil(AddressUtil addressUtil) {
        String[] arrays = new String[16];
        arrays[0] = "AddressUtil";
        arrays[1] = String.valueOf(98348647 / 1000F / 1000F);

        long startTime = System.nanoTime();
        addressUtil.getAddress("130000", "130800", "130828", "-");
        addressUtil.getAddressSelect("130000", "130800", "130828");
        arrays[2] = String.valueOf((System.nanoTime() - startTime) / 1000F / 1000F);


        startTime = System.nanoTime();
        addressUtil.getAddressByPosition(24, 3, 0, null);
        arrays[3] = String.valueOf((System.nanoTime() - startTime) / 1000F / 1000F);

        return arrays;
    }

    public static String[] sizeOfAddressUtil(AddressUtil addressUtil) {
        String[] arrays = new String[16];
        arrays[0] = "AddressUtil";

        //计算指定对象及其引用树上的所有对象的综合大小，单位字节
        long size = RamUsageEstimator.sizeOf(addressUtil);
        arrays[1] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mProvinceList);
        arrays[2] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mProvinceNameList);
        arrays[3] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mCityNameList);
        arrays[4] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mAreaNameList);
        arrays[5] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mProvinceIdName);
        arrays[6] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mCityIdName);
        arrays[7] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mAreaIdName);
        arrays[8] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mProvinceIdIndex);
        arrays[9] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mCityIdIndex);
        arrays[10] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mAreaIdIndex);
        arrays[11] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mProvinceNameId);
        arrays[12] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mCityNameId);
        arrays[13] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mAreaNameId);
        arrays[14] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(addressUtil.mAreaListIdName);
        arrays[15] = String.valueOf(size / 1024F);
        return arrays;
    }

    public static String[] timeOfRegionUtil(RegionUtil regionUtil) {
        String[] arrays = new String[16];
        arrays[0] = "RegionUtil";
        arrays[1] = String.valueOf(97961942 / 1000F / 1000F);

        long startTime = System.nanoTime();
        regionUtil.getAddressById(130000, 130800, 130828);
        arrays[2] = String.valueOf((System.nanoTime() - startTime) / 1000F / 1000F);

        startTime = System.nanoTime();
        regionUtil.getAddressByPosition(24, 3, 0, null);
        arrays[3] = String.valueOf((System.nanoTime() - startTime) / 1000F / 1000F);

        return arrays;
    }

    public static String[] sizeOfRegionUtil(RegionUtil regionUtil) {
        String[] arrays = new String[6];
        arrays[0] = "RegionUtil";

        //计算指定对象及其引用树上的所有对象的综合大小，单位字节
        long size = RamUsageEstimator.sizeOf(regionUtil);
        arrays[1] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(regionUtil.mProvinceModelList);
        arrays[2] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(regionUtil.mProvinceList);
        arrays[3] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(regionUtil.mCityList);
        arrays[4] = String.valueOf(size / 1024F);

        size = RamUsageEstimator.sizeOf(regionUtil.mAreaList);
        arrays[5] = String.valueOf(size / 1024F);

        return arrays;
    }
}
