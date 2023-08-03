package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ViewCheckedOut
{
    private ResultSet resultSet;
    private final String url = "jdbc:mysql://localhost:3306/library_system";
    private final String user = "root";
    private final String password = "1234";

    private String user_id;



    public void displayUserCheckedOut()
    {
        System.out.print("Enter the id of the user to view items currently checked out\n");
        Scanner input = new Scanner(System.in);
        setUser_id(input.nextLine());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String get_username = "SELECT name FROM library_card WHERE user_id='"+user_id+"'";
            resultSet = myStatement.executeQuery(get_username);
            while (resultSet.next())
            {
                System.out.print("\nItems currently loaned to "+ resultSet.getString(1));
                System.out.print("\n=====================================================================================");
            }
            String sql = "SELECT item_id, title, checkout_date, due_date FROM item_checkout WHERE user_id='"+user_id+"'";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String title = resultSet.getString("title");
                String checkout_date = resultSet.getString("checkout_date");
                String due_date = resultSet.getString("due_date");
                System.out.print("\nBook ID: " + id + "\tTitle: " + title + "\tChecked out:" + checkout_date + "\t\tDue:" + due_date + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayAllCheckedOut()
    {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String sql = "SELECT * FROM item_checkout";
            resultSet = myStatement.executeQuery(sql);
            System.out.print("Items currently loaned out\n" +
                    "===========================================================================");
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String title = resultSet.getString("title");
                String checkout_date = resultSet.getString("checkout_date");
                String due_date = resultSet.getString("due_date");
                System.out.print("\nBook ID: " + id + "\tTitle: " + title + "\tChecked out:" + checkout_date + "\t\tDue:" + due_date + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUser_id(String id)
    {
        user_id = id;
    }

    public String getUser_id()
    {
        return user_id;
    }
}
