package cn.edu.nju.service.impl;

import java.util.List;

import cn.edu.nju.dao.impl.BbsTopicDAO;
import cn.edu.nju.model.BbsTopic;

public class BbsTopicService {
	private BbsTopicDAO dao;

	public BbsTopicDAO getDao() {
		return dao;
	}

	public void setDao(BbsTopicDAO dao) {
		this.dao = dao;
	}

	public List<BbsTopic> findAllByChannelId(int id) {
		return dao.findAllByChannelId(id);
	}

	public List<BbsTopic> findAll() {
		return dao.findAll();
	}

	public int add(BbsTopic topic) {
		return dao.add(topic);
	}

	public int del(int id) {
		return dao.del(id);
	}

	public BbsTopic findById(int id) {
		return dao.findById(id);
	}

	public int upd(BbsTopic topic) {
		return dao.upd(topic);
	}

	public List<BbsTopic> findAllTop() {
		return dao.findAllTop();
	}

	public List<BbsTopic> findAllEssence() {
		return dao.findAllEssence();
	}
}
