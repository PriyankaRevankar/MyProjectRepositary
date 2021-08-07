package main.java.com.mindtree.campusmindstracks.service;

import java.util.List;

import main.java.com.mindtree.campusmindstracks.entity.Track;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.serviceException.TrackServiceException;

public interface TrackService {
	public String addTrack(Track track) throws TrackServiceException, CampusMindTrackApplicationException;
	public String UpdateDuration(int trackid, int duration) throws TrackServiceException;
	public List<Track> getAllTracksSortedByName() throws TrackServiceException;
}
