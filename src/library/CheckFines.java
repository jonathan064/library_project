package library;

import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CheckFines {

    String user = "root";
    String password = "1234";
    String url = "jdbc:mysql://localhost:3306/library_system";

    public void OutstandingFines() {

        Scanner input = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Display information for all user IDs");
        System.out.println("2. Display information for a specific user ID");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        input.nextLine();

        if (choice == 1) {
            displayFinesForAllUsers();
        } else if (choice == 2) {
            System.out.print("Enter the user ID: ");
            String userId = input.nextLine();
            displayFinesForUser(userId);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }

    }


        // Calculate the total outstanding fines for each user
        private void displayFinesForAllUsers() {
            Map<String, Double> userFinesMap = new HashMap<>();
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String finesQuery = "SELECT b.user_id, SUM(CASE WHEN b.due_date < CURDATE() THEN "
                        + "LEAST(0.10 * DATEDIFF(CURDATE(), b.due_date), ic.item_value) ELSE 0 END) AS total_fine "
                        + "FROM item_checkout b INNER JOIN item_catalog ic ON b.item_id = ic.item_id "
                        + "GROUP BY b.user_id";
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(finesQuery)) {
                    while (resultSet.next()) {
                        String userId = resultSet.getString("user_id");
                        double totalFine = resultSet.getDouble("total_fine");
                        userFinesMap.put(userId, totalFine);
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            // Display the total outstanding fines for each user
            System.out.println("Total outstanding fines for each user:");
            for (Map.Entry<String, Double> entry : userFinesMap.entrySet()) {
                String userId = entry.getKey();
                double totalFine = entry.getValue();
                System.out.println("User ID: " + userId + ", Total Fine: $" + totalFine);
            }

            // Return to main menu
            Scanner input = new Scanner(System.in);
            String select;
            System.out.print("\nReturn to Main Menu? Y/N\n");
            select = input.nextLine();
            if(select.equals("Y") || select.equals("y")) {
                new menu();
            }
        }

        // Calculate the total outstanding fines for the specified user
        private void displayFinesForUser(String userId) {
            double totalFine = 0.0;
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String finesQuery = "SELECT SUM(LEAST(0.10 * DATEDIFF(CURDATE(), b.due_date), ic.item_value)) AS total_fine "
                        + "FROM item_checkout b INNER JOIN item_catalog ic ON b.item_id = ic.item_id "
                        + "WHERE b.user_id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(finesQuery)) {
                    preparedStatement.setString(1, userId);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            totalFine = resultSet.getDouble("total_fine");
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }

            // Display the total outstanding fines for the specified user
            System.out.println("Total outstanding fines for user ID " + userId + ": $" + totalFine);

            // Return to main menu
            Scanner input = new Scanner(System.in);
            String select;
            System.out.print("\nReturn to Main Menu? Y/N\n");
            select = input.nextLine();
            if(select.equals("Y") || select.equals("y")) {
                new menu();
        }


            input.close();
    }

}
