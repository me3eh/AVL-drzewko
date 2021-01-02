package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class List_of_indices {
    HashMap<String, Integer> indexes_for_vertices = new HashMap<>();
    ArrayList<Edge> Edges = new ArrayList<>();
    public void Add_Edge(String name_of_city, int length){
        Integer int_for_checking = indexes_for_vertices.putIfAbsent(name_of_city, Edges.size());
        if(int_for_checking == null)
            Edges.add(new Edge(name_of_city, length));
    }
    public void Delete_Edge(String second){
        Integer index = indexes_for_vertices.remove(second);
        if( index == null)
            return;
        if(index != Edges.size() - 1) {
            Edge temp = Edges.get(Edges.size() - 1);
            //zmiana indexu w hashmapie z
            indexes_for_vertices.put(temp.getName(), index);
            Edges.set(index, temp);
        }
        Edges.remove(Edges.size()-1);
    }
    public ArrayList<Edge> getEdges(){
        return Edges;
    }
    public void show(){
        int iterator = 0;
        for(Edge t : Edges) {
            System.out.print(iterator != Edges.size() - 1 ?
                    t.getName() + "->" + t.getLength() + ", " :
                    t.getName() + "->" + t.getLength()+"\n");
            ++iterator;
        }
    }
    public boolean isEmpty(){
        return Edges.isEmpty();
    }
}
