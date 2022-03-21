package pojo;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Addplace {
	private int accuracy;
	private String name;
	private String address;
	private String website;
	private String language;
	private Location location;
	private List courses;
	

	
	public List getCourses() {
		return courses;
	}
	

	
	
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void setCourses(List l) {
		// TODO Auto-generated method stub
		
	}


	
}
