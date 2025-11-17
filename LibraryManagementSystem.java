import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a Book entity
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; // Initially available
    }

    // Getters and setters
    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + isAvailable;
    }
}

// Class to represent a User (Member) entity
class User {
    private String name;
    private String userId;
    private List<Book> borrowedBooks;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public String getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // Method to borrow a book
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            borrowedBooks.add(book);
            System.out.println(name + " has successfully borrowed the book: " + book.toString() + "");
        } else {
            System.out.println("Sorry, the book is currently unavailable.");
        }
    }

    // Method to return a book
    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            System.out.println(name + " has successfully returned the book: " + book.toString() + "");
        } else {
            System.out.println("Error: " + name + " did not borrow this book.");
        }
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Books Borrowed: " + borrowedBooks.size();
    }
}

// Class to manage the overall library operations
class Library {
    private List<Book> books;
    private List<User> users;
    private Scanner scanner;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to add a new book
    public void addBook() {
        System.out.println("Enter Book Title:");
        String title = scanner.nextLine();
        System.out.println("Enter Author Name:");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();
        books.add(new Book(title, author, isbn));
        System.out.println("Book added successfully!");
    }

    // Method to register a new user
    public void registerUser() {
        System.out.println("Enter User Name:");
        String name = scanner.nextLine();
        System.out.println("Enter User ID:");
        String userId = scanner.nextLine();
        users.add(new User(name, userId));
        System.out.println("User registered successfully!");
    }

    // Method to handle book borrowing
    public void borrowBook() {
        System.out.println("Enter User ID:");
        String userId = scanner.nextLine();
        User user = findUserById(userId);

        if (user != null) {
            System.out.println("Enter Book ISBN to borrow:");
            String isbn = scanner.nextLine();
            Book book = findBookByIsbn(isbn);

            if (book != null) {
                user.borrowBook(book);
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    // Method to handle book returning
    public void returnBook() {
        System.out.println("Enter User ID:");
        String userId = scanner.nextLine();
        User user = findUserById(userId);

        if (user != null) {
            System.out.println("Enter Book ISBN to return:");
            String isbn = scanner.nextLine();
            Book book = findBookByIsbn(isbn);

            if (book != null) {
                user.returnBook(book);
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("User not found.");
        }
    }

    // Helper methods to find books and users
    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    // Method to display all books
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    
     // Method to display all users
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }


    // Main menu loop
    public void menu() {
        int choice;
        do {
            System.out.println("\n*** Library Management System Menu ***");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Books");
            System.out.println("6. Display Users");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    displayBooks();
                    break;
                case 6:
                    displayUsers();
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }
}

// Main class to run the application
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.menu();
    }
}