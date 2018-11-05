package com.region;

import java.io.*;

public class FileUtils {


    private static String PATH = "C:\\Users\\YougaKing\\Documents\\WXWork\\1688853464301501\\Cache\\File\\2018-11\\region.json";

    public static String readAssetJson() {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            File file = new File(PATH);
            inputStream = new FileInputStream(file);
            //通过管理器打开文件并读取
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
