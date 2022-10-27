import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class Graph {
	public Map<Integer, Course> courseLookup;
	public List<DirectedEdge> edges;
	
	public Graph() {
		this.courseLookup = new HashMap<Integer, Course>();
		this.edges = new ArrayList<DirectedEdge>();
		this.load();
	}
	
	public void addCourse(Course course) {
		// only add to the graph if the page is new:
		if (this.get(course.id) == null) {
			this.courseLookup.put(course.id, course);
    		}
	}
	
	
	public Course get(int id) {
		return this.courseLookup.get(id);
	}
	
	public int size() {
		return this.courseLookup.size();
	}
	

	public void print() {
		// print nodes:
	    	for (Course course : this.courseLookup.values()) {
	    		System.out.println(course);
	    	}
	    	
	    	// print edges:
	    	for (DirectedEdge edge : this.edges) {
	    		System.out.println(edge);
	    	}
    }
	
	
	private String getFilePath() {
		String dir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        return dir + separator + "src/courses.json";
	}
	
	public void load() {
		// loads the graph from the JSON file (if it exists):
		String filePath = this.getFilePath();
		File tempFile = new File(filePath);
		if (!tempFile.exists()) {
			System.out.println("Can't find courses.json file");
			return;
		}
		JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(filePath)) {

        		JSONObject data = (JSONObject)parser.parse(reader);
        		
        		JSONArray nodes = (JSONArray)data.get("nodes");
        		this.loadNodes(nodes);
        		
        		JSONArray edges = (JSONArray)data.get("edges");
        		this.loadEdges(edges);
	        

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadNodes(JSONArray nodes) {
		for (Object node : nodes) {
        		this.addCourse(new Course((JSONObject)node));
		}
	}
	
	private void loadEdges(JSONArray edges) throws Exception {
		for (Object edge : edges) {
			DirectedEdge de = new DirectedEdge((JSONObject)edge);
			Course target = this.get(de.targetId);
			Course source = this.get(de.sourceId);
			if (target == null || source == null) {
				throw new Exception("Invalid edge!");
			}
			target.dependencies.add(source);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void save() {
		JSONArray pages = new JSONArray();
		for (int key: this.courseLookup.keySet()) {
			pages.add(this.courseLookup.get(key).toJSON());
    	}
		
        try (FileWriter file = new FileWriter(this.getFilePath())) {
            file.write(pages.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
