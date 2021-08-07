package main.java.com.mindtree.campusmindstracks.dao;

import java.util.Map;
import java.util.Set;

import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.CampusMindDaoException;
import main.java.com.mindtree.campusmindstracks.exception.utilityException.DaoUtilityException;

public interface CampusMindDao {
	public String insertCampusMindsToDB(Set<CampusMind> campusminds) throws CampusMindDaoException, DaoUtilityException, CampusMindTrackApplicationException;
	public Map<Integer, CampusMind> getAllCampusMindsFromDB() throws DaoUtilityException,CampusMindDaoException;
	public CampusMind UpdateAgeOfCampusMindInDB(String name, int age) throws CampusMindDaoException, DaoUtilityException;
	public Set<CampusMind> getAllMindsFromDB() throws CampusMindDaoException, DaoUtilityException;
}
