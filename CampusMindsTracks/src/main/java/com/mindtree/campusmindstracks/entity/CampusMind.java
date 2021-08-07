package main.java.com.mindtree.campusmindstracks.entity;

public class CampusMind implements Comparable<CampusMind>{
	private int MID;
	private String name;
	private int age;
	private String gender;
	private int trackId;
	public CampusMind() {
		super();
		
	}
	public CampusMind(int mID, String name, int age, String gender, int trackId) {
		super();
		MID = mID;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.trackId = trackId;
	}
	public int getMID() {
		return MID;
	}
	public void setMID(int mID) {
		MID = mID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	@Override
	public String toString() {
		return "CampusMind [MID=" + MID + ", name=" + name + ", age=" + age + ", gender=" + gender + ", trackId=" + trackId
				+ "]";
	}
	@Override
	public int compareTo(CampusMind o) {
		
		return this.getMID()-o.getMID();
	}

}
