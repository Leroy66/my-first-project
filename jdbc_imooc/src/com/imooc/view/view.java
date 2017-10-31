package com.imooc.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.imooc.action.GoddnessAction;
import com.imooc.model.Goddess;

public class view {
	private static final String CONTEXT = "欢迎来到女神禁区：\n" + "下面是女神禁区的功能列表：\n" + " [MAIN/M]:主菜单\n"
			+ " [QUERY/Q]：查询全部女神的信息\n" + " [GET/G]:查看某个女神的详细信息\n" + " [ADD/A]:添加女神信息\n" + " [UPDATE/U]:更新女神信息\n"
			+ " [DELETE/D]:删除女神信息\n" + " [SEARCH/S]:查询女神信息(根据姓名、手机号来查询)\n" + " [EXIT/E]:推出女神禁区\n"
			+ " [BREAK/B]:退出当前功能，返回主菜单\n";

	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_SEARCH = "SEARCH";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BREAK = "BREAK";

	public static void main(String[] args) {

		System.out.println(CONTEXT);
		Scanner scan = new Scanner(System.in);

		Goddess goddess = new Goddess();
		GoddnessAction action = new GoddnessAction();
		String prenious = null;
		Integer step = 1;
		while (true) {
			String in = scan.next().toString();
			if (OPERATION_EXIT.equals(in.toUpperCase()) || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已成功退出女神禁区！");
				break;
			} else if (OPERATION_MAIN.equals(in.toUpperCase())
					|| OPERATION_MAIN.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_BREAK.equals(in.toUpperCase())
					|| OPERATION_BREAK.substring(0, 1).equals(in.toUpperCase())) {
				// (退出到)首页
				System.out.println(CONTEXT);
				prenious=OPERATION_MAIN;
				step = 1;
			} else if (OPERATION_QUERY.equals(in.toUpperCase())
					|| OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())) {
				try {// 查询女神的list
					List<Goddess> gs = action.query();
					for (Goddess g : gs) {
						System.out.println(g.getId() + "," + g.getUser_name());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (OPERATION_GET.equals(in.toUpperCase()) || OPERATION_GET.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_GET.equals(prenious)) {
				try {// 根据id查询女神
					if (step == 1) {
						System.out.println("请输入女神的[id]");
						prenious = OPERATION_GET;
					} else {
						Goddess g = action.get(Integer.valueOf(in));
						System.out.println(g.getId() + ",姓名：" + g.getUser_name() + ",年龄：" + g.getAge() + ",生日："
								+ g.getBirthday() + ",email:" + g.getEmail() + ",mobile:" + g.getMobile());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (OPERATION_DELETE.equals(in.toUpperCase())
					|| OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase()) || OPERATION_DELETE.equals(prenious)) {
				try {// 根据id删除女神
					if (step == 1) {
						System.out.println("请输入女神的[id]");
						prenious = OPERATION_DELETE;
					} else {
						action.del(Integer.valueOf(in));
						System.out.println("删除女神成功！！！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (OPERATION_ADD.equals(in.toUpperCase()) || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_ADD.equals(prenious)) {
				prenious = OPERATION_ADD;
				// 新增女神
				if (step == 1) {
					System.out.println("请输入女神的[姓名]");
				} else if (step == 2) {
					goddess.setUser_name(in);
					System.out.println("请输入女神的[年龄]");
				} else if (step == 3) {
					goddess.setAge(Integer.valueOf(in));
					System.out.println("请输入女神的[生日]，格式：yyyy-MM-dd");
				} else if (step == 4) {
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = sf.parse(in);
						goddess.setBirthday(birthday);
						System.out.println("请输入女神的[邮箱]");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("您输入的格式不正确，请重新输入！");
						step = 3;
					}
				} else if (step == 5) {
					goddess.setEmail(in);
					System.out.println("请输入女神的[手机号]");
				} else if (step == 6) {
					goddess.setMobile(in);
					try {
						action.addGoddess(goddess);
						System.out.println("新增女神成功！");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("新增女神失败！");
					}
				}
				if (OPERATION_ADD.equals(prenious)) {
					step++;
				}
			} else {
				System.out.println("您输入的值为：" + in);
			}
		}
	}

}
