package cn.edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.model.BbsRole;
import cn.edu.nju.utils.JDBCUtil;

public class BbsRoleDAO {
	private Connection con = null;
	public List<BbsRole> findAll(){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<BbsRole> roleList = new ArrayList<BbsRole>();
		String sql = "SELECT * FROM bbs_role";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				BbsRole role = new BbsRole();
				role.setRoleId(rs.getInt(1));
				role.setRoleDes(rs.getString(2));
				roleList.add(role) ;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return roleList;
	}
	
	public BbsRole findById(int id){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		BbsRole role = null;
		String sql = "SELECT * FROM bbs_role WHERE role_id = ?";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next()){
				role = new BbsRole();
				role.setRoleId(rs.getInt(1));
				role.setRoleDes(rs.getString(2));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return role;
	}
}
