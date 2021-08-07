package main.java.com.mindtree.campusmindstracks.service.serviceImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import main.java.com.mindtree.campusmindstracks.dao.daoImpl.CampusMindDaoImpl;
import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.DaoException.CampusMindDaoException;
import main.java.com.mindtree.campusmindstracks.exception.serviceException.CampusMindServiceException;
import main.java.com.mindtree.campusmindstracks.exception.utilityException.DaoUtilityException;
import main.java.com.mindtree.campusmindstracks.service.CampusMindService;

public class CampusMindServiceImpl implements CampusMindService{
	CampusMindDaoImpl campusdao=new CampusMindDaoImpl();

	@Override
	public String addCampusMinds(Set<CampusMind> campusminds) throws CampusMindTrackApplicationException {
		String message="";
		try {
			message = campusdao.insertCampusMindsToDB(campusminds);
		} catch (CampusMindDaoException |DaoUtilityException e) {
			throw new CampusMindServiceException("could not be established");
		}
		return message;
	}

	@Override
	public Map<Integer, CampusMind> getallCampusMind() throws CampusMindServiceException{
		
			Map<Integer, CampusMind> campusmind=new HashMap<Integer, CampusMind>();
			try {
				campusmind = campusdao.getAllCampusMindsFromDB();
			} catch (CampusMindDaoException | DaoUtilityException e) {
				throw new CampusMindServiceException("----no campus mind to update-----");
			}
			return campusmind;
		
	}

	@Override
	public CampusMind updateAgeOfCampusMind(String name, int age) throws CampusMindServiceException {
		CampusMind mind;
		try {
			mind = campusdao.UpdateAgeOfCampusMindInDB(name,age);
		} catch (CampusMindDaoException|DaoUtilityException e) {
			
			throw new CampusMindServiceException(e);
		}
		return mind;
	}

	@Override
	public Set<CampusMind> getAllCampusMindSortedByMid() throws CampusMindServiceException {
		Set<CampusMind> sortedCampusMind=new TreeSet<CampusMind>();
		try {
			sortedCampusMind=campusdao.getAllMindsFromDB();
		} catch (CampusMindDaoException|DaoUtilityException e) {
			throw new CampusMindServiceException();
		}
		return sortedCampusMind;
	}

	

	

	

}
