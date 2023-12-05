package com.example.myapplication.database;

import java.sql.SQLException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.example.myapplication.Bean.*;

public class MdbHelper extends OrmLiteSqliteOpenHelper {
    // 数据库名字
    private static final String DATABASE_NAME = "ormlite-test.db";
    private static final int DATABASE_VERSION = 0;

    // 本类的单例实例
    private static MdbHelper instance;

    // 存储APP中所有的DAO对象的Map集合
    private ArrayMap<String, Dao> daos = new ArrayMap<>();

    // 表的Dao，每一张表对应一个Dao
//    private Dao<UserBean, Integer> userDao;

    private Integer curUserId = null;
    // 构造函数，私有的外部不能直接访问
    private MdbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            // 通过TableUtils这个类新建类对应的表
            TableUtils.createTable(connectionSource, UserBean.class);
            TableUtils.createTable(connectionSource, SportsBean.class);
            TableUtils.createTable(connectionSource, TeamBean.class);
            TableUtils.createTable(connectionSource, Team2UserBean.class);
            TableUtils.createTable(connectionSource, StrategyBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // 删除表
            TableUtils.dropTable(connectionSource, UserBean.class, true);
            TableUtils.dropTable(connectionSource, SportsBean.class, true);
            TableUtils.dropTable(connectionSource, TeamBean.class, true);
            TableUtils.dropTable(connectionSource, Team2UserBean.class, true);
            TableUtils.dropTable(connectionSource, StrategyBean.class, true);
            // 再建表
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例模式获取实例
     *
     * @param context
     * @return
     */
    public static synchronized MdbHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MdbHelper.class) {
                if (instance == null)
                    instance = new MdbHelper(context);
            }
        }
        return instance;
    }

    // 根据传入的DAO的路径获取到这个DAO的单例对象（要么从daos这个Map中获取，要么新创建一个并存入daos）
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    // 释放资源
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }


    /**
     * 获取UserDao
     *
     * @return
     * @throws SQLException
     */
//    public Dao<UserBean, Integer> getUserDao() throws SQLException {
//        if (userDao == null) {
//            userDao = getDao(UserBean.class);
//        }
//        return userDao;
//    }

    /**
     * 释放
     */
//    @Override
//    public void close() {
//        super.close();
//        userDao = null;
//    }
}
