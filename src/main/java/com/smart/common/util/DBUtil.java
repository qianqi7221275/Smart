package com.smart.common.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by oneapm on 2017/6/14.
 */
public class DBUtil {

    private DBUtil(){

    }


    public static Properties MYSQL = new Properties();
    static{
        try {
            InputStream in = DBUtil.class.getResourceAsStream("/properties/jdbc-mysql.properties");
            MYSQL.load(in);
            in.close();
            Class.forName(MYSQL.getProperty("jdbc.driverClassName"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(MYSQL.getProperty("jdbc.url"),MYSQL.getProperty("jdbc.username"),MYSQL.getProperty("jdbc.password"));
    }

    public static JSONArray query(String sql,Object[] params) throws SQLException {
        Connection conn = createConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        if(params != null){
            for(int i=0,size=params.length;i<size;i++){
                preparedStatement.setObject(i+1,params[i]);
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        JSONArray result = new JSONArray();
        JSONObject obj;
        int columnCount = 0;
        String catalogName;
        while(resultSet.next()){
            obj = new JSONObject();
            columnCount = metaData.getColumnCount();
            for(int i = 1;i<=columnCount;i++){
                catalogName = metaData.getColumnLabel(i);
                obj.put(catalogName.toUpperCase(),resultSet.getObject(catalogName));
            }
            result.add(obj);

        }
        return result;
    }

}
