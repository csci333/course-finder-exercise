import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DFS {
	private int time = 0;
	private Graph graph;
	
	public DFS(Graph graph) {
		this.graph = graph;
	}
	
	public void printGraphTopologically() {
		System.out.println("Print graph topologically");
		
		// create empty list to store our topologically 
		// sorted courses:
		List<Course> sortedCourses = new ArrayList<Course>();
		
		// pre-processing step to initialize properties:
		for (Course course : this.graph.getCourses()) {
			course.color = DFSNode.Colors.WHITE;
			course.parent = null;
		}
		this.time = 0;
		for (Course course : this.graph.getCourses()) {
			if (course.color == DFSNode.Colors.WHITE) {
				dfsVisit(course, sortedCourses);
			}
		}
		// print all courses in topological order:
		for (Course course : sortedCourses) {
			System.out.println(course + "f timestamp: " + course.f + "\n");
		}
	}
	
	private void dfsVisit(Course course, List<Course> prequisites) {
		this.time++;
		course.d = this.time;
		course.color = DFSNode.Colors.GRAY;
		for (Course currentPrereq : course.dependencies) {
			if (currentPrereq.color == DFSNode.Colors.WHITE) {
				currentPrereq.parent = course;
				dfsVisit(currentPrereq, prequisites);
			}
		}
		course.color = DFSNode.Colors.BLACK;
		this.time++;
		course.f = this.time;
		prequisites.add(course);
	}
	
	public void printPrereqs(Course course) {
		List<Course> prereqs = new ArrayList<Course>();
		this.dfsVisit(course, prereqs);
		prereqs.remove(prereqs.size() - 1);
		for (Course c : prereqs) {
			System.out.println(c);
		}
	}
	
	public void savePrereqsToFile(Course course) {
		List<Course> prereqs = new ArrayList<Course>();
		this.dfsVisit(course, prereqs);
		for (Course c : prereqs) {
			System.out.println(c);
		}
		this.save(prereqs);
	}
	
	private String getOutputFilePath() {
		String dir = System.getProperty("user.dir");
        String separator = System.getProperty("file.separator");
        return dir + separator + "src/visualizer/course-graph.js";
	}
	
	@SuppressWarnings("unchecked")
	public void save(List<Course> courseChain) {
		JSONObject object = new JSONObject();
		JSONArray nodes = new JSONArray();
		JSONArray edges = new JSONArray();
		for (Course course : courseChain) {
			nodes.add(course.toNodeJSON());
			edges.addAll(course.toEdgesJSON());
    		}
		object.put("nodes", nodes);
		object.put("edges", edges);
		
        try (FileWriter file = new FileWriter(this.getOutputFilePath())) {
            file.write("const graphData = " + object.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public void writeDependencyTreeToJavaScript(Course course) {
		// pirate code from Graph's save() method, but only
		// output the course subgraph (dependency chain).
	}
	
	public void printEligibleCourses(int[] coursesTaken) {
		System.out.println("Print all the courses a student can take");
		
	}
	
}
