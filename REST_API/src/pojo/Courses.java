package pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Courses {
	private HashMap<String, Object> Web;
	
	
	
	
	public String setWeb(Object object) {
		Web = (HashMap<String, Object>) object;
		return null;
		
	}

	private int Mobile;
	
	public int getMobile() {
		return Mobile;
	}

	public void setMobile(int i) {
		Mobile = i;
	}

	public HashMap<String, Object> getWeb() {
		return Web;
	}


	
}
