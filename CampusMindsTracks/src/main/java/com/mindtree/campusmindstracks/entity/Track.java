package main.java.com.mindtree.campusmindstracks.entity;



public class Track {
	private int trackId;
	private String name;
	private int duration;
	public Track() {
		super();
			}
	public Track(int trackId, String name, int duration) {
		super();
		this.trackId = trackId;
		this.name = name;
		this.duration = duration;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", name=" + name + ", duration=" + duration + "]";
	}
	
	
}
