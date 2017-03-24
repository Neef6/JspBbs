package cn.edu.nju.service.impl;

import java.util.List;

import cn.edu.nju.dao.impl.BbsRepliesDAO;
import cn.edu.nju.model.BbsReplies;

public class BbsRepliesService {
	private BbsRepliesDAO dao = null;

	public BbsRepliesDAO getDao() {
		return dao;
	}

	public void setDao(BbsRepliesDAO dao) {
		this.dao = dao;
	}
	
	public List<BbsReplies> findAllByTopicId(int id){
		return dao.findAllByTopicId(id);
	}
	
	public int add(BbsReplies replies){
		return dao.add(replies);
	}
}
