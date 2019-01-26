package cn.itcast.bookStore.service;

import java.sql.SQLException;
import java.util.Date;

import javax.security.auth.login.LoginException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.bookStore.dao.UserDao;
import cn.itcast.bookStore.domain.User;
import cn.itcast.bookStore.exception.ActiveUserException;
import cn.itcast.bookStore.exception.RegisterException;
import cn.itcast.bookStore.utils.DataSourceUtils;
import cn.itcast.bookStore.utils.MailUtils;

public class UserService {
    private UserDao dao = new UserDao();

    public void register(User user) throws RegisterException {
        try {
            dao.addUser(user);
            /*String emailMsg = "感谢您注册网上书城，点击<a href='http://localhost:8080/bookstore/activeUser?activeCode="
                    + user.getActiveCode() + "'>&nbsp;激活&nbsp;</a>/后使用。<br>为保障您的账户安全，请在24小时内完成激活操作";
            MailUtils.sendMail(user.getEmail(), emailMsg);*/
        } catch (Exception e) {
            e.printStackTrace();
            throw new RegisterException("注册失败");
        }
    }

    public void activeUser(String activeCode) throws ActiveUserException {
        try {
            User user = dao.findUserByActiveCode(activeCode);
            if (user == null) {
                throw new ActiveUserException("激活用户失败");
            }
            Date registTime = user.getRegistTime();
            long time = System.currentTimeMillis() - registTime.getTime();
            if (time / 1000 / 60 / 60 > 24) {
                throw new ActiveUserException("激活码过期");
            }
            dao.activeUser(activeCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ActiveUserException("激活用户失败");
        }
    }

    public User login(String username, String password) throws LoginException {
        try {
            User user = dao.findUserByUsernameAndPassword(username, password);
            if (user != null) {
                if (user.getState() == 1) {
                    return user;
                }
                throw new LoginException("用户未激活");
            }
            throw new LoginException("用户名或密码错误");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new LoginException("登陆失败");
        }
    }

    public int updateUser(User user) throws SQLException {
        return dao.updateUser(user);
    }
}
