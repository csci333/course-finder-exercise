import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.json.simple.JSONObject;

public class Course { 

	public int id;
	public String title;
	public boolean compSysRequirement;
	public boolean infoSysRequirement;
	public boolean minorRequirement;
	public List<Course> dependencies = new ArrayList<Course>();
	
	public String toString() {
		return this.id + ": " + this.title + 
				" (requires: " + this.dependenciesToString() + ")" + 
				"\n      * computer systems=" + this.compSysRequirement + 
				"\n      * information systems=" + this.infoSysRequirement + 
				"\n      * minor=" + this.minorRequirement + "\n";
	}
	
	public String dependenciesToString() {
		if (this.dependencies.size() == 0) {
			return "No prereqs";
		}
		StringJoiner sj = new StringJoiner(", ");
		for (Course dependency : this.dependencies) {
			sj.add(((Integer)dependency.id).toString());
		}
		return sj.toString();
	}
	
	
	public Course(JSONObject obj) {
		obj = (JSONObject)obj.get("data");
		Long id = (Long)obj.get("id");
		this.id = id.intValue();
		this.title = (String)obj.get("title");
		Object sys = (Object)obj.get("comp_sys_requirement");
		Object info = (Object)obj.get("info_sys_requirement");
		Object minor = (Object)obj.get("minor_requirement");
		this.compSysRequirement = (sys != null) ? (boolean)sys : false;
		this.infoSysRequirement = (info != null) ? (boolean)info : false;
		this.minorRequirement = (minor != null) ? (boolean)minor : false;
	}
	
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject page = new JSONObject();
		page.put("id", this.id);
		page.put("title", this.title);
		page.put("comp_sys_requirement", this.compSysRequirement);
		page.put("info_sys_requirement", this.infoSysRequirement);
		page.put("minor_requirement", this.minorRequirement);
		return page;
	}

}
