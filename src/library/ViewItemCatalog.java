package library;

import java.awt.*;
import java.util.Scanner;
import java.sql.*;

public class ViewItemCatalog {
    private ResultSet resultSet;
    private final String url = "jdbc:mysql://localhost:3306/library_system";
    private final String user = "root";
    private final String password = "1234";

    public void viewCatalog() {
        Scanner input = new Scanner(System.in);
        String userOption;

        System.out.print("\t\tCatalog Menu\n");
        System.out.print("\t1. View items available for checkout: \n");
        System.out.print("\t2. View best seller list: \n");
        System.out.print("\t3. View entire catalog: \n");
        userOption = input.nextLine();

        switch(userOption){
            case "1":
                System.out.print("\n\tAvailable For Checkout:\n");
                getAvailable();
                break;
            case "2":
                System.out.print("Here are the bestsellers:\n");
                getBestsellers();
                break;
            case "3":
                System.out.print("Here is the entire catalog:\n");
                getCatalog();
                break;
            default:
                System.out.print("unknown entry\n");
        }
        String choice;
        System.out.print("\nReturn to Main Menu? Y/N\n");
        choice = input.nextLine();
        if(choice.equals("Y") || choice.equals("y")){
            new menu();
        }



    }

    public void getAvailable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String sql = "SELECT item_id, title FROM item_catalog WHERE available_for_checkout >= 1";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String title = resultSet.getString("title");;
                System.out.print("\nID:\t" + id + "\tTitle:\t" + title + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getBestsellers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String sql = "SELECT item_id, title FROM item_catalog WHERE best_seller = 1";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String title = resultSet.getString("title");;
                System.out.print("\nID:\t" + id + "\tTitle:\t" + title + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCatalog(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String sql = "SELECT * FROM item_catalog";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String title = resultSet.getString("title");;
                System.out.print("\nID:\t" + id + "\tTitle:\t" + title + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

