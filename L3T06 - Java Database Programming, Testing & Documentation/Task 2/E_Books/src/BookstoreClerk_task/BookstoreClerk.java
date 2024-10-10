package BookstoreClerk_task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookstoreClerk {

    // Method to connect to the database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore", "root", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Method to enter a new book
    public static void enterBook(Connection conn, int id, String title, String author, int qty) {
        String sql = "INSERT INTO books(id, title, author, qty) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setInt(4, qty);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to update book information
    public static void updateBook(Connection conn, int id, String title, String author, int qty) {
        String sql = "UPDATE books SET title = ?, author = ?, qty = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, qty);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
            System.out.println("Book updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to delete a book
    public static void deleteBook(Connection conn, int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Book deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to search for a book
    public static void searchBook(Connection conn, int id) {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Author: " + rs.getString("author"));
                System.out.println("Quantity: " + rs.getInt("qty"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = connect();
        
        if (conn == null) {
            System.out.println("Failed to make connection!");
            return;
        }

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Enter book");
            System.out.println("2. Update book");
            System.out.println("3. Delete book");
            System.out.println("4. Search books");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 0) break;

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    enterBook(conn, id, title, author, qty);
                    break;
                case 2:
                    System.out.print("Enter ID of the book to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQty = scanner.nextInt();
                    updateBook(conn, updateId, newTitle, newAuthor, newQty);
                    break;
                case 3:
                    System.out.print("Enter ID of the book to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteBook(conn, deleteId);
                    break;
                case 4:
                    System.out.print("Enter ID of the book to search: ");
                    int searchId = scanner.nextInt();
                    searchBook(conn, searchId);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        
        scanner.close();
    }
}