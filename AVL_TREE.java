package com.company;

import java.util.TreeMap;

public class AVL_TREE {
    private Nodes root;

    public void set_root(Nodes checking) {root = checking;}

    public Nodes get_Root() {return root;}

    private void updateHeight(Nodes being_checked) {
        int LEFT = (being_checked.getLeft() == null) ? 0 : being_checked.getLeft().getHeight();
        int RIGHT = (being_checked.getRight() == null) ? 0 : being_checked.getRight().getHeight();
        being_checked.setHeight(1 + Math.max(LEFT, RIGHT));
    }

    private int weight_for_rotation(Nodes being_checked) {
        if (being_checked == null)
            return 0;
        int LEFT = being_checked.getLeft() == null ? 0 : being_checked.getLeft().getHeight();
        int RIGHT = being_checked.getRight() == null ? 0 : being_checked.getRight().getHeight();
        return LEFT - RIGHT;
//        return get_Height_NULL_OR_INT(being_checked.getLeft()) - get_Height_NULL_OR_INT(being_checked.getRight());
//        return leftINT - rightINT;
    }

    //    wszystkie rotacje :D, nazwy takie same jak na wykladzie w celu latwiejszej identyfikacji zamienianych wezlow
    private Nodes RR(Nodes A) {
        Nodes B = A.getLeft();
        Nodes II = B.getRight();
        B.setRight(A);
        A.setLeft(II);
        updateHeight(A);
        updateHeight(B);
        return B;
    }

    private Nodes LL(Nodes A) {
        Nodes B = A.getRight();
        Nodes II = B.getLeft();
        B.setLeft(A);
        A.setRight(II);
        updateHeight(A);
        updateHeight(B);
        return B;
    }

    private Nodes RL(Nodes A) {
        Nodes B = A.getRight();
        Nodes C = B.getLeft();
        Nodes III = C.getRight();
        Nodes II = C.getLeft();
        C.setLeft(A);
        C.setRight(B);
        B.setLeft(III);
        A.setRight(II);
        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
        return C;
    }

    private Nodes LR(Nodes A) {
        Nodes B = A.getLeft();
        Nodes C = B.getRight();
        Nodes III = C.getRight();
        Nodes II = C.getLeft();
        C.setLeft(B);
        C.setRight(A);
        B.setRight(II);
        A.setLeft(III);
        updateHeight(A);
        updateHeight(B);
        updateHeight(C);
        return C;
    }

    //dodanie elementu do drzewa. Zwraca łączenie, bo może być zastąpiony korzeń.
    public Nodes insert(Nodes checking, String node_to_insert, TRUE_OR_FALSE truth_to_quicken) {
        if (checking == null) {
            checking = new Nodes(node_to_insert);
            return checking;
        }
        if(truth_to_quicken.getTruth())
            return checking;
        //skrót od greater - equal- lower - porownanie stringow.
        int gel = checking.getName_city().compareTo(node_to_insert);
        if (gel > 0)
            checking.setRight(insert(checking.getRight(), node_to_insert, truth_to_quicken));
        else if (gel < 0)
            checking.setLeft(insert(checking.getLeft(), node_to_insert, truth_to_quicken));
        else
            return checking;
        updateHeight(checking);
        int balance = weight_for_rotation(checking);
        if(balance == 0)
            truth_to_quicken.Change_Truth();
        if (balance > 1) {
            truth_to_quicken.Change_Truth();
            return (weight_for_rotation(checking.getLeft()) == -1) ? LR(checking) : RR(checking);
        }
        else if (balance < -1) {
            truth_to_quicken.Change_Truth();
            return (weight_for_rotation(checking.getRight()) == 1) ? RL(checking) : LL(checking);
        }
        return checking;
    }

    //prywatna metoda do znajdowania nastepnika - maks. w lewo
    private Nodes find_Next_One(Nodes start) {
        Nodes temp = start;
        while (temp.getLeft() != null)
            temp = temp.getLeft();
        return temp;
    }

