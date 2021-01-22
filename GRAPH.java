package com.company;
import java.util.*;


    class Class_with_name_and_length {
        private final int index;
        private final int how_far;

        public Class_with_name_and_length(int index, int how_far) {
            this.index = index;
            this.how_far = how_far;
        }

        public int getIndex() {return index;}

        public int getHow_far() {
            return how_far;
        }
    }

    class comparator implements Comparator<Class_with_name_and_length> {
        public int compare(Class_with_name_and_length o1, Class_with_name_and_length o2) {
            return o1.getHow_far() - o2.getHow_far();
        }
    }

    public class GRAPH {
        private HashMap<String, Integer> Indices_of_vertices = new HashMap<>();
        private ArrayList<String> Vertix = new ArrayList<>();
        private ArrayList<List_of_indices> Lists_of_indices_for_every_vertix = new ArrayList<>();

        public void addVertix(String name_city) {
            Integer int_for_checking = Indices_of_vertices.putIfAbsent(name_city, Vertix.size());
            if (int_for_checking != null)
                return;
            Lists_of_indices_for_every_vertix.add(new List_of_indices());
            Vertix.add(name_city);
        }

        public void addEdge(String first_vertex, String second_vertex, int length) {
            Integer int_for_checking1 = Indices_of_vertices.get(first_vertex);
            Integer int_for_checking2 = Indices_of_vertices.get(second_vertex);
            if (int_for_checking1 == null || int_for_checking2 == null || length <= 0 || first_vertex.equals(second_vertex))
                return;
            Lists_of_indices_for_every_vertix.get(int_for_checking1).Add_Edge(second_vertex, length);
            Lists_of_indices_for_every_vertix.get(int_for_checking2).Add_Edge(first_vertex, length);
        }

        public void deleteVertix(String name_city) {
            Integer index = Indices_of_vertices.remove(name_city);
            if (index == null)
                return;
            //dla każdego sąsiada danego wierzcholka usunac obecnosc tego wierzcholka
            for (Edge x : Lists_of_indices_for_every_vertix.get(index).getEdges())
                Lists_of_indices_for_every_vertix.get(Indices_of_vertices.get(x.getName())).Delete_Edge(name_city);
            if (index != Vertix.size() - 1) {
                String temp = Vertix.get(Vertix.size() - 1);
                Lists_of_indices_for_every_vertix.set(index, Lists_of_indices_for_every_vertix.get(Lists_of_indices_for_every_vertix.size() - 1));
                Indices_of_vertices.put(temp, index);
                Vertix.set(index, temp);
            }
            Vertix.remove(Vertix.size() - 1);
            Lists_of_indices_for_every_vertix.remove(Lists_of_indices_for_every_vertix.size() - 1);
        }

        public void deleteEdge(String Vertex_name_city, String edge_name_city) {
            Integer int_for_checking1 = Indices_of_vertices.get(Vertex_name_city);
            Integer int_for_checking2 = Indices_of_vertices.get(edge_name_city);
            if (int_for_checking1 == null || int_for_checking2 == null || Vertex_name_city.equals(edge_name_city))
                return;
            Lists_of_indices_for_every_vertix.get(int_for_checking1).Delete_Edge(edge_name_city);
            Lists_of_indices_for_every_vertix.get(int_for_checking2).Delete_Edge(Vertex_name_city);
        }

        public void showEdge() {
            for (int i = 0; i < Vertix.size(); ++i) {
                System.out.print(Vertix.get(i) + "=");
                Lists_of_indices_for_every_vertix.get(i).show();
                if (Lists_of_indices_for_every_vertix.get(i).isEmpty())
                    System.out.println();
            }
        }
        private String way_to_String(int index, int [] ancestors){
            String k = "" + Vertix.get(index);
            int temp = ancestors[index];
            while(temp != -1) {
                k = Vertix.get(temp) + "->" + k;
                temp = ancestors[temp];
            }
            return k;
        }

        public String Dijkstra_SHORTEST_PATH(String from, String to, boolean show_way) {
            Integer index_from = Indices_of_vertices.get(from);
            Integer index_to = Indices_of_vertices.get(to);
            if (index_from == null || index_to == null || from.equals(to))
                return "NIE";
            boolean[] visited = new boolean[Vertix.size()];
            int[] d = new int[Vertix.size()];
            int[] ancestors = new int[Vertix.size()];
            PriorityQueue<Class_with_name_and_length> queue = new PriorityQueue<>(new comparator());
            for (int j = 0; j < Vertix.size(); ++j)
                d[j] = Integer.MAX_VALUE;
            d[index_from] = 0;
            ancestors[index_from] = -1;
            queue.add(new Class_with_name_and_length(index_from, d[index_from]));
            int using;
            while (!queue.isEmpty()) {
                using = queue.remove().getIndex();
                if(visited[using])
                    continue;
                if (using == index_to) {
                    if (show_way)
                        return d[index_to] + ". Droga:" + way_to_String(index_to, ancestors);
                    else
                        return "" + d[index_to];
                }
                visited[using] = true;
                for (Edge t : Lists_of_indices_for_every_vertix.get(using).getEdges()) {
                    int index_of_d = Indices_of_vertices.get(t.getName());
                    if (d[using] + t.getLength() < d[index_of_d]) {
                        d[index_of_d] = d[using] + t.getLength();
                        ancestors[index_of_d] = using;
                        queue.add(new Class_with_name_and_length(index_of_d, d[index_of_d]));
                    }
                }
            }
            return "NIE";
        }

        public void SPFA(String name_city, int[] d) {
            boolean[] visited = new boolean[Vertix.size()];
            for (int i = 0; i < Vertix.size(); ++i)
                d[i] = Integer.MAX_VALUE;
            Integer index = Indices_of_vertices.get(name_city);
            d[index] = 0;
            PriorityQueue<Class_with_name_and_length> queue = new PriorityQueue<>(new comparator());
            queue.add(new Class_with_name_and_length(index, d[index]));
            while (!queue.isEmpty()) {
                int v = queue.remove().getIndex();
                if(visited[v])
                    continue;
                visited[v] = true;
                //najdluzsza droga
                for (Edge t : Lists_of_indices_for_every_vertix.get(v).getEdges()) {
                    int index_of_t = Indices_of_vertices.get(t.getName());
                    if (d[v] + t.getLength() < d[index_of_t]) {
                        d[index_of_t] = d[v] + t.getLength();
                        queue.add(new Class_with_name_and_length(index_of_t, d[index_of_t]));
                    }
                }
            }
        }

        public int is_cities_shorter(int[] d, int index1, int index2, int length, Stack<Integer> changed_vertices) {
            PriorityQueue<Class_with_name_and_length> queue = new PriorityQueue<>(new comparator());
            boolean [] visited = new boolean[Vertix.size()];
            if (d[index1] == d[index2])
                return 0;
            int start = (d[index1] > d[index2] ? index2 : index1);
            int second_one = start == index1 ? index2 : index1;
            if (d[start] + length < d[second_one]) {
                d[second_one] = d[start] + length;
                changed_vertices.push(second_one);
                queue.add(new Class_with_name_and_length(second_one, d[second_one]));
            } else
                return 0;
            int sum = 0;
            while (!queue.isEmpty()) {
                int v = queue.remove().getIndex();
                if(visited[v])
                    continue;
                visited[v] = true;
                ++sum;
                if (sum == 101)
                    return sum;
                for (Edge t : Lists_of_indices_for_every_vertix.get(v).getEdges()) {
                    int index_of_t = Indices_of_vertices.get(t.getName());
                    if (d[v] + t.getLength() < d[index_of_t]) {
                        d[index_of_t] = d[v] + t.getLength();
                        changed_vertices.push(index_of_t);
                        queue.add(new Class_with_name_and_length(index_of_t, d[index_of_t]));
                    }
                }
            }
            return sum;
        }

        public boolean existsVertix(String name_city) {
            return Indices_of_vertices.get(name_city) == null ? false : true;
        }

        public Integer getVertix(String name_city) {
            return Indices_of_vertices.get(name_city);
        }

        public int getSizeOfVertixes() {
            return Vertix.size();
        }
    }
