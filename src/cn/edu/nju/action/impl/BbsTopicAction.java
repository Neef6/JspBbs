package cn.edu.nju.action.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.dao.impl.BbsChannelDAO;
import cn.edu.nju.dao.impl.BbsTopicDAO;
import cn.edu.nju.model.BbsChannel;
import cn.edu.nju.model.BbsReplies;
import cn.edu.nju.model.BbsTopic;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.service.impl.BbsChannelService;
import cn.edu.nju.service.impl.BbsRepliesService;
import cn.edu.nju.service.impl.BbsTopicService;
import cn.edu.nju.utils.BeanUtilities;
import cn.edu.nju.utils.FileUtils;

public class BbsTopicAction extends HttpServlet {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BbsTopicService service = new BbsTopicService();
	private BbsChannelService service1 = new BbsChannelService();
//	private BbsRepliesService service3=new BbsRepliesService();
	private BbsTopic topic = new BbsTopic();

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

		service.setDao(new BbsTopicDAO());
		service1.setDao(new BbsChannelDAO());
		BeanUtilities.populateBean(topic, req);
		String cmd = req.getParameter("cmd");
		FileUtils.invoke(this, cmd);

	}

	/*
	 * 从首页发出的请求，返回的帖子是选定的板块下面所有的帖子
	 */
	public void frontFindAll() {
		findAll();
		FileUtils.forward(req, resp, "topicIndex.jsp");
	}

	/*
	 * 从后台管理界面发出的请求，返回的帖子是所有的板块下面的所有的帖子
	 */
	public void backFindAll() {
		findAll();
		FileUtils.forward(req, resp, "back/topicIndex.jsp");
	}

	/*
	 * 根据不同条件得到topic
	 */
	private void findAll() {
		String strId = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(strId);
		} catch (NumberFormatException e) {
			id = 0;
		}
		if (id == 0) {// 来自于back管理页面的请求，不分板块，需要得到所有的帖子
			List<BbsTopic> list = service.findAll();
			req.setAttribute("list", list);
		} else {// 来自于main.jsp，需要得到指定channel中所有帖子
			List<BbsTopic> list = service.findAllByChannelId(id);
			req.setAttribute("list", list);
			BbsChannel channel = service1.findById(id);// 得到指定板块的信息
			req.setAttribute("channel", channel);
		}
	}

	/*
	 * 跳转到到发帖页面
	 */
	public void toAdd() {
		BbsUser user = (BbsUser) req.getSession().getAttribute("user");// 判断用户是否是登录状态
		if (user == null) {// 不是登录状态，跳转到提示页面
			FileUtils.forward(req, resp, "loginout.jsp");
		} else {// 已经登录，跳转到发帖页面
			FileUtils.forward(req, resp, "topicAdd.jsp");
		}
	}

	/*
	 * 添加一个帖子
	 */
	public void add() {
		BbsUser user = (BbsUser) req.getSession().getAttribute("user");// 判断用户是否是登录状态
		if (user == null) {// 不是登录状态，跳转到提示页面
			FileUtils.forward(req, resp, "loginout.jsp");
		} else {// 已经登录，跳转到发帖页面
			topic.setBbsUser(user);// 将已登录用户信息放入帖子信息
			String strId = req.getParameter("id");
			if (strId == null || strId.equals("")) {// 从request中得到名称为“id”的参数，如果参数不存在，则该参数为0
				strId = "0";
			}
			int channelId = 0;
			try {
				channelId = Integer.parseInt(strId);// 把channelId转化为int型
			} catch (NumberFormatException e) {
			}
			BbsChannel channel = service1.findById(channelId);
			topic.setBbsChannel(channel);
			int rst = service.add(topic);
			findAll();
			FileUtils.forward(req, resp, "topicIndex.jsp?channelId="
					+ channelId);
		}
	}

	// 删除一个帖子
	public void del() {
		String strId = req.getParameter("topicId");// 得到要删除帖子的id
		int id = 0;
		try {
			id = Integer.parseInt(strId);
		} catch (NumberFormatException e) {
			id = 0;
		}
		service.del(id);
		backFindAll();
	}

	/*
	 * 更新帖子
	 */
	public void upd() {
		String topicId = req.getParameter("topicId");
		String topicTop = req.getParameter("topicTop");
		String topicEssence = req.getParameter("topicEssence");
		int id = 0;
		try {
			id = Integer.parseInt(topicId);
		} catch (NumberFormatException e) {
			id = 0;
		}
		topic = service.findById(id);// 得到数据库中保存的id为topicId的帖子的所有信息
		// 把topic对象中置顶的属性改为用户想要设的值
		if (topicTop != null) {
			topic.setTopicTop(Boolean.parseBoolean(topicTop));
		}
		// 把topic对象中精华的属性改为用户想要设的值
		if (topicEssence != null) {
			topic.setTopicEssence(Boolean.parseBoolean(topicEssence));
		}
		service.upd(topic);
		backFindAll();
	}

//	public void findTopTopic() {
//		String strId = req.getParameter("id");
//		int id = 0;
//		try {
//			id = Integer.parseInt(strId);
//		} catch (NumberFormatException e) {
//			id = 0;
//		}
//		if (id == 0) {
//
//		} else {
//			BbsTopic topic = service.findById(id);
//			req.setAttribute("bbsUser", topic.getBbsUser());
//			req.setAttribute("topic", topic);
//			List<BbsReplies> list = service3.findAllByTopicId(id);
//			req.setAttribute("list", list);
//			FileUtils.forward(req, resp, "topIndex.jsp");
//		}
//	}
//
//	public void findEssenceTopic() {
//		String strId = req.getParameter("id");
//		int id = 0;
//		try {
//			id = Integer.parseInt(strId);
//		} catch (NumberFormatException e) {
//			id = 0;
//		}
//		if (id == 0) {
//
//		} else {
//			BbsTopic topic = service.findById(id);
//			req.setAttribute("bbsUser", topic.getBbsUser());
//			req.setAttribute("topic", topic);
//			List<BbsReplies> list = service3.findAllByTopicId(id);
//			req.setAttribute("list", list);
//			FileUtils.forward(req, resp, "essenceIndex.jsp");
//		}
//	}

}
