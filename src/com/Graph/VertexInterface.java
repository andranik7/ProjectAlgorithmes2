package com.Graph;

interface VertexInterface {
    public double getStop_lat();
    public double getStop_lon();
    public String getStop_name();
    public String getStop_id();
    public void setHeuritic(double heuritic);
    public double getHeuritic();
    public double distanceTo(Vertex dest);
    public boolean isLessThan (Vertex comp, Vertex dest);

}
