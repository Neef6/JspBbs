package cn.edu.nju.action.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;

import cn.edu.nju.dao.impl.BbsChannelDAO;
import cn.edu.nju.dao.impl.BbsRoleDAO;
import cn.edu.nju.dao.impl.BbsStateDAO;
import cn.edu.nju.dao.impl.BbsUserDao;
import cn.edu.nju.model.BbsChannel;
import cn.edu.nju.model.BbsRole;
import cn.edu.nju.model.BbsState;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.service.impl.BbsChannelService;
import cn.edu.nju.service.impl.BbsRoleService;
import cn.edu.nju.service.impl.BbsStateService;
import cn.edu.nju.service.impl.BbsUserService;
import cn.edu.nju.utils.BeanUtilities;
import cn.edu.nju.utils.FileUtils;
import cn.edu.nju.utils.SplitPage;

public class BbsUserAction extends HttpServlet {
	private BbsUser user = new BbsUser();
	private String[] hobbies = {"����",  "��Ӿ", "����", "��ɽ"};
	private BbsUserService service = new BbsUserService();
	private BbsRoleService service1 = new BbsRoleService();
	private BbsStateService service2 = new BbsStateService();
	private BbsChannelService service3 = new BbsChannelService();
	private HttpServletRequest req;
	private HttpServletResponse resp;
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
		BeanUtilities.populateBean(user, req);

		StringBuffer sb = new StringBuffer();
		for(int i=0;(user.getHobbies()!= null)&& i<user.getHobbies().length;i++){
			sb.append(user.getHobbies()[i] + ",");
		}
		user.setUserHobby(sb.toString());	
		service.setDao(new BbsUserDao());
		service1.setDao(new BbsRoleDAO());
		service2.setDao(new BbsStateDAO());
		service3.setDao(new BbsChannelDAO());
		String cmd = req.getParameter("cmd");
		//����
		FileUtils.invoke(this, cmd);
	}
	
	public void toAdd(){
		req.setAttribute("hobbies", hobbies);
		req.setAttribute("heads", FileUtils.getHeadImages());
		FileUtils.forward(req, resp, "register.jsp");
	}
	
	public void add(){
		BbsUser newUser = service.getUserByName(user.getUserName()); 
		if(newUser ==null){
			int rst = service.add(user);
			if(rst > 0){
				FileUtils.forward(req, resp, "index.jsp");
			}else{
				req.setAttribute("error", "<font color='red'>ע��ʧ�ܣ�������ע�ᣡ����</font>");
				req.setAttribute("hobbies", hobbies);
				req.setAttribute("heads", FileUtils.getHeadImages());
				FileUtils.forward(req, resp, "register.jsp");
			}
		}else{
			req.setAttribute("error", "<font color='red'>�û����ѱ�ע�ᣡ����</font>");
			req.setAttribute("hobbies", hobbies);
			req.setAttribute("heads", FileUtils.getHeadImages());
			FileUtils.forward(req, resp, "register.jsp");
		}
	}
	
	//��¼�ж�
	public void login(){
		BbsUser newUser = service.login(user.getUserName(), user.getUserPassword(),null);
//		System.out.println(newUser.getBbsState().getStateId());
		if(newUser != null && newUser.getBbsState().getStateId() != 2){
			user = newUser;
			req.getSession().setAttribute("user", newUser);
			List<BbsChannel> list = service3.findAll();
			req.setAttribute("channelList", list);
 			FileUtils.forward(req, resp, "main.jsp");
		}else if(newUser != null && newUser.getBbsState().getStateId() == 2){
			req.setAttribute("error", "<font color='red'>���û�Ϊ�Ƿ��û�����ֹ��¼������</font>");
			FileUtils.forward(req, resp, "login.jsp");
		}else{
			req.setAttribute("error", "<font color='red'>��¼ʧ�ܣ��û������ڣ��û�����������󣡣���</font>");
			FileUtils.forward(req, resp, "login.jsp");
		}
	}
	
//	public void findAll(){
//		int num = service.getRows();
//		SplitPage sp = null;
//		List<BbsUser> userList = service.findAll(sp);
//		req.setAttribute("list", userList);
//		FileUtils.forward(req, resp, "back/userIndex.jsp");
//	}
	public  void  findAll(){
		List<BbsUser> userList=service.findAll();
		req.setAttribute("list", userList);
		FileUtils.forward(req, resp, "back/userIndex.jsp");
	}
	
	
	public void findUserByName(){
		String name=user.getUserName();
//		System.out.println("findUserByName():"+name);
		List<BbsUser> userList = service.findUserByName(name);
		req.setAttribute("list", userList);
		FileUtils.forward(req, resp, "back/thisUserInfo.jsp");
	}
	
	public void del(){
		String userID = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(userID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int rst = service.del(id);
		findAll();
	}
	
	public void toUpd(){
		String userID = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(userID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		user = service.findById(id);
		List<BbsRole> listRole = service1.findAll();
		List<BbsState> listState = service2.findAll();
		
		req.setAttribute("listRole", listRole);
		req.setAttribute("listState", listState);
		req.setAttribute("hobbies", hobbies);
		req.setAttribute("heads", FileUtils.getHeadImages());
		
		String userHobby = user.getUserHobby();//����"����,��Ӿ"���ߡ����飬��ɽ��
		userHobby = userHobby==null ? "" :userHobby;
		//������Ĵ��빦��һ��
		//if(userHobby == null){
		//	userHobby = "";
		//}
		String[] userHobbies = userHobby.split(",");
		user.setHobbies(userHobbies);
		user.setConfirmPassword(user.getUserPassword());
		req.setAttribute("data", user);
		
		FileUtils.forward(req, resp, "back/userUpd.jsp");
	}
	
	public void toUpdThisUser(){
		String userID = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(userID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		user = service.findById(id);
		
		req.setAttribute("hobbies", hobbies);
		req.setAttribute("heads", FileUtils.getHeadImages());
		
		String userHobby = user.getUserHobby();//����"����,��Ӿ"���ߡ����飬��ɽ��
		userHobby = userHobby==null ? "" :userHobby;
		//������Ĵ��빦��һ��
		//if(userHobby == null){
		//	userHobby = "";
		//}
		String[] userHobbies = userHobby.split(",");
		user.setHobbies(userHobbies);
		user.setConfirmPassword(user.getUserPassword());
		req.setAttribute("data", user);
		
		FileUtils.forward(req, resp, "back/thisUserUpd.jsp");
	}
	
	
	public void upd(){
		service.upd(user);
		findAll();
	}
	
	//--------------------------------------------------------------------
	//����˳��������session
	public void exit(){
		//��ȡ��ǰ��session��Ϣ
		Object object = req.getSession().getAttribute("user");
		//����û���Ϣ���ڣ������session����ת����¼ҳ��
		if(object != null){
			try {
				req.getSession().removeAttribute("user");
//				ServletActionContext.getRequest().getSession().invalidate();
				FileUtils.forward(req, resp, "/login.jsp");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//�����ǰû�е�¼������ʾ�û���δ��¼
		}else{
			FileUtils.forward(req, resp, "/loginout.jsp");
		}
	}
}
