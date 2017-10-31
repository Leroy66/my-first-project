package com.imooc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.imooc.db.DBUtil;
import com.imooc.model.Goddess;

public class GoddessDao {

	public void addGoddess(Goddess g) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = " insert into imooc_goddess (user_name,sex,age,birthday,  email,mobile,create_user,create_date,  update_user,update_date,isdel) values(?,?,?,?,?,?,?,current_date(),?,current_date(),?) ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		// 传参
		ptmt.setString(1, g.getUser_name());
		if(g.getSex()!=null){
			ptmt.setInt(2, g.getSex());
		}else{
			ptmt.setInt(2, 1);
		}
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getCreate_user());
		ptmt.setString(8, g.getUpdate_user());
		if(g.getIsdel()!=null){
			ptmt.setInt(9, g.getIsdel());
		}else{
			ptmt.setInt(9, 0);
		}
		
		// 执行
		ptmt.execute();
	}

	public Boolean updateGoddess(Goddess g) throws Exception {
		Connection conn = DBUtil.getConnection();
		String sql = " update imooc_goddess  " + "set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?,"
				+ "update_user=?,update_date=current_date(),isdel=? " + "where id=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		// 传参
		ptmt.setString(1, g.getUser_name());
		ptmt.setInt(2, g.getSex());
		ptmt.setInt(3, g.getAge());
		ptmt.setDate(4, new Date(g.getBirthday().getTime()));
		ptmt.setString(5, g.getEmail());
		ptmt.setString(6, g.getMobile());
		ptmt.setString(7, g.getUpdate_user());
		ptmt.setInt(8, g.getIsdel());
		ptmt.setInt(9, g.getId());
		// 执行
		ptmt.execute();
		return true;
	}

	public Boolean deleteGoddess(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = " delete from  imooc_goddess where id=? ";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
		return true;
	}

	public List<Goddess> query() throws Exception {
		Connection conn = DBUtil.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id,user_name,age FROM `imooc_goddess`");
		List<Goddess> gs = new ArrayList<Goddess>();
		Goddess g = null;
		while (rs.next()) {
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setAge(rs.getInt("age"));
			g.setUser_name(rs.getString("user_name"));
			gs.add(g);
		}
		return gs;
	}

	public Goddess get(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT * FROM `imooc_goddess` where id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		Goddess g = new Goddess();
		while (rs.next()) {
			g.setId(rs.getInt("id"));
			g.setUser_name(rs.getString("user_name"));
			g.setAge(rs.getInt("age"));
			g.setSex(rs.getInt("sex"));
			g.setBirthday(rs.getDate("birthday"));
			g.setEmail(rs.getString("email"));
			g.setMobile(rs.getString("mobile"));
			g.setCreate_date(rs.getDate("create_date"));
			g.setCreate_user(rs.getString("create_user"));
			g.setUpdate_date(rs.getDate("update_date"));
			g.setUpdate_user(rs.getString("update_user"));
			g.setIsdel(rs.getInt("isDel"));
		}
		return g;
	}

	public List<Goddess> queryByCondition(List<Map<String, Object>> params) throws Exception {
		Connection conn = DBUtil.getConnection();
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT id,user_name,age FROM `imooc_goddess` where 1=1 ");
		if (params != null && params.size() > 0) {
			for (Map<String, Object> map : params) {
				sb.append(" and " + map.get("key") + " " + map.get("rela") + " " + map.get("value"));
			}
		}
		System.out.println(sb.toString());
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ResultSet rs = ptmt.executeQuery();
		List<Goddess> gs = new ArrayList<Goddess>();
		Goddess g = null;
		while (rs.next()) {
			g = new Goddess();
			g.setId(rs.getInt("id"));
			g.setAge(rs.getInt("age"));
			g.setUser_name(rs.getString("user_name"));
			gs.add(g);
		}
		return gs;
	}
}
