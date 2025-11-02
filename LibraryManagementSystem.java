/*
 *  Library Management System
 *  Concepts showcased: Inheritance, Interface, Collections, Exception-Handling,
 *                      Serialization, Scanner-menu, diamond-operator, try-with-resources
 */
import java.io.*;
import java.util.*;

/* ---------- 1.  INTERFACE ---------- */
interface ILibrary {
    void addBook(Book b);
    void addMember(Member m);
    void issueBook(String bookId, String memberId) throws Exception;
    void returnBook(String bookId, String memberId) throws Exception;
    void listBooks();
    void listMembers();
}

/* ---------- 2.  PARENT CLASS ---------- */
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    protected final String id;
    protected final String name;
    Person(String id, String name){ this.id=id; this.name=name; }
    public String getId(){return id;}
    public String getName(){return name;}
}

/* ---------- 3.  MEMBER ---------- */
class Member extends Person {
    private static final long serialVersionUID = 1L;
    Member(String id, String name){ super(id,name); }
    @Override
    public String toString(){ return id+" - "+name; }
}

/* ---------- 4.  BOOK ---------- */
class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String bookId, title;
    private boolean issued;
    Book(String bookId, String title){
        this.bookId=bookId; this.title=title;
    }
    public String getBookId(){return bookId;}
    public String getTitle(){return title;}
    public boolean isIssued(){return issued;}
    public void setIssued(boolean issued){this.issued=issued;}
    @Override
    public String toString(){
        return bookId+" - "+title+" ("+(issued?"Issued":"Available")+")";
    }
}

/* ---------- 5.  CUSTOM EXCEPTION ---------- */
class BookNotAvailableException extends Exception {
    BookNotAvailableException(String msg){ super(msg); }
}

/* ---------- 6.  LIBRARY CORE ---------- */
class Library implements ILibrary {
    private final Map<String,Book>   books   = new HashMap<>();
    private final Map<String,Member> members = new HashMap<>();
    private final Map<String,String> issued  = new HashMap<>(); // bookId -> memberId
    private static final String FILE = "library.ser";

    /* ---- API ---- */
    @Override
    public void addBook(Book b){
        books.put(b.getBookId(),b);
        System.out.println("✅ Book added.");
    }
    @Override
    public void addMember(Member m){
        members.put(m.getId(),m);
        System.out.println("✅ Member added.");
    }
    @Override
    public void issueBook(String bookId, String memberId) throws Exception {
        Book b=books.get(bookId);
        if(b==null)            throw new Exception("❌ Book not found!");
        if(members.get(memberId)==null) throw new Exception("❌ Member not found!");
        if(b.isIssued())       throw new BookNotAvailableException("❌ Book already issued!");
        b.setIssued(true);
        issued.put(bookId,memberId);
        System.out.println("📗 Book '"+b.getTitle()+"' issued to "+members.get(memberId).getName());
    }
    @Override
    public void returnBook(String bookId, String memberId) throws Exception {
        String mid=issued.get(bookId);
        if(mid==null)                   throw new Exception("❌ Book was not issued!");
        if(!mid.equals(memberId))       throw new Exception("❌ Book not issued to this member!");
        books.get(bookId).setIssued(false);
        issued.remove(bookId);
        System.out.println("📘 Book returned by "+members.get(memberId).getName());
    }
    @Override
    public void listBooks(){
        System.out.println("\n📚 Book List:");
        if(books.isEmpty()) System.out.println("  (empty)");
        books.values().forEach(System.out::println);
    }
    @Override
    public void listMembers(){
        System.out.println("\n👥 Member List:");
        if(members.isEmpty()) System.out.println("  (empty)");
        members.values().forEach(System.out::println);
    }

    /* ---- Persistence + DEMO SEED ---- */
    @SuppressWarnings("unchecked")
    void load(){
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILE))){
            books.putAll(   (Map<String,Book>)   ois.readObject() );
            members.putAll( (Map<String,Member>) ois.readObject() );
            issued.putAll(  (Map<String,String>) ois.readObject() );
            System.out.println("📂 Data restored.");
            insertDemoData();
        }catch(Exception e){
            System.out.println("⚠ No previous data – creating fresh demo set...");
        }
    }
    void save(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILE))){
            oos.writeObject(books);
            oos.writeObject(members);
            oos.writeObject(issued);
            System.out.println("💾 Data saved.");
        }catch(IOException e){
            System.out.println("Save error: "+e.getMessage());
        }
    }

    /* ---------- DEMO DATA ---------- */
    private void insertDemoData(){
        // Books
        addBook(new Book("B100","The Java Programming Language"));
        addBook(new Book("B101","Effective Java"));
        addBook(new Book("B102","Clean Code"));
        addBook(new Book("B103","Introduction to Algorithms"));
        // Members
        addMember(new Member("M10","Alice"));
        addMember(new Member("M11","Bob"));
        addMember(new Member("M12","Charlie"));
        // Issue one book manually (bypass normal checks)
        Book demo=books.get("B100");
        demo.setIssued(true);
        issued.put("B100","M10");
        System.out.println("(Demo) 📗 Book 'B100' pre-issued to Alice");
    }
}

/* ---------- 7.  CONSOLE DRIVER ---------- */
public class LibraryManagementSystem{
    private static final Scanner in=new Scanner(System.in);
    private static final Library lib=new Library();
    public static void main(String[] args){
        lib.load();
        menu();
    }
    private static void menu(){
        while(true){
            System.out.println("\n========= 📘 LIBRARY MANAGEMENT SYSTEM 📘 =========");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Display Members");
            System.out.println("7. Save & Exit");
            System.out.print("Choice: ");
            try{
                switch(Integer.parseInt(in.nextLine().trim())){
                    case 1->addBook();
                    case 2->addMember();
                    case 3->issueBook();
                    case 4->returnBook();
                    case 5->lib.listBooks();
                    case 6->lib.listMembers();
                    case 7->{ lib.save(); System.out.println("👋 Goodbye!"); return; }
                    default->System.out.println("❌ Invalid choice!");
                }
            }catch(NumberFormatException e){
                System.out.println("❌ Enter 1-7.");
            }catch(Exception ex){
                System.out.println("⚠ "+ex.getMessage());
            }
        }
    }
    /* ---- helpers ---- */
    private static void addBook(){
        System.out.print("Book ID  : "); String id=in.nextLine();
        System.out.print("Title    : "); String t=in.nextLine();
        lib.addBook(new Book(id,t));
    }
    private static void addMember(){
        System.out.print("Member ID: "); String id=in.nextLine();
        System.out.print("Name     : "); String n=in.nextLine();
        lib.addMember(new Member(id,n));
    }
    private static void issueBook() throws Exception{
        System.out.print("Book ID : "); String b=in.nextLine();
        System.out.print("MemberID: "); String m=in.nextLine();
        lib.issueBook(b,m);
    }
    private static void returnBook() throws Exception{
        System.out.print("Book ID : "); String b=in.nextLine();
        System.out.print("MemberID: "); String m=in.nextLine();
        lib.returnBook(b,m);
    }
}