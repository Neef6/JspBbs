package cn.edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.model.BbsState;
import cn.edu.nju.utils.JDBCUtil;

public class BbsStateDAO {
	private Connection con = null;
	public List<BbsState> findAll(){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BbsState> stateList = new ArrayList<BbsState>();
		String sql = "SELECT * FROM bbs_state";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				BbsState state = new BbsState();
				state.setStateId(rs.getInt(1));
				state.setStateDes(rs.getString(2));
				stateList.add(state) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return stateList;
	}
	
	public BbsState findById(int id){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BbsState state = null;
		String sql = "SELECT * FROM bbs_state WHERE state_id = ?";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next()){
				state = new BbsState();
				state.setStateId(rs.getInt(1));
				state.setStateDes(rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return state;
	}
}
