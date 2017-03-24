package cn.edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.model.BbsChannel;
import cn.edu.nju.model.BbsTopic;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.utils.JDBCUtil;

public class BbsTopicDAO {
	private Connection con;

	/**
	 * ͨ��ѡ�еİ��id�õ��������������е�����
	 **/
	public List<BbsTopic> findAllByChannelId(int id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BbsTopic> topicList = new ArrayList<BbsTopic>();
		String sql = "SELECT * FROM bbs_topic WHERE topic_channel_id = ?";
		BbsUserDao userDao = new BbsUserDao();
		BbsChannelDAO channelDAO = new BbsChannelDAO();
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BbsTopic topic = new BbsTopic();
				topic.setTopicId(rs.getInt(1));
				topic.setTopicTitle(rs.getString(2));
				topic.setTopicCreateTime(rs.getTimestamp(3));
				int userID = rs.getInt(4);// �õ������˵�user id
				BbsUser user = userDao.findById(userID);// ͨ��user Id�õ�user ������Ϣ
				topic.setBbsUser(user);// ��������Ϣ�м��뷢������Ϣ
				int channelID = rs.getInt(5);// �õ�����id
				BbsChannel channel = channelDAO.findById(channelID);// ͨ�����id�õ����ľ�����Ϣ
				topic.setBbsChannel(channel);// ��������Ϣ�й���������Ϣ��
				topic.setTopicComment(rs.getString(6));
				topic.setTopicTop(rs.getBoolean(7));
				topic.setTopicEssence(rs.getBoolean(8));
				topicList.add(topic);// ���������Ӷ�����뵽����list��ȥ
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return topicList;
	}

