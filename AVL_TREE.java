package com.company;


import java.util.Stack;

public class AVL_TREE {
    private Nodes root;

    //3 metody do poprawy wysokosci danego
    //settter
    public void set_root(Nodes checking) {
        root = checking;
    }

    //getter
    public Nodes get_Root() {
        return root;
    }

    //porównywarka
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private int get_Height_NULL_OR_INT(Nodes checked_for_height) {
        return (checked_for_height == null) ? 0 : checked_for_height.getHeight();
    }

    private void updateHeight(Nodes being_checked) {
        int quick_math = 1 + max(get_Height_NULL_OR_INT(being_checked.getLeft()),
                get_Height_NULL_OR_INT(being_checked.getRight()));
        being_checked.setHeight(quick_math);
    }

    private int weight_for_rotation(Nodes being_checked) {
        if (being_checked == null)
            return 0;
        return get_Height_NULL_OR_INT(being_checked.getLeft()) - get_Height_NULL_OR_INT(being_checked.getRight());
//        return leftINT - rightINT;
    }

    //    wszystkie rotacje :D
    private Nodes RR(Nodes A) {
//        System.out.println("RR");
        Nodes B = A.getLeft();
        Nodes II = B.getRight();
        B.setRight(A);
        A.setLeft(II);
        updateHeight(A);
        updateHeight(B);
        return B;
    }

    private Nodes LL(Nodes A) {
//        System.out.println("LL");
        Nodes B = A.getRight();
        Nodes II = B.getLeft();
        B.setLeft(A);
        A.setRight(II);
        updateHeight(A);
        updateHeight(B);
        return B;
    }

    private Nodes RL(Nodes A) {
//        System.out.println("RL");
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
//        System.out.println("LR");
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
    public Nodes insert(Nodes checking, String node_to_insert) {
        if (checking == null) {
            checking = new Nodes(node_to_insert);
            return checking;
        }
        //skrót od greater - equal- lower
        int gel = checking.getName_city().compareTo(node_to_insert);
        if (gel > 0)
            checking.setRight(insert(checking.getRight(), node_to_insert));
        else if (gel < 0)
            checking.setLeft(insert(checking.getLeft(), node_to_insert));
        else
            return checking;
        updateHeight(checking);
        int balance = weight_for_rotation(checking);
        if (balance > 1) {
            if (weight_for_rotation(checking.getLeft()) == -1)
                return LR(checking);
            else
                return RR(checking);
        } else if (balance < -1) {
            if (weight_for_rotation(checking.getRight()) == 1)
                return RL(checking);
            else
                return LL(checking);
        }
        return checking;
    }

    private Nodes find_Next_One(Nodes start) {
        Nodes temp = start;
        while (temp.getLeft() != null)
            temp = temp.getLeft();
        return temp;
    }

    public Nodes delete(Nodes checking, String node_to_delete) {
        if (checking == null)
            return checking;
        int gel = checking.getName_city().compareTo(node_to_delete);
        if (gel > 0)
            checking.setRight(delete(checking.getRight(), node_to_delete));
        else if (gel < 0)
            checking.setLeft(delete(checking.getLeft(), node_to_delete));
        else {
            if (checking.getLeft() == null && checking.getRight() == null)
                return null;
            else if (checking.getLeft() == null)
                return checking.getRight();
            else if (checking.getRight() == null)
                return checking.getLeft();
            else {
                //znajdowanie nastepnika
                Nodes next_one = find_Next_One(checking.getRight());
                //zastapienie nazwy miasta usuwanego wezla z nastepnikiem
                String temp_name_city = next_one.getName_city();
                checking.setName_city(temp_name_city);
                //usuwanie nastepnika
                checking.setRight(delete(checking.getRight(), temp_name_city));
            }
        }
        updateHeight(checking);
        int balance = weight_for_rotation(checking);
        if (balance > 1) {
            if (weight_for_rotation(checking.getLeft()) == -1)
                return LR(checking);
            else
                return RR(checking);
        } else if (balance < -1) {
            if (weight_for_rotation(checking.getRight()) == 1)
                return RL(checking);
            else
                return LL(checking);
        }
        return checking;
    }

    //pokazanie wszystkich elementów. Złożoność n
    public void show() {
        Stack<Nodes> lil_stack = new Stack<>();
        Stack<Integer> nr_of_space = new Stack<>();
        lil_stack.push(root);
        root.setLevel(0);
        nr_of_space.push(0);
        while (!lil_stack.isEmpty()) {
            Nodes x = lil_stack.pop();
            int y = nr_of_space.pop();
            System.out.println(printed(x, y));
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
    //pomoc w tworzeniu tabulacji
    private String printed(Nodes being_checked, int number_space) {
        String word = "";
        for (int i = 0; i < number_space; ++i)
            word += "  ";
        return (being_checked == null) ? word + "NULL" : word + being_checked.getName_city();
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
            count += Count_prefix(checking.getLeft(), Prefix_to_Find);
            count += Count_prefix(checking.getRight(), Prefix_to_Find);
        }
            return count;
    }
}
