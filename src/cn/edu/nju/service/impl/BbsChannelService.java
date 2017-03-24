package cn.edu.nju.service.impl;

import java.util.List;

import cn.edu.nju.dao.impl.BbsChannelDAO;
import cn.edu.nju.model.BbsChannel;

public class BbsChannelService {
	private BbsChannelDAO dao;

	public BbsChannelDAO getDao() {
		return dao;
	}

	public void setDao(BbsChannelDAO dao) {
		this.dao = dao;
	}
	
	public List<BbsChannel> findAll(){
		return dao.findAll();
	}
	
	public int add(BbsChannel channel){
		return dao.add(channel);
	}
	
	public int del(int id){
		return dao.del(id);
	}
	
	public BbsChannel findById(int id){
		return dao.findById(id);
	}
	
	public int upd(BbsChannel channel){
		return dao.upd(channel);
	}

	public BbsChannel getChannelByName(String channelName){
		return dao.getChannelByName(channelName);
	}
}
