package com.library.service;

import com.library.exception.BookNotAvailableException;
import com.library.model.Book;
import com.library.model.Member;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Library implements ILibrary {
    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Member> members = new HashMap<>();
    private final Map<String, String> issued = new HashMap<>(); // bookId -> memberId
    private static final String FILE = "library.ser";

    /* ---- API ---- */
    @Override
    public void addBook(Book b) {
        books.put(b.getBookId(), b);
        System.out.println("✅ Book added.");
    }

    @Override
    public void addMember(Member m) {
        members.put(m.getId(), m);
        System.out.println("✅ Member added.");
    }

    @Override
    public void issueBook(String bookId, String memberId) throws Exception {
        Book b = findBookIgnoreCase(bookId);
        if (b == null)
            throw new Exception("❌ Book not found!");

        Member m = findMemberIgnoreCase(memberId);
        if (m == null)
            throw new Exception("❌ Member not found!");

        if (b.isIssued())
            throw new BookNotAvailableException("❌ Book already issued!");

        b.setIssued(true);
        issued.put(b.getBookId(), m.getId());
        System.out.println("📗 Book '" + b.getTitle() + "' issued to " + m.getName() + " " + m.getId());
    }

    @Override
    public void returnBook(String bookId, String memberId) throws Exception {
        Book b = findBookIgnoreCase(bookId);
        Member m = findMemberIgnoreCase(memberId);

        if (b == null)
            throw new Exception("❌ Book not found!");
        if (m == null)
            throw new Exception("❌ Member not found!");

        String mid = issued.get(b.getBookId());
        if (mid == null)
            throw new Exception("❌ Book was not issued!");
        if (!mid.equalsIgnoreCase(m.getId()))
            throw new Exception("❌ Book not issued to this member!");

        b.setIssued(false);
        issued.remove(b.getBookId());
        System.out.println("📘 Book returned by " + m.getName());
    }

    @Override
    public void listBooks() {
        System.out.println("\n📚 Book List:");
        if (books.isEmpty())
            System.out.println("  (empty)");
        books.values().forEach(System.out::println);
    }

    @Override
    public void listMembers() {
        System.out.println("\n👥 Member List:");
        if (members.isEmpty())
            System.out.println("  (empty)");
        members.values().forEach(System.out::println);
    }

    /* ---- Persistence + DEMO SEED ---- */
    @SuppressWarnings("unchecked")
    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            books.putAll((Map<String, Book>) ois.readObject());
            members.putAll((Map<String, Member>) ois.readObject());
            issued.putAll((Map<String, String>) ois.readObject());
            System.out.println("📂 Data restored.");
            insertDemoData();
        } catch (Exception e) {
            System.out.println("⚠ No previous data – creating fresh demo set...");
            insertDemoData();
        }
    }

    public void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(books);
            oos.writeObject(members);
            oos.writeObject(issued);
            System.out.println("💾 Data saved.");
        } catch (IOException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }

    private Book findBookIgnoreCase(String bookId) {
        for (Book b : books.values()) {
            if (b.getBookId().equalsIgnoreCase(bookId))
                return b;
        }
        return null;
    }

    private Member findMemberIgnoreCase(String memberId) {
        for (Member m : members.values()) {
            if (m.getId().equalsIgnoreCase(memberId))
                return m;
        }
        return null;
    }

    /* ---------- DEMO DATA ---------- */
    private void insertDemoData() {
        // Books
        addBook(new Book("A8013", "BUSINESS ECONOMICS AND FINANCIAL ANALYSIS"));
        addBook(new Book("A8509", "DISCRETE MATHEMATICAL STRUCTURES"));
        addBook(new Book("A8510", "OPERATING SYSTEMS"));
        addBook(new Book("A8511", "ADVANCED DATA STRUCTURES"));
        addBook(new Book("A8601", "OBJECT ORIENTED PROGRAMMING"));
        // Members
        addMember(new Member("M1", "Sridinesh"));
        addMember(new Member("M2", "Charan"));
        addMember(new Member("M3", "Jyoshna"));
        addMember(new Member("M4", "Nithin"));
    }
}
