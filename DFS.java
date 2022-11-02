import java.util.ArrayList;
import java.util.List;

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
	
	
	public void writeDependencyTreeToJavaScript(Course course) {
		// pirate code from Graph's save() method, but only
		// output the course subgraph (dependency chain).
	}
	
	public void printEligibleCourses(int[] coursesTaken) {
		System.out.println("Print all the courses a student can take");
		
	}
	
}
