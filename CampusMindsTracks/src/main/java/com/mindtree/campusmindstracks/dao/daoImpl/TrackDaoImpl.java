package main.java.com.mindtree.campusmindstracks.dao.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;



import main.java.com.mindtree.campusmindstracks.dao.TrackDao;
import main.java.com.mindtree.campusmindstracks.entity.Track;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.ThrowException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.DurationNotUpdated;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.NoTracksException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.TrackDaoException;
import main.java.com.mindtree.campusmindstracks.exception.utilityException.DaoUtilityException;
import main.java.com.mindtree.campusmindstracks.utility.DBUtil;

public class TrackDaoImpl implements TrackDao {

	@Override
	public String insertTrackToDB(Track track) throws CampusMindTrackApplicationException {
		String message = "";
		Connection con = DBUtil.getConnection();
		CallableStatement cst = DBUtil.openCallableStatement();
		try {
			if (con == null) {
				throw new DaoUtilityException("connection couldnt be established");
			}
			cst = con.prepareCall("{Call webproject.insertTracks(?,?,?)}");
			cst.setInt(1, track.getTrackId());
			cst.setString(2, track.getName());
			cst.setInt(3, track.getDuration());
			int rows = cst.executeUpdate();
			if (rows > 0) {
				message = "inserted";
			} else {
				message = "not inserted";
			}

		} catch (SQLException e) {
			// throw new TrackDaoException("could not be established");
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
	public String updateDurationOfTrack(int trackid, int duration) throws TrackDaoException, DaoUtilityException {
		Connection con = DBUtil.getConnection();
		PreparedStatement pst = DBUtil.openPreparedStatement();
		int rows = 0;
		String msg = "";
		try {
			if (con == null) {
				throw new DaoUtilityException("connection couldnt be established");
			}
			pst = con.prepareStatement("update track set duration=? where trackid=?");
			pst.setInt(1, duration);
			pst.setInt(2, trackid);
			rows = pst.executeUpdate();
			if (rows > 0) {
				msg = "updated";
			} else {
				throw new DurationNotUpdated("duration not updated because track not found");
			}

		} catch (SQLException | DurationNotUpdated e) {
			throw new TrackDaoException("duration not updated");
		} finally {
			if (pst != null) {
				DBUtil.closingPreparedStatement(pst);
			}
			if (con != null) {
				DBUtil.closingConnection(con);
			}
		}
		return msg;

	}

	@Override
	public List<Track> getAllTracksFromDB() throws DaoUtilityException, TrackDaoException {
		List<Track> tracks = new ArrayList<Track>();
		Connection con = DBUtil.getConnection();
		CallableStatement cst = DBUtil.openCallableStatement();
		String query = "call webproject.getAllTracksFromDB()";
		ResultSet rs = DBUtil.openingResultSet();
		try {
			if (con == null) {
				throw new DaoUtilityException("connection couldnt be established");
			}
			cst = con.prepareCall(query);
			rs = cst.executeQuery();
			while (rs.next()) {
				tracks.add(new Track(rs.getInt("trackid"), rs.getString("trackname"), rs.getInt("duration")));
			}
			if (tracks.isEmpty()) {
				throw new NoTracksException("no tracks found");
			}

		} catch (SQLException | NoTracksException e) {
			throw new TrackDaoException(e);
		} finally {
			if (cst != null) {
				DBUtil.closingCallableStatement(cst);
			}
			if (rs != null) {
				DBUtil.closingResultSet(rs);
			}
			if (con != null) {
				DBUtil.closingConnection(con);
			}
		}
		return tracks;
	}

}
