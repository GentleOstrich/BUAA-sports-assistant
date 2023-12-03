package com.example.myapplication.Dao;

import android.content.Context;

import com.example.myapplication.Bean.Team2UserBean;
import com.example.myapplication.database.MdbHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Team2UserDao implements mDao {
    // ORMLite提供的DAO类对象，第一个泛型是要操作的数据表映射成的实体类；第二个泛型是这个实体类中ID的数据类型
    private Dao<Team2UserBean, Integer> dao;

    public Team2UserDao(Context context) {
        try {
            this.dao = MdbHelper.getInstance(context).getDao(Team2UserBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据
     *
     * @param data
     */
    public void create(Team2UserBean data) {
        try {
            dao.createOrUpdate(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据集合
     *
     * @param datas
     */
    public void createList(List<Team2UserBean> datas) {
        try {
            dao.create(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向user表中添加一条数据
     * <p>
     * create:插入一条数据或集合
     * <p>
     * createIfNotExists:如果不存在则插入
     * <p>
     * createOrUpdate:如果指定id则更新
     *
     * @param data
     */
    public void insert(Team2UserBean data) {
        try {
            dao.createIfNotExists(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 通过id删除指定数据
    public void delete(int id) {
        try {
            dao.deleteById(id);
        } catch (SQLException e) {
        }
    }

    // 删除表中的一条数据
    public void delete(Team2UserBean data) {
        try {
            dao.delete(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除数据集合
    public void deleteList(List<Team2UserBean> datas) {
        try {
            dao.delete(datas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //清空数据
    public void deleteAll() {
        try {
            dao.delete(dao.queryForAll());
        } catch (Exception e) {
        }
    }

    // 修改表中的一条数据
    public void update(Team2UserBean data) {
        try {
            dao.update(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询表中的所有数据
    public List<Team2UserBean> queryAll() {
        List<Team2UserBean> users = null;
        try {
            users = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // 根据ID取出用户信息
    public Team2UserBean queryById(int id) {
        Team2UserBean user = null;
        try {
            user = dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // 通过条件查询集合（例如：通过messageId和title）
    public List<Team2UserBean> queryByTeamId(Integer id) {
        ArrayList<Team2UserBean> res = new ArrayList<>();
        ArrayList<Team2UserBean> all = new ArrayList<>();
        all.addAll(queryAll());
        for (Team2UserBean bean : all) {
            if (bean.getTeam().getTeamId() == id) {
                res.add(bean);
            }
        }
        return res;
    }

    // 通过条件查询集合（例如：通过content）
    public List<Team2UserBean> queryByParticipantId(Integer id) {
        ArrayList<Team2UserBean> res = new ArrayList<>();
        ArrayList<Team2UserBean> all = new ArrayList<>();
        all.addAll(queryAll());
        for (Team2UserBean bean : all) {
            if (bean.getParticipant().getId() == id) {
                res.add(bean);
            }
        }
        return res;
    }
}
