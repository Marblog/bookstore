package cn.itcast.bookStore.service;

import java.sql.SQLException;

import cn.itcast.bookStore.dao.NoticeDao;
import cn.itcast.bookStore.domain.Notice;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	
	public Notice getRecentNotice() {
		try{
			return dao.getRecentNotice();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
