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
		//��ʼ������
		this.req = req;
		this.resp = resp;
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		service.setDao(new BbsRepliesDAO());
		service1.setDao(new BbsTopicDAO());
		
		BeanUtilities.populateBean(replies, req);//����������͹����Ļ�����Ϣ(repliesTitle, repliesComment)���浽һ��replies������
		
		String cmd = req.getParameter("cmd");
		FileUtils.invoke(this, cmd);
	}
	
	public void frontFindAll(){
		findAll();
		FileUtils.forward(req, resp, "repliesIndex.jsp");
	}
	
	public void findAll(){
		String strId = req.getParameter("id");//�õ�topicId
		int id = 0;
		try {
			id = Integer.parseInt(strId);//���õ���topicIdת��Ϊ���֣����ת���ɹ���˵��request����topicId����֮˵��request�в�����topicId
		} catch (NumberFormatException e) {
			id = 0;
		}
		if(id == 0){//�����в�����topicId
			
		}else{//��������topicId
			BbsTopic topic = service1.findById(id);//ȡ���û������topic����Ϣ
			req.setAttribute("topic", topic);
			List<BbsReplies> list = service.findAllByTopicId(id);//ȡ���û������topic��������л�����
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
