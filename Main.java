package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static boolean for_file_only = true;
    static Nodes file_control(AVL_TREE poob, GRAPH graphis) throws FileNotFoundException {
        File file = new File("C:\\Users\\matt3\\Desktop\\testy_ASD\\Projekt\\polecenie.txt");
        Scanner in = new Scanner(file);
        for(String line = in.nextLine() ; line != null ; line = (in.hasNextLine()) ? in.nextLine() : null) {
            String[] command_and_data = line.split(" ");
            switch (command_and_data[0]) {
                case "DM":
                    if(!poob.find(poob.get_Root(), command_and_data[1])) {
                        poob.set_root(poob.insert(poob.get_Root(), command_and_data[1]));
                        graphis.addVertix(command_and_data[1]);
                    }
                    break;
                case "UM":
                    if(poob.find(poob.get_Root(), command_and_data[1])) {
                        poob.set_root(poob.delete(poob.get_Root(), command_and_data[1]));
                        graphis.deleteVertix(command_and_data[1]);
                    }
                    break;
                case "WM":
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
                    break;
                case "E":
                    graphis.showEdge();
                    break;
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
            AVL_TREE poob = new AVL_TREE();
            GRAPH graphis = new GRAPH();
//            graphis.addVertix("Bielsk");
//            graphis.addVertix("Bialystok");
//            graphis.addVertix("Poznan");
//            graphis.addVertix("Gdansk");
//            graphis.addVertix("Poznaniak");
//            if(poob.find(poob.get_Root(),"A") && poob.find(poob.get_Root(),"B")){
//
//            }
//            Strni
//            if(poob.find(poob.get_Root()), )
//            graphis.addVertix("A");
//            graphis.addVertix("B");
//            graphis.addVertix("C");
//            graphis.addVertix("D");
//            graphis.addVertix("E");
//            graphis.deleteVertix("C");
//            graphis.addEdge("A", "B", 3);
//            graphis.addEdge("C", "A", 1);
//            graphis.addEdge("D", "B", 5);
//            graphis.addEdge("B", "C", 7);
//            graphis.addEdge("E", "B", 1);
//            graphis.addEdge("D", "C", 2);
//            graphis.addEdge("D", "E", 7);
//            System.out.println(graphis.showEdge());
//            System.out.print(graphis.Dijkstra_SHORTEST_PATH("C" , "E"));
            Scanner in = new Scanner(System.in);
            Scanner inInt = new Scanner(System.in);
            do {
                MENU_text(0);
                switch (in.next().charAt(0)) {
                    case '1':
                        MENU_text(1);
                        word_for_choices = in.next();
                        if(!poob.find(poob.get_Root(), word_for_choices)) {
                            poob.set_root(poob.insert(poob.get_Root(), word_for_choices));
                            graphis.addVertix(word_for_choices);
                        }
                        break;
                    case '2':
                        MENU_text(2);
                        word_for_choices = in.next();
                        if(poob.find(poob.get_Root(), word_for_choices)) {
                            poob.set_root(poob.delete(poob.get_Root(), word_for_choices));
                            graphis.deleteVertix(word_for_choices);
                        }
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
                        if(!word_for_choices.equals(word_for_choices_additional_graphs)
                                && integer_for_choices > 0)
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
            AVL_TREE poob = new AVL_TREE();
            GRAPH graphis = new GRAPH();
            poob.set_root(file_control(poob, graphis));
            }
    }
}
