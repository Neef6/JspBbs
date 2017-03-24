package cn.edu.nju.action.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.dao.impl.BbsRepliesDAO;
import cn.edu.nju.dao.impl.BbsTopicDAO;
import cn.edu.nju.model.BbsReplies;
import cn.edu.nju.model.BbsTopic;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.service.impl.BbsRepliesService;
import cn.edu.nju.service.impl.BbsTopicService;
import cn.edu.nju.utils.BeanUtilities;
import cn.edu.nju.utils.FileUtils;

public class BbsRepliesAction extends HttpServlet{
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BbsRepliesService service = new BbsRepliesService();
	private BbsTopicService service1 = new BbsTopicService();
	private BbsReplies replies = new BbsReplies();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//初始化变量
		this.req = req;
		this.resp = resp;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		service.setDao(new BbsRepliesDAO());
		service1.setDao(new BbsTopicDAO());
		
		BeanUtilities.populateBean(replies, req);//将浏览器发送过来的回帖信息(repliesTitle, repliesComment)保存到一个replies对象中
		
		String cmd = req.getParameter("cmd");
		FileUtils.invoke(this, cmd);
	}
	
	public void frontFindAll(){
		findAll();
		FileUtils.forward(req, resp, "repliesIndex.jsp");
	}
	
	public void findAll(){
		String strId = req.getParameter("id");//得到topicId
		int id = 0;
		try {
			id = Integer.parseInt(strId);//将得到的topicId转会为数字，如果转化成功，说明request中有topicId，反之说明request中不包括topicId
		} catch (NumberFormatException e) {
			id = 0;
		}
		if(id == 0){//请求中不包括topicId
			
		}else{//请求中有topicId
			BbsTopic topic = service1.findById(id);//取到用户请求的topic的信息
			req.setAttribute("topic", topic);
			List<BbsReplies> list = service.findAllByTopicId(id);//取到用户请求的topic下面的所有回帖；
			req.setAttribute("list", list);
		}
	}
	
	public void add(){
		BbsUser user = (BbsUser)req.getSession().getAttribute("user");
		if(user == null){
			FileUtils.forward(req, resp, "loginout.jsp");
		}else{
			replies.setBbsUser(user);
			String topicId = req.getParameter("id");
			int id = 0;
			try {
				id = Integer.parseInt(topicId);
			} catch (NumberFormatException e) {
				id = 0;
			}
			BbsTopic topic = service1.findById(id);
			replies.setBbsTopic(topic);
			service.add(replies);
			frontFindAll();
		}
	}
	
}
