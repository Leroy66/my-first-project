package com.imooc.action;

import java.util.List;
import java.util.Map;

import com.imooc.dao.GoddessDao;
import com.imooc.model.Goddess;

public class GoddnessAction {
	public Boolean addGoddess(Goddess g) throws Exception {
		GoddessDao dao = new GoddessDao();
		/*
		 * Goddess g = new Goddess(); g.setUser_name("湘湘"); g.setAge(24);
		 * g.setSex(1); SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd"); Date date = sdf.parse("1993-10-10");
		 * g.setBirthday(date); g.setMobile("1562222222");
		 * g.setEmail("xiangxiang@163.com"); g.setCreate_user("ADMIN");
		 * g.setUpdate_user("ADMIN"); g.setIsdel(0);
		 */
		dao.addGoddess(g);
		return true;
	}

	public List<Goddess> query() throws Exception {
		GoddessDao dao = new GoddessDao();
		return dao.query();
	}

	public Goddess get(Integer id) throws Exception {
		GoddessDao dao = new GoddessDao();
		return dao.get(id);
	}

	public Boolean del(Integer id) throws Exception {
		GoddessDao dao = new GoddessDao();
		dao.deleteGoddess(id);
		return true;
	}

	public Boolean updateGoddess(Goddess g) throws Exception {
		GoddessDao dao = new GoddessDao();
		/*
		 * Goddess g = new Goddess(); g.setUser_name("湘湘"); g.setAge(20);
		 * g.setSex(1); SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd"); Date date = sdf.parse("1992-11-11");
		 * g.setBirthday(date); g.setMobile("156333322");
		 * g.setEmail("xiangxiang@163.com"); g.setUpdate_user("xxx");
		 * g.setIsdel(0); g.setId(5);
		 */
		dao.updateGoddess(g);
		return true;
	}

	public List<Goddess> queryByCondition(List<Map<String, Object>> params) throws Exception {
		GoddessDao dao = new GoddessDao();
		/*
		 * List<Map<String, Object>> params=new ArrayList<Map<String,
		 * Object>>(); Map<String, Object> param=new HashMap<String, Object>();
		 * param.put("key", "user_name"); param.put("rela", "=");
		 * param.put("value", "'湘湘'"); params.add(param); param=new
		 * HashMap<String, Object>(); param.put("key", "mobile");
		 * param.put("rela", "like"); param.put("value", "'%156%'");
		 * params.add(param);
		 */
		List<Goddess> rs = dao.queryByCondition(params);
		return rs;

	}
}
