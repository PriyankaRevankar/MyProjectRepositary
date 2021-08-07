package main.java.com.mindtree.campusmindstracks.dao;

import java.util.List;
import java.util.Map;

import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.entity.Track;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.TrackDaoException;
import main.java.com.mindtree.campusmindstracks.exception.utilityException.DaoUtilityException;

public interface TrackDao {
	public String insertTrackToDB(Track track) throws TrackDaoException, DaoUtilityException, CampusMindTrackApplicationException;
	public String updateDurationOfTrack(int trackid, int duration) throws TrackDaoException, DaoUtilityException;
	public List<Track> getAllTracksFromDB() throws DaoUtilityException, TrackDaoException;
}
