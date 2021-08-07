package main.java.com.mindtree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import main.java.com.mindtree.campusmindstracks.client.CampusMindsTracksClient;
import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.entity.Track;
import main.java.com.mindtree.campusmindstracks.exception.CampusMindTrackApplicationException;
import main.java.com.mindtree.campusmindstracks.exception.serviceException.CampusMindServiceException;
import main.java.com.mindtree.campusmindstracks.exception.serviceException.TrackServiceException;
import main.java.com.mindtree.campusmindstracks.service.serviceImpl.CampusMindServiceImpl;
import main.java.com.mindtree.campusmindstracks.service.serviceImpl.TrackServiceImpl;

public class CampusMindTrackApplication {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException, CampusMindTrackApplicationException {
		int choice;
		CampusMindsTracksClient campustrackclient = new CampusMindsTracksClient();
		TrackServiceImpl trackservice = new TrackServiceImpl();
		CampusMindServiceImpl campusmindservice = new CampusMindServiceImpl();
		boolean exit = false;
		String message = "";
		do {
			choice = campustrackclient.displayMenu();
			switch (choice) {
			case 1:
				Track track = campustrackclient.createTracks();
				try {
					message = trackservice.addTrack(track);
				} catch (TrackServiceException e) {
					System.out.println(e.getMessage());
				}
				campustrackclient.showMessage(message);
				break;
			case 2:
				Set<CampusMind> campusminds = campustrackclient.createCampusMinds();
				try {
					message = campusmindservice.addCampusMinds(campusminds);
				} catch (CampusMindServiceException e) {
					System.out.println(e.getMessage());
				}
				campustrackclient.showMessage(message);

				break;
			case 3:
				Map<Integer,CampusMind> campusmind= new HashMap<>();
				try {
					campusmind=campusmindservice.getallCampusMind();
				}catch(CampusMindServiceException e)
				{
					System.out.println(e.getMessage());
				}
				    campustrackclient.getAllCampusMinds(campusmind);
				break;
			case 4:
				System.out.println("enter the name of the campus mind whose age you want to update");
				String name = sc.next();
				System.out.println("enter the age you want to update");
				sc.nextLine();
				int age = sc.nextInt();
				CampusMind updatedcampusmind;
				try {
					updatedcampusmind = campusmindservice.updateAgeOfCampusMind(name,age);
					 campustrackclient.showUpdatedCampusMind(updatedcampusmind);
				} catch (CampusMindServiceException e) {
				System.out.println(e.getMessage());
				}
				
				break;
			case 5:
				System.out.println("enter  the track id whose duration to be updated");
				int trackid=sc.nextInt();
				System.out.println("enter the duration you want to update");
				int duration = sc.nextInt();
				String msg="";
				
					try {
						 msg=trackservice.UpdateDuration(trackid,duration);
					} catch (TrackServiceException e) {
						System.out.println(e.getMessage());
						
					}
					campustrackclient.showMessage(msg);
				break;
			case 6:
				List<Track> tracks=new ArrayList<Track>();
				try {
					tracks=trackservice.getAllTracksSortedByName();
				} catch (TrackServiceException e) {
					System.out.println(e.getMessage());
				}
				campustrackclient.getAllTracksSortedByName(tracks);
				break;
			case 7:
				Set<CampusMind> sortedcampusmind=new TreeSet<CampusMind>();
				try {
					sortedcampusmind=campusmindservice.getAllCampusMindSortedByMid();
					campustrackclient.getAllCampusMindSortedByMid(sortedcampusmind);
				} catch (CampusMindServiceException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			
			case 8:
				exit = true;
				break;
			}
			if (exit == true) {
				System.out.println("buhbyeeee------");
				break;
			}
		} while (choice < 9);
	}

}
