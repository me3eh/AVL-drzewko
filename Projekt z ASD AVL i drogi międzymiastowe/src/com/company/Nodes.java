package com.company;
public class Nodes {
    private Nodes left, right;
    private String name_city;
    private int height;
    public Nodes(String name){
        name_city = name;
        height = 1;
    }
    //gettery
    public String getName_city() {
        return name_city;
    }
    public int getHeight() {return height;}
    public Nodes getLeft() {
        return left;
    }
    public Nodes getRight(){
        return right;
    }
    //settery
    public void setHeight(int height){
        this.height = height;
    }
    public void setLeft(Nodes left){this.left = left;}
    public void setRight(Nodes right){
            this.right = right;
    }
    public void setName_city(String name_city){this.name_city=name_city;}
}
