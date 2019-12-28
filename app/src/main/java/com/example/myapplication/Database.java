package com.example.myapplication;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Database {
    private static final String TAG = "Database";

    private static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); //加载驱动
            String driver_url="jdbc:mysql://127.0.0.1:3306/elearn?serverTimezone=UTC&verifyServerCertificate=false&useSSL=false";
            String database_user="root";
            String database_password="root";
            conn=DriverManager.getConnection(driver_url,database_user,database_password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static int login(String username, String pass) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            String sql = "select * from user where username = '" + username + "' and password = '"+pass+"';";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                conn.close();
                st.close();
                res.close();
                return 0;
            } else {
                conn.close();
                st.close();
                res.close();
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");

        }
        return 1000;
    }

    public static int signup(String username, String pass) {
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            String sql = "select * from user where username = '" + username + "'";
            ResultSet res = st.executeQuery(sql);
            if (res == null) {
                sql = "insert into user values ('" + username + "','" + pass + "');";
                st.executeUpdate(sql);
                conn.close();
                st.close();
                res.close();
                return 0;
            } else {
                conn.close();
                st.close();
                res.close();
                return 1;


            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");

        }
        return 1000;
    }

}