	/*
	 * �õ����е�����
	 */
	public List<BbsTopic> findAll() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BbsTopic> topicList = new ArrayList<BbsTopic>();
		String sql = "SELECT * FROM bbs_topic";
		BbsUserDao userDao = new BbsUserDao();
		BbsChannelDAO channelDAO = new BbsChannelDAO();
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BbsTopic topic = new BbsTopic();
				topic.setTopicId(rs.getInt(1));
				topic.setTopicTitle(rs.getString(2));
				topic.setTopicCreateTime(rs.getTimestamp(3));
				int userID = rs.getInt(4);// �õ������˵�user id
				BbsUser user = userDao.findById(userID);// ͨ��user Id�õ�user ������Ϣ
				topic.setBbsUser(user);// ��������Ϣ�м��뷢������Ϣ
				int channelID = rs.getInt(5);// �õ�����id
				BbsChannel channel = channelDAO.findById(channelID);// ͨ�����id�õ����ľ�����Ϣ
				topic.setBbsChannel(channel);// ��������Ϣ�й���������Ϣ��
				topic.setTopicComment(rs.getString(6));
				topic.setTopicTop(rs.getBoolean(7));// ������ݿ��д��е�ֵ��0������false����1������true
				topic.setTopicEssence(rs.getBoolean(8));
				topicList.add(topic);// ���������Ӷ�����뵽����list��ȥ
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return topicList;
	}

	public int add(BbsTopic topic) {
		PreparedStatement pstm = null;
		String sql = "INSERT INTO bbs_topic (topic_title, topic_createtime, topic_user_id, topic_channel_id, topic_comment) VALUES "
				+ " (?, ?, ?, ?, ?)";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, topic.getTopicTitle());
			pstm.setTimestamp(2, topic.getTopicCreateTime());
			pstm.setInt(3, topic.getBbsUser().getUserId());
			pstm.setInt(4, topic.getBbsChannel().getChannelId());
			pstm.setString(5, topic.getTopicComment());
			int rst = pstm.executeUpdate();
			return rst;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}

	public int del(int id) {
		PreparedStatement pstm = null;
		String sql = "DELETE FROM bbs_topic WHERE topic_id = ?";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			int rst = pstm.executeUpdate();
			return rst;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}

	public BbsTopic findById(int id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_topic WHERE topic_id = ?";
		BbsTopic topic = null;
		BbsUserDao userDao = new BbsUserDao();
		BbsChannelDAO channelDAO = new BbsChannelDAO();
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				topic = new BbsTopic();
				topic.setTopicId(rs.getInt(1));
				topic.setTopicTitle(rs.getString(2));
				topic.setTopicCreateTime(rs.getTimestamp(3));
				int userID = rs.getInt(4);// �õ������˵�user id
				BbsUser user = userDao.findById(userID);// ͨ��user Id�õ�user ������Ϣ
				topic.setBbsUser(user);// ��������Ϣ�м��뷢������Ϣ
				int channelID = rs.getInt(5);// �õ�����id
				BbsChannel channel = channelDAO.findById(channelID);// ͨ�����id�õ����ľ�����Ϣ
				topic.setBbsChannel(channel);// ��������Ϣ�м�������Ϣ��
				topic.setTopicComment(rs.getString(6));
				topic.setTopicTop(rs.getBoolean(7));// ������ݿ��д��е�ֵ��0������false����1������true
				topic.setTopicEssence(rs.getBoolean(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return topic;
	}

	public int upd(BbsTopic topic) {
		PreparedStatement pstm = null;
		String sql = "UPDATE bbs_topic SET topic_comment=?,topic_title=?,  topic_channel_id = ?, topic_top=?, topic_essence = ? WHERE topic_id=?";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, topic.getTopicComment());
			pstm.setString(2, topic.getTopicTitle());
			pstm.setInt(3, topic.getBbsChannel().getChannelId());
			pstm.setBoolean(4, topic.getTopicTop());
			pstm.setBoolean(5, topic.getTopicEssence());
			pstm.setInt(6, topic.getTopicId());
			int rst = pstm.executeUpdate();
			return rst;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}

	/*
	 * �õ����е��ö���
	 */
	public List<BbsTopic> findAllTop() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BbsTopic> topicList = new ArrayList<BbsTopic>();
		String sql = "SELECT * FROM bbs_topic WHERE topic_top = 1";
		BbsUserDao userDao = new BbsUserDao();
		BbsChannelDAO channelDAO = new BbsChannelDAO();
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BbsTopic topic = new BbsTopic();
				topic.setTopicId(rs.getInt(1));
				topic.setTopicTitle(rs.getString(2));
				topic.setTopicCreateTime(rs.getTimestamp(3));
				int userID = rs.getInt(4);// �õ������˵�user id
				BbsUser user = userDao.findById(userID);// ͨ��user Id�õ�user ������Ϣ
				topic.setBbsUser(user);// ��������Ϣ�м��뷢������Ϣ
				int channelID = rs.getInt(5);// �õ�����id
				BbsChannel channel = channelDAO.findById(channelID);// ͨ�����id�õ����ľ�����Ϣ
				topic.setBbsChannel(channel);// ��������Ϣ�й���������Ϣ��
				topic.setTopicComment(rs.getString(6));
				topic.setTopicTop(rs.getBoolean(7));
				topic.setTopicEssence(rs.getBoolean(8));
				topicList.add(topic);// ���������Ӷ�����뵽����list��ȥ
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return topicList;
	}

	public List<BbsTopic> findAllEssence() {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BbsTopic> topicList = new ArrayList<BbsTopic>();
		String sql = "SELECT * FROM bbs_topic WHERE topic_essence=1";
		BbsUserDao userDao = new BbsUserDao();
		BbsChannelDAO channelDAO = new BbsChannelDAO();
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BbsTopic topic = new BbsTopic();
				topic.setTopicId(rs.getInt(1));
				topic.setTopicTitle(rs.getString(2));
				topic.setTopicCreateTime(rs.getTimestamp(3));
				int userID = rs.getInt(4);// �õ������˵�user id
				BbsUser user = userDao.findById(userID);// ͨ��id �õ�������Ϣ
				topic.setBbsUser(user);// ��������Ϣ�м��뷢������Ϣ
				int channelID = rs.getInt(5);// �õ������Ϣ
				BbsChannel channel = channelDAO.findById(channelID);// ͨ��id
																	// �õ�������Ϣ
				topic.setBbsChannel(channel);// ������Ϣ�м�������Ϣ
				topic.setTopicComment(rs.getString(6));
				topic.setTopicTop(rs.getBoolean(7));
				topic.setTopicEssence(rs.getBoolean(8));
				topicList.add(topic);// ���������Ӷ�����뵽����list��
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return topicList;
	}
}
