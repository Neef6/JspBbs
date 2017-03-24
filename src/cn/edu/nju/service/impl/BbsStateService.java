package cn.edu.nju.service.impl;

import java.util.List;

import cn.edu.nju.dao.impl.BbsStateDAO;
import cn.edu.nju.model.BbsState;



public class BbsStateService {
	private BbsStateDAO dao;

	public BbsStateDAO getDao() {
		return dao;
	}

	public void setDao(BbsStateDAO dao) {
		this.dao = dao;
	}
	
	public List<BbsState> findAll(){
		return dao.findAll();
	}
	
	public BbsState findById(int id){
		return dao.findById(id);
	}
}
