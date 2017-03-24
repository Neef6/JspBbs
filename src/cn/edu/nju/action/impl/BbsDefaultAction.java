package cn.edu.nju.action.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.dao.impl.BbsChannelDAO;
import cn.edu.nju.dao.impl.BbsTopicDAO;
import cn.edu.nju.dao.impl.BbsUserDao;
import cn.edu.nju.model.BbsChannel;
import cn.edu.nju.model.BbsTopic;
import cn.edu.nju.service.impl.BbsChannelService;
import cn.edu.nju.service.impl.BbsTopicService;
import cn.edu.nju.service.impl.BbsUserService;
import cn.edu.nju.utils.FileUtils;

public class BbsDefaultAction extends HttpServlet {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BbsChannelService service = new BbsChannelService();
	private BbsUserService service1 = new BbsUserService();
	private BbsTopicService service2 = new BbsTopicService();
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
		service1.setDao(new BbsUserDao());
		service2.setDao(new BbsTopicDAO());
		//得到所有的板块，放到channelList里面
		List<BbsChannel> channelList = service.findAll();
		//将板块放入到request中去，这样可以在JSP文件中得到
		req.setAttribute("channelList", channelList);
		
		List<BbsTopic> topList = service2.findAllTop();
		req.setAttribute("listTop", topList);
		FileUtils.forward(req, resp, "main.jsp");//跳转到首页
	}
	
}
