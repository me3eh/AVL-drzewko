package com.company;

import java.util.*;

class Class_with_name_and_length{
    private final int index;
    private final int how_far;
    public Class_with_name_and_length(int index, int how_far){
        this.index = index;
        this.how_far = how_far;
    }
    public int getIndex(){return index;}
    public int getHow_far(){return how_far;}
}
class comparator implements Comparator<Class_with_name_and_length>{
    public int compare(Class_with_name_and_length o1, Class_with_name_and_length o2) {
        return o1.getHow_far()-o2.getHow_far();}
}

public class GRAPH {
    private HashMap<String, Integer> Indices_of_vertices = new HashMap<>();
    private ArrayList<String> Vertix = new ArrayList<>();
//    private ArrayList<ArrayList<Edge>> Edges_names_and_weights = new ArrayList<>();
    private ArrayList<List_of_indices> Lists_of_indices_for_every_vertix = new ArrayList<>();
//    private boolean remove(ArrayList<Edge> l, String s) {
//        return (l.removeIf(i -> i.getName().equals(s)));
//    }

    public void addVertix(String name_city){
        //implementacja bez hashmapy
//        Vertix.add(name_city);
//        Edges_names_and_weights.add(new ArrayList<>());
        Integer int_for_checking = Indices_of_vertices.putIfAbsent(name_city, Vertix.size());
        if(int_for_checking != null)
            return;
        Lists_of_indices_for_every_vertix.add(new List_of_indices());
        Vertix.add(name_city);
    }
    //pod warunkiem, ze te dwa miasta istnieja i nie sa sobie rowne
    public void addEdge(String first_vertex, String second_vertex, int length){
//        int [] index = new int[2];
//        //tylko dwie wartosci j przybierze;
//        int j = 0;
//        int i = 0;
//        for(String y : Vertix) {
//            if(y.equals(first_vertex)) {
//                index[0] = i;
//                ++j;
//            }
//            if(y.equals(second_vertex)){
//                index[1] = i;
//                ++j;
//            }
//        if(j == 2)
//            break;
//        ++i;
//        }
//        Edges_names_and_weights.get(index[1]).add(new Edge(first_vertex, length));
//        Edges_names_and_weights.get(index[0]).add(new Edge(second_vertex, length));
        Integer int_for_checking1 = Indices_of_vertices.get(first_vertex);
        Integer int_for_checking2 = Indices_of_vertices.get(second_vertex);
        if(int_for_checking1 == null || int_for_checking2 == null)
            return;
        Lists_of_indices_for_every_vertix.get(int_for_checking1).Add_Edge(second_vertex, length);
        Lists_of_indices_for_every_vertix.get(int_for_checking2).Add_Edge(first_vertex, length);
    }
    //usuwanie wierzcholka jako string, gdyz nie znamy dlugosci
    public void deleteVertix(String name_city){
//        int index = Vertix.indexOf(name_city);
//        ArrayList<Edge> temp_to_delete = Edges_names_and_weights.get(index);
//        for(Edge i : temp_to_delete){
//            int temp_index = Vertix.indexOf(i.getName());
//            //znalezienie odpowiedniego indexu w liscie miast
//            remove(Edges_names_and_weights.get(temp_index), name_city);
//        }
//        Vertix.remove(index);
//        Edges_names_and_weights.remove(index);
        Integer index = Indices_of_vertices.remove(name_city);
        if( index == null)
            return;
        for(Edge x: Lists_of_indices_for_every_vertix.get(index).getEdges())
            Lists_of_indices_for_every_vertix.get(Indices_of_vertices.get(x.getName())).Delete_Edge(name_city);
        if(index != Vertix.size() - 1){
            String temp = Vertix.get(Vertix.size()-1);
            Indices_of_vertices.put(temp, index);
            Vertix.set(index, temp);
        }
        Vertix.remove(Vertix.size() - 1);
        //if(index != Edges.size() - 1) {
        //            Edge temp = Edges.get(Edges.size() - 1);
        //            //zmiana indexu w hashmapie z
        //            indexes_for_vertices.put(temp.getName(), index);
        //            Edges.set(index, temp);
        //        }
        //        Edges.remove(Edges.size()-1);
    }
    public void deleteEdge(String Vertex_name_city, String edge_name_city){
//        int [] index = new int[2];
//        int i = 0;
//        int j = 0;
//        for(String y : Vertix) {
//            if(y.equals(Vertex_name_city)) {
//                index[0] = i;
//                ++j;
//            }
//            else if(y.equals(edge_name_city)){
//                index[1] = i;
//                ++j;
//            }
//            if(j == 2)
//                break;
//            ++i;
//        }
//        if(!remove(Edges_names_and_weights.get(index[0]), edge_name_city))
//            return false;
//        if(!remove(Edges_names_and_weights.get(index[1]), Vertex_name_city))
//            return false;
//        return true;
        Integer int_for_checking1 = Indices_of_vertices.get(Vertex_name_city);
        Integer int_for_checking2 = Indices_of_vertices.get(edge_name_city);
        if(int_for_checking1 == null || int_for_checking2 == null)
            return;
        Lists_of_indices_for_every_vertix.get(int_for_checking1).Delete_Edge(edge_name_city);
        Lists_of_indices_for_every_vertix.get(int_for_checking2).Delete_Edge(Vertex_name_city);
    }
    public void show(){
        String wo = "";
        for(String t: Vertix)
            wo += t;
        System.out.println(wo);
    }
    public void showEdge(){
        for(int i = 0; i<Vertix.size(); ++i) {
            System.out.print(Vertix.get(i) + "=");
            Lists_of_indices_for_every_vertix.get(i).show();
            if(Lists_of_indices_for_every_vertix.get(i).isEmpty())
                System.out.println();
        }
    }
    public String Dijkstra_SHORTEST_PATH(String from, String to) {
        Integer s = Indices_of_vertices.get(from);
        Integer dk = Indices_of_vertices.get(to);
        if( s==null || dk == null)
            return "NIE";
        //tablica odwiedzonych
        boolean[] visited = new boolean[Vertix.size()];
        //tablica poprzednikow
        int [] ancestors = new int[Vertix.size()];
        //tablica do kolejki priorytetowej
        int[] d = new int[Vertix.size()];
        PriorityQueue<Class_with_name_and_length> queue = new PriorityQueue<>(new comparator());
        int i = 0;
        for (int j = 0 ; j < Vertix.size() ; ++j)
            d[j] = Integer.MAX_VALUE;
//        int s = Vertix.indexOf(from);
//        int dk = Vertix.indexOf(to);
        d[s] = 0;
        boolean end = false;
        ancestors[s] = -1;
        //jako ile odwiedzonych bedzie traktowane
        queue.add(new Class_with_name_and_length(s ,d[s]));
        int using = 0;
        while (i != Vertix.size()) {
            //wyjecie z kolejki elementu
            do{
                if(queue.isEmpty()) {
                    if (d[dk] == Integer.MAX_VALUE)
                        return "NIE";
                    end = true;
                    break;
                }
                using = queue.remove().getIndex();
            }while(visited[using]);
            if(end)
                break;
            //zaznaczone ze odwiedzone
            visited[using] = true;
            //przejscie po sasiadach danego wierzcholka
            int iterator = 0;
//            for (Edge t : Edges_names_and_weights.get(using)) {
            for(Edge t : Lists_of_indices_for_every_vertix.get(using).getEdges()){
                //wziecie indexu sasiada
//                int index_of_d = Vertix.indexOf(t.getName());
                int index_of_d = Indices_of_vertices.get(t.getName());
//                if (d[using] + Edges_names_and_weights.get(using).get(iterator).getLength() < d[index_of_d]){
                if(d[using] + t.getLength() < d[index_of_d]){
//                    d[index_of_d] = d[using] + Edges_names_and_weights.get(using).get(iterator).getLength();
                    d[index_of_d] = d[using] + t.getLength();
                    //ustalenie poprzednkika
                    ancestors[index_of_d] = using;
                    //jezeli byla relaksacja krawedzi, wrzucamy dany wierzcholek do kolejki priorytetowej
                    if(!visited[index_of_d])
                        queue.add(new Class_with_name_and_length(index_of_d, d[index_of_d]));
                }
                ++iterator;
            }
            ++i;
        }
        int temp = dk;
        String way_to_go = "";
        do{
            //bierzemy poprzednika rozpatrywanego i przylepiamy go do stringa
            way_to_go += Vertix.get(temp) + " ";
            temp = ancestors[temp];
        }while (temp != -1);
        return way_to_go + d[dk];
    }
}
