package com.company;

public class Edge {
    private String name;
    private int length;
    public Edge(String n, int l){
        length=l;
        name=n;
    }

    //gettery
    public int getLength() {return length;}
    public String getName() {return name;}
    //settery
    public void setLength(int length) {this.length = length;}
    public void setName(String name) {this.name = name;}
}
