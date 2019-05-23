package com.echarts.datashow;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class WeblogService {
    static String url = "jdbc:mysql://bigdata-pro01.kfk.com:3306/kfk";
    static String username = "root";
    static String password = "ee771227";
    public Map<String,String[]> queryWeblogs() {
        Connection conn = null;
        PreparedStatement pst = null;
        String[] titleNames = new String[20];
        String[] titleCounts = new String[20];
        Map<String, String[]> retMap = new HashMap();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            String query_sql = "select titleName,count from webCount where 1=1 order by count desc limit 20";
            pst = conn.prepareStatement(query_sql);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                String titleName = rs.getString("titleName");
                String titleCount = rs.getString("count");
                titleNames[i] = titleName;
                titleCounts[i] = titleCount;
                i++;
            }
            retMap.put("titleName", titleNames);
            retMap.put("titleCount", titleCounts);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retMap;
    }

    public String[] titleCount(){
        Connection conn = null;
        PreparedStatement pst = null;
        String[] titleSums = new String[1];
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            String query_sql = "select count(1) titleSum from webCount";
            pst = conn.prepareStatement(query_sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                String titleSum = rs.getString("titleSum");
                titleSums[0] = titleSum;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return titleSums;
    }
}
