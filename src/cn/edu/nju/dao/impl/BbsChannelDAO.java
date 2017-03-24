package cn.edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.model.BbsChannel;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.utils.JDBCUtil;

public class BbsChannelDAO {
	private Connection con = null;
	public List<BbsChannel> findAll(){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BbsChannel> channelList = new ArrayList<BbsChannel>();
		String sql = "SELECT * FROM bbs_channel";
		try{
			con = JDBCUtil.getConnection();//得到数据库连接
			pstm = con.prepareStatement(sql);//预处理sql语句
			rs = pstm.executeQuery();//执行sql，得到结果集
			while(rs.next()){//遍历结果集，每条记录都是一个channel
				BbsChannel channel = new BbsChannel();
				channel.setChannelId(rs.getInt(1));
				channel.setChannelName(rs.getString(2));
				channel.setChannelDes(rs.getString(3));
				channelList.add(channel) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return channelList;
	}
	
	public int add(BbsChannel channel){
		PreparedStatement pstm = null;
		String sql = "INSERT INTO bbs_channel (channel_name, channel_des) VALUES (?, ?)";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, channel.getChannelName());
			pstm.setString(2, channel.getChannelDes());
			int rst = pstm.executeUpdate();
			return rst;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}
	
	public int del(int id){
		PreparedStatement pstm = null;
		String sql = "DELETE FROM bbs_channel WHERE channel_id = ?";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			int rst = pstm.executeUpdate();
			return rst;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}
	
	public BbsChannel findById(int id){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BbsChannel channel = null;
		String sql = "SELECT * FROM bbs_channel  WHERE channel_id = ?";
		try{
			con = JDBCUtil.getConnection();//得到数据库连接
			pstm = con.prepareStatement(sql);//预处理sql语句
			pstm.setInt(1, id);//将参数传给sql语句
			rs = pstm.executeQuery();//执行sql，得到结果集
			while(rs.next()){//遍历结果集，每条记录都是一个channel
				channel = new BbsChannel();
				channel.setChannelId(rs.getInt(1));
				channel.setChannelName(rs.getString(2));
				channel.setChannelDes(rs.getString(3));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return channel;
	}
	
	public int upd(BbsChannel channel){
		PreparedStatement pstm = null;
		String sql = "UPDATE bbs_channel SET channel_name= ?, channel_des = ?";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, channel.getChannelName());
			pstm.setString(2, channel.getChannelDes());
			int rst = pstm.executeUpdate();
			return rst;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}

	public BbsChannel getChannelByName(String channelName){
		BbsChannel channel = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_channel WHERE channel_name = ?";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, channelName);
			rs = pstm.executeQuery();
			if(rs.next()){
				channel = new BbsChannel();
				channel.setChannelId(rs.getInt(1));
				channel.setChannelName(rs.getString(2));
				channel.setChannelDes(rs.getString(3));
			}
			return channel;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return null;
	}
}
