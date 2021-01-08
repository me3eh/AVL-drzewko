package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static boolean for_file_only = true;
    static Nodes file_control(AVL_TREE poob, GRAPH graphis) throws FileNotFoundException {
        File file = new File("C:\\Users\\matt3\\Desktop\\testy_ASD\\Projekt\\projekt1_in8.txt");
        Scanner in = new Scanner(file);
        Method_for_last_one meth = null;
        boolean show_time = false;
        for(String line = in.nextLine() ; line != null ; line = (in.hasNextLine()) ? in.nextLine() : null) {
            String[] command_and_data = line.split(" ");
            switch (command_and_data[0]) {
                case "DM":
                    poob.set_root(poob.insert(poob.get_Root(), command_and_data[1]));
                    graphis.addVertix(command_and_data[1]);
                    break;
                case "UM":
                    poob.set_root(poob.delete(poob.get_Root(), command_and_data[1]));
                    graphis.deleteVertix(command_and_data[1]);
                case "WM":
//                        start = System.nanoTime();
                    System.out.println((poob.find(poob.get_Root(), command_and_data[1]) ? "TAK" : "NIE"));
                    break;
                case "LM":
                    System.out.println(poob.Count_prefix(poob.get_Root(), command_and_data[1]));
                    break;
                case "WY":
                    poob.show2(poob.get_Root(),0);
                    break;
                case "DD":
                    if(!command_and_data[1].equals(command_and_data[2]) && Integer.parseInt(command_and_data[3]) > 0)
                        graphis.addEdge(command_and_data[1], command_and_data[2], Integer.parseInt(command_and_data[3]));
                    break;
                case "UD":
                    if(!command_and_data[1].equals(command_and_data[2]))
                        graphis.deleteEdge(command_and_data[1], command_and_data[2]);
                    break;
                case "ND":
                    if(!command_and_data[1].equals(command_and_data[2]))
                        System.out.println(graphis.Dijkstra_SHORTEST_PATH(command_and_data[1], command_and_data[2]));
                    else
                        System.out.println("NIE");
                    break;
                case "E":
                    graphis.showEdge();
                    break;
                case "IS":
                    if(!show_time){
                        meth = new Method_for_last_one(graphis);
                        show_time = true;
                    }
                    if(!command_and_data[2].equals(command_and_data[3]) && Integer.parseInt(command_and_data[4]) > 0)
                        System.out.println(meth.how_less_amount_of_cities(command_and_data[1], command_and_data[2], command_and_data[3], Integer.parseInt(command_and_data[4])));
            }
        }
        return poob.get_Root();
    }
    static void MENU_text(int choice){
        if(choice == 0) {
            System.out.println("---------------");
            System.out.println("1. Dodaj miasto");
            System.out.println("2. Usuń miasto");
            System.out.println("3. Wyszukaj miasto o danej nazwie");
            System.out.println("4. Wyszukaj liczbę miast o danym prefiksie nazw");
            System.out.println("5. Wypisz drzewko AVL");
            System.out.println("6. Wypełnienie zadań z pliku");
            System.out.println("=---=||-- GRAF --||=---=");
            System.out.println("A. Dodaj droge miedzy miastami");
            System.out.println("B. Usun droge miedzy miastami");
            System.out.println("C. Oblicz droge miedzy dwoma miastami (jej przebieg i dlugosc)");
            System.out.println("Q. Wyjdź z programu");
            System.out.println("Twój wybór:");
        }
        else if(choice == 1)
            System.out.println("Podaj miasto do dodania:");
        else if(choice == 2)
            System.out.println("Podaj miasto do usunięcia:");
        else if(choice == 3)
            System.out.println("Podaj miasto do wyszukania");
        else if(choice == 4)
            System.out.println("Podaj prefiks:");
        else if(choice == 5)
            System.out.println("Podaj pierwsze miasto");
        else if(choice == 6)
            System.out.println("Podaj drugie miasto");
        else if(choice == 7)
            System.out.println("Podaj dlugosc");
        else if(choice == 8)
            System.out.println("Podaj nazwe glownego miasta");
        else
            System.out.println("Lel zła opcja.");
    }

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        if(!for_file_only) {
            System.out.println("Witamy w becie programu Sieć Drogowa");
            String word_for_choices;
            int integer_for_choices;
            String word_for_choices_additional_graphs;
            boolean show_must_go_on = true;
//            Wczytywanie z pliku
            String ch;
            AVL_TREE poob = new AVL_TREE();
            GRAPH graphis = new GRAPH();
            Method_for_last_one meth = null;
            boolean show_time = false;
            Scanner in = new Scanner(System.in);
            Scanner inInt = new Scanner(System.in);
            do {
                MENU_text(0);
                do
                    ch = in.next();
                while(ch.length() != 1);
                switch (ch.charAt(0)) {
                    case '1':
                        MENU_text(1);
                        word_for_choices = in.next();
                        poob.set_root(poob.insert(poob.get_Root(), word_for_choices));
                        graphis.addVertix(word_for_choices);
                        break;
                    case '2':
                        MENU_text(2);
                        word_for_choices = in.next();
                        poob.set_root(poob.delete(poob.get_Root(), word_for_choices));
                        graphis.deleteVertix(word_for_choices);
                        break;
                    case '3':
                        MENU_text(3);
                        word_for_choices = in.next();
                        System.out.println((poob.find(poob.get_Root(), word_for_choices) ? "TAK" : "NIE"));
                        break;
                    case '4':
                        MENU_text(4);
                        word_for_choices = in.next();
                        int count = poob.Count_prefix(poob.get_Root(), word_for_choices);
                        System.out.println("Znaleziono: " + count + " o prefiksie " + word_for_choices);
                        break;
                    case '5':
                        poob.show2(poob.get_Root(), 0);
                        break;
                    case '6':
                        poob.set_root(file_control(poob, graphis));
                        break;
                    case 'a':
                    case 'A':
                        MENU_text(5);
                        word_for_choices = in.next();
                        MENU_text(6);
                        word_for_choices_additional_graphs=in.next();
                        MENU_text(7);
                        integer_for_choices=inInt.nextInt();
                        //warunek jezeli sie sobie nie rownaja, istnieja juz stworzone takie wierzcholki oraz długosc krawedzi > 0
                        if(!word_for_choices.equals(word_for_choices_additional_graphs) && integer_for_choices > 0)
                            graphis.addEdge(word_for_choices, word_for_choices_additional_graphs, integer_for_choices);
                        break;
                    case 'b':
                    case 'B':
                        MENU_text(5);
                        word_for_choices = in.next();
                        MENU_text(6);
                        word_for_choices_additional_graphs = in.next();
                        if(!word_for_choices.equals(word_for_choices_additional_graphs))
                            graphis.deleteEdge(word_for_choices, word_for_choices_additional_graphs);
                        break;
                    case 'c':
                    case 'C':
                        MENU_text(5);
                        word_for_choices = in.next();
                        MENU_text(6);
                        word_for_choices_additional_graphs = in.next();
                        if(!word_for_choices.equals(word_for_choices_additional_graphs))
                            System.out.println(graphis.Dijkstra_SHORTEST_PATH(word_for_choices, word_for_choices_additional_graphs));
                        break;
                    case 'e':
                    case 'E':
                        graphis.showEdge();
                        break;
                    case 'f':
                    case 'F':
                        if(!show_time) {
                            meth = new Method_for_last_one(graphis);
                            show_time = true;
                        }
                        MENU_text(8);
                        String word1 = in.next();
                        MENU_text(5);
                        word_for_choices = in.next();
                        MENU_text(6);
                        word_for_choices_additional_graphs = in.next();
                        MENU_text(7);
                        integer_for_choices = inInt.nextInt();
                        meth.how_less_amount_of_cities(word1, word_for_choices, word_for_choices_additional_graphs, integer_for_choices);
                        break;
                    case 'q':
                    case 'Q':
                        show_must_go_on = false;
                        break;
                    default:
                        MENU_text(-2);
                }
            } while (show_must_go_on);
            in.close();
        }
        else{
            long start = System.nanoTime();
            AVL_TREE poob = new AVL_TREE();
            GRAPH graphis = new GRAPH();
//            Method_for_last_one meth = null;
            poob.set_root(file_control(poob, graphis));
            System.out.println("Twoj czas to:" + ((System.nanoTime() - start) /1000000000.0f));
            }
    }
}