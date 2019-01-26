package cn.itcast.bookStore.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookStore.domain.Notice;
import cn.itcast.bookStore.utils.DataSourceUtils;

public class NoticeDao {

    public Notice getRecentNotice() throws SQLException {
        String sql = "select * from notice order by n_time desc limit 0,1";
        QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
        return runner.query(sql, new BeanHandler<>(Notice.class));
    }

}
