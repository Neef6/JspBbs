package cn.edu.nju.service.impl;

import java.util.List;

import cn.edu.nju.dao.impl.BbsUserDao;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.utils.SplitPage;

public class BbsUserService {
	private BbsUserDao dao;

	public BbsUserDao getDao() {
		return dao;
	}

	public void setDao(BbsUserDao dao) {
		this.dao = dao;
	}
	
	public BbsUser getUserByName(String userName){
		return dao.getUserByName(userName);
	}
	
	public int add(BbsUser user){
		return dao.add(user);
	}
	
	public BbsUser login(String userName, String userPassword,String status){
		return dao.login(userName, userPassword,status);
	}
	
	public List<BbsUser> findAll(){
		return dao.findAll();
	}
	
	public int del(int id){
		return dao.del(id);
	}
	
	public BbsUser upd(BbsUser user){
		return dao.upd(user);
	}
	
	public BbsUser findById(int id){
		return dao.findById(id);
	}
	
	public int getRows(){
		return dao.getRows();
	}
	
	public List<BbsUser> findAll(SplitPage sp){
		return dao.findAll(sp);
	}
	
	public List<BbsUser> findUserByName(String name){
		return dao.findUserByName(name);
	}
}
