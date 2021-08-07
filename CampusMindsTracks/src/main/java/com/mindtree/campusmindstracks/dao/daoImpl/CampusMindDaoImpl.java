package main.java.com.mindtree.campusmindstracks.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;



import main.java.com.mindtree.campusmindstracks.dao.CampusMindDao;
import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.ThrowException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.AgeNotUpdated;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.CampusMindDaoException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.NoCampusMindsException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.TrackDaoException;
import main.java.com.mindtree.campusmindstracks.exception.utilityException.DaoUtilityException;
import main.java.com.mindtree.campusmindstracks.utility.DBUtil;

public class CampusMindDaoImpl implements CampusMindDao {

	@Override
	public String insertCampusMindsToDB(Set<CampusMind> campusminds) throws CampusMindTrackApplicationException {
		String message = "";
		Connection con = DBUtil.getConnection();
		CallableStatement cst = DBUtil.openCallableStatement();
		try {
			if (con == null) {
				throw new DaoUtilityException("connection couldnt be established");
			}
			cst = con.prepareCall("{Call webproject.insertcampusmind(?,?,?,?,?)}");
			for (CampusMind campusMind : campusminds) {
				cst.setInt(1, campusMind.getMID());
				cst.setString(2, campusMind.getName());
				cst.setInt(3, campusMind.getAge());
				cst.setString(4, campusMind.getGender());
				cst.setInt(5, campusMind.getTrackId());
				int rows = cst.executeUpdate();
				if (rows > 0) {
					message = "campusminds inserted";
				} else {
					message = "campusminds not inserted";
				}
			}
		} catch (SQLException e) {
			// throw new CampusMindDaoException("could not be established");
			ThrowException te = msg -> {
				throw new TrackDaoException(msg);
			};
			te.throwing("SQL Exception");
		} finally {
			if (cst != null) {
				DBUtil.closingCallableStatement(cst);
			}
			if (con != null) {
				DBUtil.closingConnection(con);
			}
		}

		return message;

	}

	@Override
	public Map<Integer, CampusMind> getAllCampusMindsFromDB() throws DaoUtilityException, CampusMindDaoException {
		Map<Integer, CampusMind> camp = new HashMap<Integer, CampusMind>();
		Connection con = DBUtil.getConnection();
		CallableStatement cst = DBUtil.openCallableStatement();
		int i = 0;
		String query = "{call webproject.getAllCampusMind()}";
		try {
			cst = con.prepareCall(query);
			if (con == null) {
				throw new DaoUtilityException("connection couldnt be established");
			}
			ResultSet rs = cst.executeQuery();
			while (rs.next()) {

				camp.put(i, new CampusMind(rs.getInt("mid"), rs.getString("name"), rs.getInt("age"),
						rs.getString("gender"), rs.getInt("trackid")));
				i++;
			}
			if (camp.isEmpty()) {
				throw new NoCampusMindsException("no campusminds found");
			}
		} catch (SQLException | NoCampusMindsException | DaoUtilityException e) {
			throw new CampusMindDaoException(e);

		} finally {
			if (cst != null) {
				DBUtil.closingCallableStatement(cst);
			}
			if (con != null) {
				DBUtil.closingConnection(con);
			}
		}

		return camp;
	}

	@Override
	public CampusMind UpdateAgeOfCampusMindInDB(String name, int age)
			throws CampusMindDaoException, DaoUtilityException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = DBUtil.openPreparedStatement();
		CampusMind campusmind = new CampusMind();
		try {
			if (con == null) {
				throw new DaoUtilityException("connection couldnt be established");
			}
			pst = con.prepareStatement("update campusmind set age = ? where name=?");
			pst.setInt(1, age);
			pst.setString(2, name);
			int z = pst.executeUpdate();
			if (z > 0) {
				System.out.println("updated");
			} else {
				throw new AgeNotUpdated("--no such campus mind avaible to update--");
			}
			pst = con.prepareStatement("select * from campusmind where name=?");
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				campusmind = new CampusMind(rs.getInt("mid"), rs.getString("name"), rs.getInt("age"),
						rs.getString("gender"), rs.getInt("trackid"));
				System.out.println(campusmind);
			}

		} catch (SQLException | AgeNotUpdated | DaoUtilityException e) {
			throw new CampusMindDaoException("--no such campus mind avaible to update--");
		} finally {
			if (pst != null) {
				DBUtil.closingPreparedStatement(pst);
			}
			if (con != null) {
				DBUtil.closingConnection(con);
			}
		}
		return campusmind;
	}

	@Override
	public Set<CampusMind> getAllMindsFromDB() throws CampusMindDaoException, DaoUtilityException {
		Set<CampusMind> minds = new TreeSet<CampusMind>();
		Connection con = DBUtil.getConnection();
		CallableStatement cst = DBUtil.openCallableStatement();
		int i = 0;
		String query = "{call webproject.getAllCampusMindsFromDB()}";
		try {
			if (con == null) {
				throw new DaoUtilityException("connection couldnt be established");
			}
			cst = con.prepareCall(query);
			ResultSet rs = cst.executeQuery();
			while (rs.next()) {

				minds.add(new CampusMind(rs.getInt("mid"), rs.getString("name"), rs.getInt("age"),
						rs.getString("gender"), rs.getInt("trackid")));

			}
			if (minds.isEmpty()) {
				throw new NoCampusMindsException("no campusminds found");
			}
		} catch (SQLException | NoCampusMindsException | DaoUtilityException e) {
			throw new CampusMindDaoException(e);

		} finally {
			if (cst != null) {
				DBUtil.closingCallableStatement(cst);
			}
			if (con != null) {
				DBUtil.closingConnection(con);
			}
		}

		return minds;
	}

}
