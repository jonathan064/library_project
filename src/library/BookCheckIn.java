package library;

import java.sql.*;
import java.util.Scanner;

public class BookCheckIn {


    public void BookReturn() {
        Scanner input = new Scanner(System.in);
        List<String> bookIdsToCheckIn = new ArrayList<>();
        //String userOption;

        // Get book ID from the user
        System.out.print("Enter the book ID: ");
        String bookId = input.nextLine();

        // Get user ID from the user
        System.out.print("Enter the user ID: ");
        String userId = input.nextLine();

        input.close();

        String user = "root";
        String password = "1234";
        String url = "jdbc:mysql://localhost:3306/library_system";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Update the book status to checked in in the database
            String updateQuery = "UPDATE item_catalog SET available_for_checkout = true WHERE item_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, bookId);
                //preparedStatement.setString(2, userId);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Book check-in successful.");

                    String choice;
                    System.out.print("\nReturn to Main Menu? Y/N\n");
                    choice = input.nextLine();
                    if(choice.equals("Y") || choice.equals("y")){
                        new menu();}


                } else {
                    System.out.println("Book check-in failed. Please check the book and user IDs.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }


    }


}
