
public abstract class DFSNode {

	public enum Colors {WHITE, GRAY, BLACK};
	public Colors color = Colors.WHITE;
	public Course parent;
	public int d; // start timestamp
	public int f; // finishing timestamp
}
