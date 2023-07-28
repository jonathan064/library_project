package library;

import java.sql.*;
import java.util.Scanner;

public class BookCheckIn {

    private String bookId;
    private boolean isCheckedOut;
    private String userId;

    private ResultSet resultSet;
    private final String url = "jdbc:mysql://localhost:3306/library_system";
    private final String user = "root";
    private final String password = "1234";

//    public BookCheckIn(String bookId) {
//        this.bookId = bookId;
//        this.isCheckedOut = false;
//    }

    // Getter for bookId
    public String getBookId() {
        return bookId;
    }


    // Getter for isCheckedOut
    public boolean isCheckedOut() {
        return isCheckedOut;
    }

//    // Method to update the database with the current checkout status and user ID
//    public void updateDatabase(boolean checkedOutStatus, String userId){
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(url, user, password);
//            PreparedStatement myStatement = conn.createStatement();
//            String sql = "UPDATE books SET available_for_checkout = ?, user_id = ?, Where item_id = ?";
//
//
//            myStatement.setBoolean(1, checkedOutStatus);
//            myStatement.setString(2, userId);
//            myStatement.setString(3, bookId);
//            resultSet = myStatement.executeQuery(sql);
//
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    // Method to check out the book
    public void checkOut(String userId) {
        if (!isCheckedOut) {
            isCheckedOut = true;
            this.userId = userId;
            System.out.println("Book with ID " + bookId + " has been checked out by User ID: " + userId);
            //updateDatabase(true, userId);
        } else {
            System.out.println("Book with ID " + bookId + " is already checked out.");
        }
    }


    public void BookCheckIn() {
        Scanner input = new Scanner(System.in);
        String userOption;

        // Get book ID from the user
        System.out.print("Enter the book ID: ");
        String bookId = input.nextLine();

        // Get user ID from the user
        System.out.print("Enter the user ID: ");
        String userId = input.nextLine();

        input.close();

    }


}
