package com.Graph;

interface VertexInterface {
    public double getStop_lat();
    public double getStop_lon();
    public String getStop_name();
    public String getStop_id();
    public double distanceTo(Vertex dest);

}
