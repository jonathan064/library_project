package library;

import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;
import java.util.Scanner;

public class NonCheckoutItem {

    private ResultSet resultSet;

    String user = "root";
    String password = "1234";
    String url = "jdbc:mysql://localhost:3306/library_system";

    public void viewRefCatalog(){

        try {
            System.out.print("These items cannot be checked out.");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String sql = "SELECT * FROM item_reference";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String title = resultSet.getString("item_title");;
                String itemType = resultSet.getString("item_type");
                System.out.print("\nID:\t" + id + "\tTitle:\t" + title + "\tType:\t" + itemType+ "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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