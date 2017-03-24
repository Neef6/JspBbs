package cn.edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.model.BbsReplies;
import cn.edu.nju.model.BbsTopic;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.utils.JDBCUtil;

public class BbsRepliesDAO {
	private Connection con;
	public List<BbsReplies> findAllByTopicId(int id){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_replies WHERE replies_topic_id = ?";
		List<BbsReplies> repliesList = new ArrayList<BbsReplies>();
		BbsUserDao userDao = new BbsUserDao();
		BbsTopicDAO topicDao = new BbsTopicDAO();
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next()){
				BbsReplies replies = new BbsReplies();
				replies.setRepliesId(rs.getInt(1));
				replies.setRepliesTitle(rs.getString(2));
				replies.setRepliesCreateTime(rs.getTimestamp(3));
				int userId = rs.getInt(4);//得到回帖人的userId
				BbsUser user = userDao.findById(userId);//通过回帖人的userId得到回帖人的信息
				replies.setBbsUser(user);
				int topicId = rs.getInt(5);//得到主题帖的Id
				BbsTopic topic = topicDao.findById(topicId);//通过主题帖的id得到主题帖的信息
				replies.setBbsTopic(topic);
				replies.setRepliesComment(rs.getString(6));
				repliesList.add(replies);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return repliesList;
	}
	
	public int add(BbsReplies replies){
		PreparedStatement pstm = null;
		String sql = "INSERT INTO bbs_replies (replies_title, replies_createtime, replies_user_id, replies_topic_id, replies_comment) VALUES(?, ?, ?, ?, ?)";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, replies.getRepliesTitle());
			pstm.setTimestamp(2, replies.getRepliesCreateTime());
			pstm.setInt(3, replies.getBbsUser().getUserId());
			pstm.setInt(4, replies.getBbsTopic().getTopicId());
			pstm.setString(5, replies.getRepliesComment());
			int rst = pstm.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}
}
