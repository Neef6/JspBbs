package cn.edu.nju.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import cn.edu.nju.dao.impl.BbsUserDao;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.service.impl.BbsUserService;

public class RightFilter implements Filter{

	private BbsUserService service=new BbsUserService();
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)arg0;
		HttpServletResponse resp=(HttpServletResponse)arg1;
		service.setDao(new BbsUserDao());
		BbsUser user=(BbsUser)req.getSession().getAttribute("user");
		
//		System.out.println("*");
//		System.out.println("RightFilter UserName:"+user.getUserName());
		int roleId=service.getUserByName(user.getUserName()).getBbsRole().getRoleId();
//		System.out.println("RightFilter RoleId:"+roleId);
		if(roleId!=2){
			JOptionPane.showMessageDialog(null,"您不是管理员！！！");
			FileUtils.forward(req, resp, "/index.jsp");
		}else{
			arg2.doFilter(arg0, arg1);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
