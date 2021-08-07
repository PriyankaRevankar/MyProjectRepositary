package main.java.com.mindtree.campusmindstracks.entity;

import java.util.Comparator;

public class NameComparator implements Comparator<Track> {

	@Override
	public int compare(Track o1, Track o2) {
		return o1.getName().compareTo(o2.getName());
		
	}
	

}
