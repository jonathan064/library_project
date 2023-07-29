package library;

import java.util.Objects;
import java.util.Scanner;
import java.sql.*;
import java.time.*;

public class RequestUnavailableItem {

    private String request_id, item_id, name, request_date;

    public void getInput ()
    {
        boolean redo = true;
        Scanner input = new Scanner(System.in);
        //repeats loop if information isn't correct to prevent conflicts with sql table insertion
        while (redo)
        {
            System.out.print("Enter request ID:\n");
            request_id = input.nextLine();
            System.out.print("Enter the ID of the item:\n");
            item_id = input.nextLine();
            System.out.print("Enter the user name:\n");
            name = input.nextLine();
            //get title through query
            System.out.print("Enter the date in the format YYYY-MM-DD \n");
            request_date = input.nextLine();
            System.out.print("Is this information correct? " + request_id + ", " +  item_id + ", " + name + ", " + request_date + " Y/N ");
            String correct = input.nextLine();
            if (Objects.equals(correct, "Y") || Objects.equals(correct, "y"))
            {
                redo = false;
            }
        }
        input.close();

        insertUserIntoTable(request_id, item_id, name, request_date);
    }

    public void insertUserIntoTable(String request_id, String item_id, String name, String request_date)
    {
        String renew = "0";
        String url = "jdbc:mysql://localhost:3306/library_system";
        String user = "root";
        String password = "1234";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet get_item_id = myStatement.executeQuery("select item_id from item_catalog where available_for_checkout= 0");
            //Only gets title if query isn't empty
            while(get_item_id.next())
            {
                item_id = get_item_id.getString(1);
            }

            myStatement.executeUpdate("insert into item_request" + "(request_id, item_id, name, request_date)" + "values('"+request_id+"','"+item_id+"','"+name+"','"+request_date+" 00:00:01')");
            myStatement.executeUpdate("update item_checkout SET renew = 0 WHERE item_id='"+item_id+"'");
            System.out.print("Request Successful.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
