package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
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
        System.out.print("Enter the id of the user: \n");
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
            String sql = "SELECT * FROM item_checkout WHERE user_id='"+user_id+"'";
            resultSet = myStatement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("item_id");
                String title = resultSet.getString("title");
                String checkout_date = resultSet.getString("checkout_date");
                String due_date = resultSet.getString("due_date");
                String renewable = resultSet.getString("renew");
                if (Objects.equals(renewable, "0"))
                {
                    renewable = "No";
                }
                else
                {
                    renewable = "Yes";
                }
                System.out.print( "\nBook ID: " + id + "\t\tTitle: " + title  + "\nChecked out:  " + checkout_date + "\t\tDue:  " + due_date +  "\n" + "Renewed before: " + renewable + "\n");
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
                String renewable = resultSet.getString("renew");
                if (Objects.equals(renewable, "0"))
                {
                    renewable = "No";
                }
                else
                {
                    renewable = "Yes";
                }
                System.out.print( "\nBook ID: " + id + "\t\tTitle: " + title  + "\nChecked out:  " + checkout_date + "\t\tDue:  " + due_date +  "\n" + "Renewed before: " + renewable + "\n");

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
