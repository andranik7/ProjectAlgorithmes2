
public class Vertex implements VertexInterface{
    private String stop_name;
    private double stop_lat;
    private double stop_lon;
    public String id;


    public Vertex(String stop_name, double stop_lat, double stop_lon, String id) {
        this.stop_name = stop_name;
        this.stop_lat = stop_lat;
        this.stop_lon = stop_lon;
        this.id = id;
    }

    public double getStop_lat() {
        return stop_lat;
    }

    public double getStop_lon() {
        return stop_lon;
    }

    public String getStop_name() {
        return stop_name;
    }
    
    public String getStop_id() {
    	return id;
    }

    public double distanceTo(Vertex dest){
        return Math.sqrt(Math.pow(stop_lat - dest.getStop_lat(),2) +
                Math.pow(stop_lon - dest.getStop_lon(),2));
    }
    
    public String toString() {
    	return stop_name;
    }
}

