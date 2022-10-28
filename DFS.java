
public class DFS {
	private int time = 0;
	private Graph graph;
	
	public DFS(Graph graph) {
		this.graph = graph;
	}
	
	// I give you a source node, and you 
	// print a list of prereqs to the screen:
	public void printPrequisites(Course source) {
		// for you to figure out, 
		// but you'll use a DFS on the source node
		System.out.println("Print all of the dependencies for " + source);
	}
	
	public void printGraphTopologically() {
		System.out.println("Print graph topologically");
	}
	
	public void writeDependencyTreeToJavaScript(Course course) {
		// pirate code from Graph's save() method, but only
		// output the course subgraph (dependency chain).
	}
	
	public void printEligibleCourses(int[] coursesTaken) {
		System.out.println("Print all the courses a student can take");
		
	}
	
}
