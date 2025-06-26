import java.util.*;

interface LibraryOperations {
    void addBook(Book book);

    void issueBook(int bookId, User user);

    void returnBook(int bookId, User user);

    void showAvailableBooks();
}

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;
    private User previousOwner;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.previousOwner = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    public User getPreviousOwner() {
        return previousOwner;
    }

    public void setPreviousOwner(User user) {
        this.previousOwner = user;
    }
}

class LibraryMember {
    protected int id;
    protected String name;

    public LibraryMember(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}

class User extends LibraryMember {
    private List<Book> issuedBooks;

    public User(int id, String name) {
        super(id, name);
        this.issuedBooks = new ArrayList<>();
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public String getName() {
        return name;
    }

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + id + ", Name: " + name);
    }

    public void displayIssuedBooks() {
        if (issuedBooks.isEmpty()) {
            System.out.println(name + " has no issued books.");
        } else {
            System.out.println("Issued Books for " + name + ":");
            for (Book book : issuedBooks) {
                System.out.println(" - " + book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}

class Library implements LibraryOperations {
    private Map<Integer, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void issueBook(int bookId, User user) {
        Book book = books.get(bookId);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            book.setIssued(true);
            // book.setPreviousOwner(user);
            user.getIssuedBooks().add(book);
            System.out.println("Book issued: " + book.getTitle() + " to " + user.getName());
        }
    }

    public void returnBook(int bookId, User user) {
        Book book = books.get(bookId);
        // for (Book book : user.getIssuedBooks()) {
        // if (book.getId() == bookId) {
        // book.setIssued(false);
        // user.getIssuedBooks().remove(book);
        // System.out.println("Book returned: " + book.getTitle());
        // return;
        // }
        // }
        if (book == null || !user.getIssuedBooks().contains(book)) {
            System.out.println("Book not found or not issued to this user.");
        } else {
            book.setIssued(false);
            book.setPreviousOwner(user);
            user.getIssuedBooks().remove(book);
            System.out.println("Book returned: " + book.getTitle() + " by " + user.getName());
        }
    }

    public void showAvailableBooks() {
        System.out.println("Available Books:");
        boolean found = false;
        for (Book b : books.values()) {
            if (!b.isIssued()) {
                found = true;
                System.out.print("- " + b.getTitle() + " by " + b.getAuthor());
                if (b.getPreviousOwner() != null) {
                    System.out.print(" (Last borrowed by: " + b.getPreviousOwner().getName() + ")");
                }
                System.out.println();
            }
        }
        if (!found) {
            System.out.println("No books available.");
        }
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        System.out.print("Enter your User ID: ");
        int userId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter your Name: ");
        String name = sc.nextLine();
        //Only one user can be created for now
        User user = new User(userId, name);

        //For multiple users 
        // Map<Integer, User> users = new HashMap<>();
        // users.put(userId, new User(userId, name));


        user.displayInfo();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add Book");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Show Available Books");
            System.out.println("5. Show My Issued Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(bookId, title, author));
                    break;
                case 2:
                    System.out.print("Enter Book ID to issue: ");
                    int issueId = sc.nextInt();
                    library.issueBook(issueId, user);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId, user);
                    break;
                case 4:
                    library.showAvailableBooks();
                    break;
                case 5:
                    user.displayIssuedBooks();
                    break;
                case 6:
                    System.out.println("Have a nice day! and visit again!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
