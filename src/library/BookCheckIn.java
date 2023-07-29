package library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookCheckIn {

    String user = "root";
    String password = "1234";
    String url = "jdbc:mysql://localhost:3306/library_system";

    public void BookReturn() {
        Scanner input = new Scanner(System.in);
        List<String> bookIdsToCheckIn = new ArrayList<>();

        System.out.print("Enter the number of books you want to check in: ");
        int numberOfBooks = input.nextInt();
        input.nextLine(); // Consume the newline character left by nextInt()

        for (int i = 0; i < numberOfBooks; i++) {
            // Get book ID from the user
            System.out.print("Enter the book ID of book " + (i + 1) + ": ");
            String bookId = input.nextLine();
            bookIdsToCheckIn.add(bookId);
        }

        // Get user ID from the user
        System.out.print("Enter the user ID: ");
        String userId = input.nextLine();



        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            int totalBooksCheckedIn = 0;
            double totalOverdueFine = 0.0;

            for (String bookId : bookIdsToCheckIn) {
                // Get check-out date of the book
                String pastDueDateQuery = "SELECT due_date FROM item_checkout WHERE item_id = ?";
                try (PreparedStatement checkoutDateStatement = connection.prepareStatement(pastDueDateQuery)) {
                    checkoutDateStatement.setString(1, bookId);
                    //checkoutDateStatement.setString(2, userId);
                    try (ResultSet resultSet = checkoutDateStatement.executeQuery()) {
                        if (resultSet.next()) {
                            Date pastDueDate = resultSet.getDate("due_date");

                            String itemValueQuery = "SELECT item_value FROM item_catalog WHERE item_id = ?";
                            double bookValue = 0;
                            try (PreparedStatement itemValueStatement = connection.prepareStatement(itemValueQuery)) {
                                itemValueStatement.setString(1, bookId);
                                try (ResultSet nextSet = itemValueStatement.executeQuery()) {
                                    if (nextSet.next()) {
                                        bookValue = nextSet.getDouble("item_value");
                                    }
                                }
                            }


                            //double bookValue = resultSet.getDouble("item_value");

                            // Calculate number of days the book was checked out past due date
                            Date checkInDate = new Date(System.currentTimeMillis());
                            long daysPastDue = (checkInDate.getTime() - pastDueDate.getTime()) / (24 * 60 * 60 * 1000);

                            // Calculate overdue fine for the book (maximum of fine or book value)
                            double fine = Math.min(0.10 * daysPastDue, bookValue);

                            // Remove the returned book from the database
                            String deleteQuery = "DELETE FROM item_checkout WHERE item_id = ? AND user_id = ?";
                            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                                deleteStatement.setString(1, bookId);
                                deleteStatement.setString(2, userId);
                                deleteStatement.executeUpdate();
                            }

                            // Update the book status to checked-in in the database
                            String updateQuery = "UPDATE item_catalog SET available_for_checkout = true WHERE item_id = ?";
                            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                                preparedStatement.setString(1, bookId);
                                //preparedStatement.setString(2, userId);

                                int rowsAffected = preparedStatement.executeUpdate();

                                if (rowsAffected > 0) {
                                    totalBooksCheckedIn++;
                                    totalOverdueFine += fine;
                                }
                            }
                        }
                    }
                }
            }

            if (totalBooksCheckedIn > 0) {
                System.out.println("Successfully checked in " + totalBooksCheckedIn + " book(s).");
                System.out.println("Total overdue fine: $" + totalOverdueFine);

                String select;
                System.out.print("\nReturn to Main Menu? Y/N\n");
                select = input.nextLine();
                if(select.equals("Y") || select.equals("y")){
                    new menu();
                }

            } else {
                System.out.println("No books were checked in. Please check the book and user IDs.");

                String select;
                System.out.print("\nReturn to Main Menu? Y/N\n");
                select = input.nextLine();
                if(select.equals("Y") || select.equals("y")) {
                    new menu();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

        input.close();
    }
}

