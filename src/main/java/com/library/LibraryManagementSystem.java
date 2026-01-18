package com.library;

import com.library.model.Book;
import com.library.model.Member;
import com.library.service.Library;
import com.library.ui.GUIComponents;

import java.util.Scanner;

public class LibraryManagementSystem {
    private static final Scanner in = new Scanner(System.in);
    private static final Library lib = new Library();

    public static void main(String[] args) {

        new GUIComponents();

        // lib.load();
        // menu();
    }

    private static void menu() {
        while (true) {
            System.out.println("\n========= LIBRARY MANAGEMENT SYSTEM =========");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Display Members");
            System.out.println("7. Save & Exit");
            System.out.print("Choice: ");
            try {
                switch (Integer.parseInt(in.nextLine().trim())) {
                    case 1 -> addBook();
                    case 2 -> addMember();
                    case 3 -> issueBook();
                    case 4 -> returnBook();
                    case 5 -> lib.listBooks();
                    case 6 -> lib.listMembers();
                    case 7 -> {
                        lib.save();
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter 1-7.");
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    /* ---- helpers ---- */
    private static void addBook() {
        System.out.print("Book ID  : ");
        String id = in.nextLine();
        System.out.print("Title    : ");
        String t = in.nextLine();
        lib.addBook(new Book(id, t));
    }

    private static void addMember() {
        System.out.print("Member ID: ");
        String id = in.nextLine();
        System.out.print("Name     : ");
        String n = in.nextLine();
        lib.addMember(new Member(id, n));
    }

    private static void issueBook() throws Exception {
        System.out.print("Book ID : ");
        String b = in.nextLine();
        System.out.print("MemberID: ");
        String m = in.nextLine();
        lib.issueBook(b, m);
    }

    private static void returnBook() throws Exception {
        System.out.print("Book ID : ");
        String b = in.nextLine();
        System.out.print("MemberID: ");
        String m = in.nextLine();
        lib.returnBook(b, m);
    }
}
