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
		BeanUtilities.populateBean(channel, req);// ���û������������д�İ����Ϣ���浽channel������
		String cmd = req.getParameter("cmd");// �õ������cmd
		FileUtils.invoke(this, cmd);// ����cmdִ����Ӧ�ķ���
	}

	public void findAll() {
		List<BbsChannel> list = service.findAll();
		req.setAttribute("list", list);
		FileUtils.forward(req, resp, "back/channelIndex.jsp");
	}

//	public void add() {
//		int rst = 0;
//		// ��ȡ�û�����İ������
//		String name = req.getParameter("channelName");
//		// System.out.println(name);
//		// �õ����ݿ��д��ڵ����а������
//		List<BbsChannel> list = service.findAll();
//		// ����һ��List������list��������а���name
//		List<String> nameStrs = new ArrayList<String>();
//		for (BbsChannel channelList : list) {
//			nameStrs.add(channelList.getChannelName());
//		}
//		// System.out.println(nameStrs);
//		// System.out.println("!!!!size = "+nameStrs.size());
//		for (int i = 0; i < nameStrs.size(); i++) {
//			// System.out.println(nameStrs.get(i));
//			if (name.equals(nameStrs.get(i))) {
//				JOptionPane.showMessageDialog(null, "�ð���Ѵ��ڣ������¶�������������");
//				FileUtils.forward(req, resp, "back/channelAdd.jsp");// ���ʧ�ܺ�����ת�����ҳ��
//			}
//		}
//		// rst = service.add(channel);
//		// if(rst>0){
//		// findAll();//��ӳɹ�����ʾ���а���б�
//		// }else{
//		// FileUtils.forward(req, resp, "back/channelAdd.jsp");//���ʧ�ܺ�����ת�����ҳ��
//		// }
//	}
	
	//---------------------------------------------------------------------//
	//�½����޸İ��ʱ��������Ʋ��ظ�
	public void add(){
		BbsChannel newChannel = service.getChannelByName(channel.getChannelName());
		if(newChannel == null){
			int rst = service.add(channel);//�õ��Ƿ���ӳɹ�����Ϣ
			if(rst>0){
				findAll();//��ӳɹ�����ʾ���а���б�
			}else{
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ�����");
				 FileUtils.forward(req, resp, "back/channelAdd.jsp");//���ʧ�ܺ�����ת�����ҳ��
			}
		}else{
			JOptionPane.showMessageDialog(null, "������Ѵ��ڣ�����");
			FileUtils.forward(req, resp, "back/channelAdd.jsp");//���ʧ�ܺ�����ת�����ҳ��
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
		String strId = req.getParameter("id");// �õ���������channelId
		int id = 0;
		try {
			id = Integer.parseInt(strId);// ת��������
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
