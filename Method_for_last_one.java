package com.company;

import java.util.Arrays;
import java.util.Stack;

public class Method_for_last_one {
    boolean is_main_city_exists = true;
    Stack<Integer> changed_vertices = new Stack<>();
    int [] d;
    int [] d_Copy;
    GRAPH graphis;

    public Method_for_last_one(GRAPH gr, String name_city_main){
        if(!gr.existsVertix(name_city_main))
            is_main_city_exists = false;
        else{
            graphis = gr;
            d = new int[gr.getSizeOfVertixes()];
            graphis.SPFA(name_city_main, d);
            d_Copy = Arrays.copyOf(d, d.length);
        }
    }

    public String how_less_amount_of_cities(String name_city_edge1, String name_city_edge2, int length){
        //automatycznie, jezeli po pierwszym razie wprowadzone pierwsze miasto nie bedzie istnialo, reszta pliku nie bedzie analizowana
        if(!is_main_city_exists)
            return "NIE";
        Integer index1 = graphis.getVertix(name_city_edge1);
        Integer index2 = graphis.getVertix(name_city_edge2);
        if(index1 == null || index2 == null || name_city_edge1.equals(name_city_edge2) || length <=0)
            return "NIE";
        while(!changed_vertices.isEmpty()){
            int v = changed_vertices.pop();
            d_Copy[v] = d[v];}
        int changed_amount = graphis.is_cities_shorter(d_Copy, index1, index2, length, changed_vertices);
        if(changed_amount <= 0)
            return "0";
        return (changed_amount > 100 ? "100+" : "" + changed_amount);
    }

    public void Change_city(String name_city_main){
        if(!graphis.existsVertix(name_city_main)) {
            is_main_city_exists = false;
            return;
        }
        d = new int[graphis.getSizeOfVertixes()];
        graphis.SPFA(name_city_main, d);
        d_Copy = Arrays.copyOf(d, d.length);
        changed_vertices.clear();
    }
}
