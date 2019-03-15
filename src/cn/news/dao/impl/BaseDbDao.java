package cn.news.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Author: Clb
 * @Date: 2018/10/31 11:14
 * @Description:
 */
public class BaseDbDao {
    public static Connection con;
    public static PreparedStatement ps;
    public static ResultSet rs;
    private static Properties properties;

    static {
        properties = new Properties();
        InputStream isrm;
        isrm = BaseDbDao.class.getClassLoader().getResourceAsStream("/db.properties");
        if (isrm == null) {
            throw new RuntimeException("找不到数据库配置文件");
        }
        try {
            properties.load(isrm);
            //new FileInputStream("/db.properties")
            Class.forName(properties.getProperty("driver"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"), properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void closeAll() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getQuery(String sql, Object[] obj) {
        getConnection();
        try {
            rs = psSet(sql,obj).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int getResult(String sql, Object[] obj) {
        int result = -1;
        getConnection();
        try {
            result = psSet(sql,obj).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll();
        }
        return result;
    }

    public static PreparedStatement psSet(String sql, Object[] obj){
        try {
            ps = con.prepareStatement(sql);
            if (obj != null) {
                for (int i = 0; i < obj.length; i++) {
                    ps.setObject(i + 1, obj[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

//    public static int getResult(String sql, Object obj) {
//        int result = -1;
//        try {
//            ps = con.prepareStatement(sql);
//            if (obj != null) {
//                ps.setObject(1, obj);
//            }
//            result = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
}