    //usuwanie wezla w drzewie AVL
    public Nodes delete(Nodes checking, String node_to_delete, TRUE_OR_FALSE truth_to_quicken) {
        if (checking == null)
            return null;
        int gel = checking.getName_city().compareTo(node_to_delete);
        if (gel > 0)
            checking.setRight(delete(checking.getRight(), node_to_delete, truth_to_quicken));
        else if (gel < 0)
            checking.setLeft(delete(checking.getLeft(), node_to_delete, truth_to_quicken));
        else {
            //jezeli brak dzieci, zastap dany wezel null'em
            if (checking.getLeft() == null && checking.getRight() == null)
                return null;
            //jezeli tylko prawe dziecko, przylacz je do ojca
            else if (checking.getLeft() == null)
                return checking.getRight();
            //jezeli tylko lewe dziecko, przylacz je do ojca
            else if (checking.getRight() == null)
                return checking.getLeft();
            //jezeli dwoje dzieci, znajdz nastepnik, zamien jego nazwe z naszym rozpatrywanym wezlem i usun nastepnik
            else {
                //znajdowanie nastepnika
                Nodes next_one = find_Next_One(checking.getRight());
                //zastapienie nazwy miasta usuwanego wezla z nastepnikiem
                String temp_name_city = next_one.getName_city();
                checking.setName_city(temp_name_city);
                //usuwanie nastepnika
                checking.setRight(delete(checking.getRight(), temp_name_city, truth_to_quicken));
            }
        }
        //pozycjonowanie drzewa, aktualizacja wysokosci
        updateHeight(checking);
        int balance = weight_for_rotation(checking);
        if (balance > 1)
            return (weight_for_rotation(checking.getLeft()) == -1) ? LR(checking) : RR(checking);
        else if (balance < -1)
            return (weight_for_rotation(checking.getRight()) == 1) ? RL(checking) : LL(checking);
        return checking;
    }
    /*
    //pokazanie wszystkich elementów. Złożoność n, iteracyjnie ze stosem
    public void show() {
        if(root == null) {
            System.out.println("NULL");
            return;
        }
        Stack<Nodes> lil_stack = new Stack<>();
        Stack<Integer> nr_of_space = new Stack<>();
        lil_stack.push(root);
        root.setLevel(0);
        nr_of_space.push(0);
        while (!lil_stack.isEmpty()) {
            Nodes x = lil_stack.pop();
            int y = nr_of_space.pop();
            String word = "";
            for (int i = 0; i < y; ++i)
                word += "\t";
            System.out.println((x == null) ? word + "NULL" : word + x.getName_city());
            if (x == null)
                continue;
            if (x.getLeft() != null)
                x.getLeft().setLevel(x.getLevel() + 1);
            if (x.getRight() != null)
                x.getRight().setLevel(x.getLevel() + 1);
            for (int i = 0; i < 2; ++i)
                nr_of_space.push(x.getLevel() + 1);
            lil_stack.push(x.getRight());
            lil_stack.push(x.getLeft());
        }
    }
    */
    //wypisanie drzewa. Rekurencyjnie
    public void show2(Nodes origin, int number_space){
        String word = "";
        for (int i = 0; i < number_space; ++i)
            word += "\t";
        System.out.println((origin == null) ? word + "NULL" : word + origin.getName_city());
        if(origin != null){
            show2(origin.getLeft(), number_space+1);
            show2(origin.getRight(), number_space+1);
        }
    }
    //znalezienie danego elementu
    public boolean find(Nodes origin, String being_finded) {
        if (origin == null)
            return false;
        boolean found;
        int gel = origin.getName_city().compareTo(being_finded);
        if (gel > 0)
            found = find(origin.getRight(), being_finded);
        else if (gel < 0)
            found = find(origin.getLeft(), being_finded);
        else
            found = true;
        return found;
    }

    public int Count_prefix(Nodes checking, String Prefix_to_Find) {
        if(checking == null)
            return 0;
        int count = 0;
        int gel;
        if(checking.getName_city().length() < Prefix_to_Find.length())
            gel = checking.getName_city().compareTo(Prefix_to_Find);
        else {
            String substring_of_city = checking.getName_city().substring(0, Prefix_to_Find.length());
            gel = substring_of_city.compareTo(Prefix_to_Find);
        }
        if(gel > 0)
            count += Count_prefix(checking.getRight(), Prefix_to_Find);
        else if(gel < 0)
            count += Count_prefix(checking.getLeft(), Prefix_to_Find);
        else{
            count += 1;
            count += Count_prefix(checking.getRight(), Prefix_to_Find);
            count += Count_prefix(checking.getLeft(), Prefix_to_Find);
        }
            return count;
    }
}
