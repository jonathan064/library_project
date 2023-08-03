package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ViewUsers {

    private ResultSet resultSet;
    private final String url = "jdbc:mysql://localhost:3306/library_system";
    private final String user = "root";
    private final String password = "1234";
    Scanner input = new Scanner(System.in);



    public void viewUserMenu() {
        String userOption;

        System.out.print("1. View individual user info: \n");
        System.out.print("2. View all user info: \n");
        userOption = input.nextLine();

        switch(userOption){
            case "1":
                getOneUser();
                break;
            case "2":
                getAllUsers();
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

    public void getOneUser(){
        String userId;
        System.out.print("Enter user id to get info:\n");
        userId = input.nextLine();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String sql = "SELECT user_id, address, is_child, name, phone_number FROM library_card WHERE user_id= '"+userId+"'";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String address = resultSet.getString("address");
                int child = resultSet.getInt("is_child");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone_number");
                System.out.print("\nID: " + id + ",\tName: " + name + ",\tAddress: " + address + ",\tPhone: " + phone + ",\tChild?: " + child + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAllUsers(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String sql = "SELECT * FROM library_card";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String address = resultSet.getString("address");
                int child = resultSet.getInt("is_child");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone_number");
                System.out.print("\nID: " + id + ",\tName: " + name + ",\tAddress: " + address + ",\tPhone: " + phone + ",\tChild?: " + child + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
