package cn.edu.nju.service.impl;

import java.util.List;

import cn.edu.nju.dao.impl.BbsRoleDAO;
import cn.edu.nju.model.BbsRole;

public class BbsRoleService {
	private BbsRoleDAO dao;

	public BbsRoleDAO getDao() {
		return dao;
	}

	public void setDao(BbsRoleDAO dao) {
		this.dao = dao;
	}
	
	public List<BbsRole> findAll(){
		return dao.findAll();
	}
	
	public BbsRole findById(int id){
		return dao.findById(id);
	}
}
