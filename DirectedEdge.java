import org.json.simple.JSONObject;

public class DirectedEdge {
	
	public int sourceId;
	public int targetId;
	
	public DirectedEdge(JSONObject obj) {
		obj = (JSONObject)obj.get("data");
		this.targetId = ((Long)obj.get("target")).intValue();
		this.sourceId = ((Long)obj.get("source")).intValue();
	}
	
	public String toString() {
		return this.sourceId + " -> " + this.targetId;
	}
}
