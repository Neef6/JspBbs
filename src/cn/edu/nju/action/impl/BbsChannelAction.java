package cn.edu.nju.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import cn.edu.nju.dao.impl.BbsChannelDAO;
import cn.edu.nju.model.BbsChannel;
import cn.edu.nju.service.impl.BbsChannelService;
import cn.edu.nju.utils.BeanUtilities;
import cn.edu.nju.utils.FileUtils;

public class BbsChannelAction extends HttpServlet {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BbsChannelService service = new BbsChannelService();
	private BbsChannel channel = new BbsChannel();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.req = req;
		this.resp = resp;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		service.setDao(new BbsChannelDAO());
		BeanUtilities.populateBean(channel, req);// 将用户在浏览器上填写的板块信息保存到channel对象中
		String cmd = req.getParameter("cmd");// 得到请求的cmd
		FileUtils.invoke(this, cmd);// 根据cmd执行相应的方法
	}

	public void findAll() {
		List<BbsChannel> list = service.findAll();
		req.setAttribute("list", list);
		FileUtils.forward(req, resp, "back/channelIndex.jsp");
	}

//	public void add() {
//		int rst = 0;
//		// 获取用户输出的版块名称
//		String name = req.getParameter("channelName");
//		// System.out.println(name);
//		// 得到数据库中存在的所有版块内容
//		List<BbsChannel> list = service.findAll();
//		// 创建一个List，遍历list并存放所有版块的name
//		List<String> nameStrs = new ArrayList<String>();
//		for (BbsChannel channelList : list) {
//			nameStrs.add(channelList.getChannelName());
//		}
//		// System.out.println(nameStrs);
//		// System.out.println("!!!!size = "+nameStrs.size());
//		for (int i = 0; i < nameStrs.size(); i++) {
//			// System.out.println(nameStrs.get(i));
//			if (name.equals(nameStrs.get(i))) {
//				JOptionPane.showMessageDialog(null, "该板块已存在，请重新定义版块名！！！");
//				FileUtils.forward(req, resp, "back/channelAdd.jsp");// 添加失败后再跳转到添加页面
//			}
//		}
//		// rst = service.add(channel);
//		// if(rst>0){
//		// findAll();//添加成功后显示所有板块列表
//		// }else{
//		// FileUtils.forward(req, resp, "back/channelAdd.jsp");//添加失败后再跳转到添加页面
//		// }
//	}
	
	//---------------------------------------------------------------------//
	//新建，修改板块时，板块名称不重复
	public void add(){
		BbsChannel newChannel = service.getChannelByName(channel.getChannelName());
		if(newChannel == null){
			int rst = service.add(channel);//得到是否添加成功的信息
			if(rst>0){
				findAll();//添加成功后显示所有板块列表
			}else{
				JOptionPane.showMessageDialog(null, "添加失败！！！");
				 FileUtils.forward(req, resp, "back/channelAdd.jsp");//添加失败后再跳转到添加页面
			}
		}else{
			JOptionPane.showMessageDialog(null, "版块名已存在！！！");
			FileUtils.forward(req, resp, "back/channelAdd.jsp");//添加失败后再跳转到添加页面
		}
	}

	public void del() {
		String strId = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(strId);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int rst = service.del(id);
		findAll();
	}

	public void toUpd() {
		String strId = req.getParameter("id");// 得到传上来的channelId
		int id = 0;
		try {
			id = Integer.parseInt(strId);// 转成数字型
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		channel = service.findById(id);
		req.setAttribute("data", channel);
		FileUtils.forward(req, resp, "back/channelUpd.jsp");
	}

	public void upd() {
		int rst = service.upd(channel);
		findAll();
	}

}
