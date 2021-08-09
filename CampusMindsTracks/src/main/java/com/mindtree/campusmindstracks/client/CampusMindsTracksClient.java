package main.java.com.mindtree.campusmindstracks.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import main.java.com.mindtree.campusmindstracks.entity.CampusMind;
import main.java.com.mindtree.campusmindstracks.entity.NameComparator;
import main.java.com.mindtree.campusmindstracks.entity.Track;

public class CampusMindsTracksClient {
	static Scanner sc = new Scanner(System.in);

	public static int displayMenu() throws IOException {
		System.out.println("press 1 to create track\n" 
	            + "press 2 to create campusmind\n"
				+ "press 3 to get all the campus minds\n" 
	            + "press 4 to update age of the mind\n"
				+ "press 5 to update duration of the track\n" 
	            + "press 6 to get all the tracks sorted by name\n"
				+ "press 7 to get all the campusmind sorted by mid\n" 
				+ "press 8 to exit the program ");
		System.out.println("enter your choice");
		int choice = sc.nextInt();
		return choice;
	}

	public Track createTracks() throws IOException {
		System.out.println("enter the track id");
		int trackid = sc.nextInt();
		System.out.println("enter the track name");
		sc.nextLine();
		String trackname = sc.nextLine();
		System.out.println("enter the duration");
		int duration = sc.nextInt();
		Track track = new Track(trackid, trackname, duration);
		return track;

	}

	public void showMessage(String message) throws IOException {
		System.out.println(message);

	}

	public Set<CampusMind> createCampusMinds() throws IOException {
		Set<CampusMind> campusminds = new HashSet();
		System.out.println("enter the number of campus mind");
		int num = sc.nextInt();
		for (int i = 0; i < num; i++) {
			System.out.println("enter the mid");
			int mid = sc.nextInt();
			System.out.println("enter the name");
			String name = sc.next();
			System.out.println("enter the age");
			int age = sc.nextInt();
			System.out.println("enter the gender");
			String gender = sc.next();
			System.out.println("enter the track id");
			int id = sc.nextInt();
			CampusMind campusMind = new CampusMind(mid, name, age, gender, id);
			campusminds.add(campusMind);

		}
		return campusminds;
	}

	public void getAllCampusMinds(Map<Integer, CampusMind> campusmind) throws IOException{
		Map<Integer, CampusMind> campusminds=new HashMap<Integer, CampusMind>();
		campusminds=campusminds;
		for (Map.Entry<Integer, CampusMind> entry :campusminds.entrySet() ) {
			int key=entry.getKey();
			CampusMind c=entry.getValue();
			System.out.println(key+" this is the value  "+c);
			
		}
		
	for(CampusMind cam:campusmind.values())		{
		System.out.println(cam);
	}
		
	}

	public void showUpdatedCampusMind(CampusMind updatedcampusmind) throws IOException {
		System.out.println("updated campus mind is"+" "+updatedcampusmind);
		
	}

	public void getAllTracksSortedByName(List<Track> tracks) throws IOException {
		List<Track> track=new ArrayList<Track>();
		track=tracks;
		//Comparator<Track> c=(o1,o2)->{return o1.getName().compareTo(o2.getName());};//Comparator using lamda expression
		Collections.sort(track, new NameComparator()) ;
		System.out.println("Sorted Tracks by name  are");
//		for (Track track2 : track) {
//			System.out.println(track2);
//		}
      track.forEach(track2->System.out.println(track2));//foreach method in iterable interfcae
		
		
	}

	public void getAllCampusMindSortedByMid(Set<CampusMind> sortedcampusmind)throws IOException {
	   Set<CampusMind> campusminds=new TreeSet<CampusMind>();
	   campusminds=sortedcampusmind;
	   System.out.println("Sorted CampusMinds by mid are");
//	   for (CampusMind campusMind : campusminds) {
//		System.out.println(campusMind);
//}
	   campusminds.forEach(minds->System.out.println(minds));//foreach method in iterable interfcae
	}

	

	
}
