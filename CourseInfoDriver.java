
public class CourseInfoDriver {

	private Graph graph;

    public CourseInfoDriver() {
    		this.graph = new Graph();
    }
    
    public static void printDashes(int numDashes) {
		for(int i = 0; i < numDashes; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
    
    public static void showMenu() {
    		System.out.println();
    		printDashes(50);
    		System.out.println("MENU");
    		printDashes(50);
    		System.out.println("1. Enter Your Courses");
    		System.out.println("2. View CSCI minor requirements");
    		System.out.println("3. View CSCI major requirements: Computer Systems");
    		System.out.println("4. View CSCI major requirements: Information Systems");
    		System.out.println("5. View course prerequisites");
    		System.out.println("6. Generate sample schedule");
    		printDashes(50);
    		System.out.println("");
    }
    
    public static void main(String[] args) {
    	
    		CourseInfoDriver searcher = new CourseInfoDriver();
    		searcher.graph.print();

    }

}