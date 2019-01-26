package cn.itcast.bookStore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.utils.DataSourceUtils;

public class UserDao {

    public void addUser(User user) throws SQLException {
        String sql = "insert into USER(username,password,gender,email,telephone,introduce,activecode) value(?,?,?,?,?,?,?)";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        System.out.println(runner);
        int row = runner.update(sql, user.getUsername(), user.getPassword(),
                user.getGender(), user.getEmail(), user.getTelephone(),
                user.getIntroduce(), user.getActiveCode());
        if (row == 0) {
            throw new RuntimeException();
        }
    }

    public User findUserByActiveCode(String activeCode) throws SQLException {
        String sql = "select * from user where activecode=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<>(User.class), activeCode);
    }

    public void activeUser(String activeCode) throws SQLException {
        String sql = "update user set state=? where activecode=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        runner.update(sql, 1, activeCode);
    }

    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from user where username=? and password=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<>(User.class), username, password);
    }

    public int updateUser(User user) throws SQLException {
        String sql = "UPDATE USER SET PASSWORD=?,gender=?,telephone=? WHERE id=?";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.update(sql, user.getPassword(), user.getGender(), user.getTelephone(), user.getId());
    }
}
