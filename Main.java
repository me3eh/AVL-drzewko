package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static boolean for_file_only = false;
    static Nodes file_control(AVL_TREE poob) throws FileNotFoundException {
        File file = new File("C:\\Users\\matt3\\Desktop\\testy_ASD\\Projekt\\polecenie.txt");
        Scanner in = new Scanner(file);
        for(String line = in.nextLine() ; line != null ; line = (in.hasNextLine()) ? in.nextLine() : null) {
            String[] command_and_data = line.split(" ");
            switch (command_and_data[0]) {
                case "DM":
                    poob.set_root(poob.insert(poob.get_Root(), command_and_data[1]));
                    break;
                case "UM":
                    poob.set_root(poob.delete(poob.get_Root(), command_and_data[1]));
                    break;
                case "WM":
                    System.out.println((poob.find(poob.get_Root(), command_and_data[1]) ? "TAK" : "NIE"));
                    break;
                case "LM":
                    System.out.println(poob.Count_prefix(poob.get_Root(), command_and_data[1]));
                    break;
                case "WY":
                    poob.show();
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
        else
            System.out.println("Lel zła opcja.");
    }

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        if(!for_file_only) {
            System.out.println("Witamy w becie programu Sieć Drogowa");
            String word_for_choices;
            boolean show_must_go_on = true;
            AVL_TREE poob = new AVL_TREE();
            Scanner in = new Scanner(System.in);
            do {
                MENU_text(0);
                switch (in.next().charAt(0)) {
                    case '1':
                        MENU_text(1);
                        word_for_choices = in.next();
                        poob.set_root(poob.insert(poob.get_Root(), word_for_choices));
                        break;
                    case '2':
                        MENU_text(2);
                        word_for_choices = in.next();
                        poob.set_root(poob.delete(poob.get_Root(), word_for_choices));
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
                        poob.show();
                        break;
                    case '6':
                        poob.set_root(file_control(poob));
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
            poob.set_root(file_control(poob));
            }
    }
}
