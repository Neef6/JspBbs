package cn.edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.model.BbsRole;
import cn.edu.nju.model.BbsState;
import cn.edu.nju.model.BbsUser;
import cn.edu.nju.utils.JDBCUtil;
import cn.edu.nju.utils.SplitPage;

public class BbsUserDao {
	private Connection con = null;;

	public BbsUser getUserByName(String userName) {
		BbsUser user = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_user WHERE user_name = ?";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userName);
			rs = pstm.executeQuery();
			if (rs.next()) {
				user = new BbsUser();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserAge(rs.getInt(4));
				user.setUserBirth(rs.getDate(5));
				user.setUserHobby(rs.getString(6));
				user.setUserEmail(rs.getString(7));
				BbsState state = new BbsState();
				BbsRole role = new BbsRole();
				role.setRoleId(rs.getInt(8));
				state.setStateId(rs.getInt(9));
				user.setBbsRole(role);
				user.setBbsState(state);
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return null;
	}

	public BbsUser login(String userName, String userPassword, String status) {
		BbsUser user = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_user WHERE user_name = ? AND user_password = ?";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, userPassword);
			rs = pstm.executeQuery();
			if (rs.next()) {
				user = new BbsUser();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserAge(rs.getInt(4));
				user.setUserBirth(rs.getDate(5));
				user.setUserHobby(rs.getString(6));
				user.setUserEmail(rs.getString(7));
				BbsState state = new BbsState();
				BbsRole role = new BbsRole();
				role.setRoleId(rs.getInt(8));
				state.setStateId(rs.getInt(9));
				user.setBbsRole(role);
				user.setBbsState(state);
				// System.out.println("state = " + rs.getInt(9));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return user;
	}

	public int add(BbsUser user) {
		PreparedStatement pstm = null;
		String sql = "INSERT INTO bbs_user (user_name, user_password, user_age, user_birth, user_hobby, user_email, user_role_id, user_state_id, user_img_face)"
				+ " VALUES (?, ?, ?,?,?,?,?,?,?)";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getUserPassword());
			pstm.setInt(3, user.getUserAge());
			pstm.setDate(4, user.getUserBirth());
			pstm.setString(5, user.getUserHobby());
			pstm.setString(6, user.getUserEmail());
			pstm.setInt(7, 1);
			pstm.setInt(8, 1);
			pstm.setString(9, user.getUserImgFace());
			int rst = pstm.executeUpdate();
			return rst;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, null);
		}
		return 0;
	}

	public BbsUser upd(BbsUser user) {
		PreparedStatement pstm = null;
		String sql = "UPDATE bbs_user SET user_name = ?, user_password = ?, user_age=?, user_birth = ?, user_hobby =? , "
				+ "user_email = ?, user_role_id=?, user_state_id=?, user_img_face=? WHERE user_id=?";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getUserName());
			pstm.setString(2, user.getUserPassword());
			pstm.setInt(3, user.getUserAge());
			pstm.setDate(4, user.getUserBirth());
			pstm.setString(5, user.getUserHobby());
			pstm.setString(6, user.getUserEmail());
			pstm.setInt(7, user.getBbsRole().getRoleId());
			pstm.setInt(8, user.getBbsState().getStateId());
			pstm.setString(9, user.getUserImgFace());
			pstm.setInt(10, user.getUserId());
			int rst = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, null);
		}
		return user;
	}

	public List<BbsUser> findAll() {
		List<BbsUser> userList = new ArrayList<BbsUser>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_user";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BbsUser user = new BbsUser();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserAge(rs.getInt(4));
				user.setUserBirth(rs.getDate(5));
				user.setUserHobby(rs.getString(6));
				user.setUserEmail(rs.getString(7));
				BbsState state = new BbsState();
				BbsRole role = new BbsRole();
				role.setRoleId(rs.getInt(8));
				state.setStateId(rs.getInt(9));
				user.setBbsRole(role);
				user.setBbsState(state);
				user.setUserImgFace(rs.getString(10));
				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return userList;
	}

	// 分页查询
	public int getRows() {
		int num = 0;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "Select count(*) From bbs_user";
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				num += rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(num);
		return num;
	}

	public List<BbsUser> findAll(SplitPage sp) {
		List<BbsUser> list = new ArrayList<BbsUser>();
		// 在sqlserver数据中分页查询的sql语句,分页的参数来自SplitPage对象
		// 在MySQL中查询语句为"select * from user limit " + sp.getPageRows()*
		// (sp.getCurrentPage()-1) +"," + sp.getPageRows();
		// 下面这种可能不适用于mysql，一般mysql采用上面那种
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "select * from bbs_user limit " + (sp.getPageRows() * (sp.getCurrentPage() - 1)) + "," + sp.getPageRows();
		// String sql =
		// "select top "+sp.getPageRows()+" * from t_user where id " +
		// " not in (select top ("+sp.getPageRows()*(sp.getCurrentPage()-1)+") id "
		// +" from t_user order by id) order by id";
		try {
			// st = conn.createStatement();
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				BbsUser user = new BbsUser();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserAge(rs.getInt(4));
				user.setUserBirth(rs.getDate(5));
				user.setUserHobby(rs.getString(6));
				user.setUserEmail(rs.getString(7));
				BbsState state = new BbsState();
				BbsRole role = new BbsRole();
				role.setRoleId(rs.getInt(8));
				state.setStateId(rs.getInt(9));
				user.setBbsRole(role);
				user.setBbsState(state);
				user.setUserImgFace(rs.getString(10));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return list;
	}

	public int del(int id) {
		PreparedStatement pstm = null;
		String sql = "DELETE FROM bbs_user WHERE user_id=?";
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

	public BbsUser findById(int id) {
		BbsUser user = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_user WHERE user_id = ?";
		BbsRoleDAO roleDAO = new BbsRoleDAO();
		BbsStateDAO stateDAO = new BbsStateDAO();
		try {
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				user = new BbsUser();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserAge(rs.getInt(4));
				user.setUserBirth(rs.getDate(5));
				user.setUserHobby(rs.getString(6));
				user.setUserEmail(rs.getString(7));

				int roleId = rs.getInt(8);// 得到该用户的roleId
				BbsRole role = roleDAO.findById(roleId);// 通过这个roleId得到这个role的具体信息（roleDes）,得到一个rold对象
				int stateId = rs.getInt(9);// 得到该用户的stateId
				BbsState state = stateDAO.findById(stateId);// 通过这个stateId得到这个state的具体信息（stateDes），得到一个state对象
				user.setBbsRole(role);// 将role对象（对象中包括roleId和roleDes）设置给user
				user.setBbsState(state);// 将state对象（对象中包括stateId和stateDes）设置给user
				user.setUserImgFace(rs.getString(10));
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return null;
	}

	public List<BbsUser> findUserByName(String name){
		List<BbsUser> userList = new ArrayList<BbsUser>();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM bbs_user WHERE user_name = ?";
		try{
			con = JDBCUtil.getConnection();
			pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while(rs.next()){
				BbsUser user = new BbsUser();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setUserPassword(rs.getString(3));
				user.setUserAge(rs.getInt(4));
				user.setUserBirth(rs.getDate(5));
				user.setUserHobby(rs.getString(6));
				user.setUserEmail(rs.getString(7));
				BbsState state = new BbsState();
				BbsRole role = new BbsRole();
				role.setRoleId(rs.getInt(8));
				state.setStateId(rs.getInt(9));
				user.setBbsRole(role);
				user.setBbsState(state);
				user.setUserImgFace(rs.getString(10));
				userList.add(user);
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.closeAll(con, pstm, rs);
		}
		return userList;
	}

}
