package main.java.com.mindtree.campusmindstracks.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.java.com.mindtree.campusmindstracks.dao.daoImpl.TrackDaoImpl;
import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.entity.Track;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.TrackDaoException;
import main.java.com.mindtree.campusmindstracks.exception.serviceException.TrackServiceException;
import main.java.com.mindtree.campusmindstracks.exception.utilityException.DaoUtilityException;
import main.java.com.mindtree.campusmindstracks.service.TrackService;


public class TrackServiceImpl implements TrackService {
	TrackDaoImpl trackdao=new TrackDaoImpl();
	@Override
	public String addTrack(Track track) throws CampusMindTrackApplicationException{
		String message=null;
		
			try {
				try {
					message = trackdao.insertTrackToDB(track);
				} catch (DaoUtilityException e) {
					throw new TrackServiceException(e);
				}
			}
			 catch (TrackDaoException e) {
				throw new  TrackServiceException(e);
			}
		
		return message;
	}
	@Override
	public String UpdateDuration(int trackid, int duration) throws TrackServiceException {
		String msg;
		try {
			msg = trackdao.updateDurationOfTrack(trackid,duration);
		} catch (TrackDaoException | DaoUtilityException e) {
			throw new TrackServiceException(e);
		}
		return msg;
	}
	@Override
	public List<Track> getAllTracksSortedByName() throws TrackServiceException {
		List<Track> track=new ArrayList<Track>();
		try {
			track=trackdao.getAllTracksFromDB();
		} catch (DaoUtilityException | TrackDaoException e) {
			throw new TrackServiceException(e);
		}
		return track;
		
		
	}
	
	
	
	
	
   

}
