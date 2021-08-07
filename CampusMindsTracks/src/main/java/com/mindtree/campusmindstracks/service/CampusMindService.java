package main.java.com.mindtree.campusmindstracks.service;

import java.util.Map;
import java.util.Set;

import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.serviceException.CampusMindServiceException;

public interface CampusMindService {
	public String addCampusMinds(Set<CampusMind> campusminds) throws CampusMindServiceException, CampusMindTrackApplicationException;
	public Map<Integer, CampusMind> getallCampusMind() throws CampusMindServiceException;
	public CampusMind updateAgeOfCampusMind(String name, int age) throws CampusMindServiceException;
	public Set<CampusMind> getAllCampusMindSortedByMid() throws CampusMindServiceException;
}
