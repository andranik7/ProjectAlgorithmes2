public class Edge implements EdgeInterface{
    Vertex source;
    Vertex destination;
    double weight;

    public Edge(Vertex source, Vertex destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex getSource() {
    	return source;
    }
    
    public Vertex getDest() {
    	return destination;
    }
    
    public double getWeight() {
    	return weight;
    }
}
