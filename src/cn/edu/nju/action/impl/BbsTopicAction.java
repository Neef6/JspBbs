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
	 * ����ҳ���������󣬷��ص�������ѡ���İ���������е�����
	 */
	public void frontFindAll() {
		findAll();
		FileUtils.forward(req, resp, "topicIndex.jsp");
	}

	/*
	 * �Ӻ�̨������淢�������󣬷��ص����������еİ����������е�����
	 */
	public void backFindAll() {
		findAll();
		FileUtils.forward(req, resp, "back/topicIndex.jsp");
	}

	/*
	 * ���ݲ�ͬ�����õ�topic
	 */
	private void findAll() {
		String strId = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(strId);
		} catch (NumberFormatException e) {
			id = 0;
		}
		if (id == 0) {// ������back����ҳ������󣬲��ְ�飬��Ҫ�õ����е�����
			List<BbsTopic> list = service.findAll();
			req.setAttribute("list", list);
		} else {// ������main.jsp����Ҫ�õ�ָ��channel����������
			List<BbsTopic> list = service.findAllByChannelId(id);
			req.setAttribute("list", list);
			BbsChannel channel = service1.findById(id);// �õ�ָ��������Ϣ
			req.setAttribute("channel", channel);
		}
	}

	/*
	 * ��ת��������ҳ��
	 */
	public void toAdd() {
		BbsUser user = (BbsUser) req.getSession().getAttribute("user");// �ж��û��Ƿ��ǵ�¼״̬
		if (user == null) {// ���ǵ�¼״̬����ת����ʾҳ��
			FileUtils.forward(req, resp, "loginout.jsp");
		} else {// �Ѿ���¼����ת������ҳ��
			FileUtils.forward(req, resp, "topicAdd.jsp");
		}
	}

	/*
	 * ���һ������
	 */
	public void add() {
		BbsUser user = (BbsUser) req.getSession().getAttribute("user");// �ж��û��Ƿ��ǵ�¼״̬
		if (user == null) {// ���ǵ�¼״̬����ת����ʾҳ��
			FileUtils.forward(req, resp, "loginout.jsp");
		} else {// �Ѿ���¼����ת������ҳ��
			topic.setBbsUser(user);// ���ѵ�¼�û���Ϣ����������Ϣ
			String strId = req.getParameter("id");
			if (strId == null || strId.equals("")) {// ��request�еõ�����Ϊ��id���Ĳ�����������������ڣ���ò���Ϊ0
				strId = "0";
			}
			int channelId = 0;
			try {
				channelId = Integer.parseInt(strId);// ��channelIdת��Ϊint��
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

	// ɾ��һ������
	public void del() {
		String strId = req.getParameter("topicId");// �õ�Ҫɾ�����ӵ�id
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
	 * ��������
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
		topic = service.findById(id);// �õ����ݿ��б����idΪtopicId�����ӵ�������Ϣ
		// ��topic�������ö������Ը�Ϊ�û���Ҫ���ֵ
		if (topicTop != null) {
			topic.setTopicTop(Boolean.parseBoolean(topicTop));
		}
		// ��topic�����о��������Ը�Ϊ�û���Ҫ���ֵ
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
