package library;

import java.util.Objects;
import java.util.Scanner;
import java.sql.*;
import java.time.*;

public class RequestUnavailableItem {

    private String  item_id, name, request_date, user_id;
    private ResultSet resultSet;

    public void getInput ()
    {
        boolean redo = true;
        Scanner input = new Scanner(System.in);
        //repeats loop if information isn't correct to prevent conflicts with sql table insertion
        while (redo)
        {
            System.out.print("Enter the ID of the item:\n");
            item_id = input.nextLine();
            System.out.print("Enter the user id:\n");
            user_id = input.nextLine();
            //get title through query
            System.out.print("Enter the date in the format YYYY-MM-DD \n");
            request_date = input.nextLine();
            System.out.print("Is this information correct? " +  item_id + ", " + user_id + ", " + request_date + " Y/N ");
            String correct = input.nextLine();
            if (Objects.equals(correct, "Y") || Objects.equals(correct, "y"))
            {
                redo = false;
            }
        }
        input.close();

        insertUserIntoTable(item_id, name, request_date, user_id);
    }

    public void insertUserIntoTable(String item_id, String name, String request_date, String user_id)
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
            String sql = "SELECT name FROM library_card WHERE user_id= '"+user_id+"'";

            resultSet = myStatement.executeQuery(sql);
            while(resultSet.next()){
                name = resultSet.getString("name");
            }



            myStatement.executeUpdate("insert into item_request" + "(item_id, name, request_date, user_id)" + "values('"+item_id+"','"+name+"','"+request_date+" 00:00:01','"+user_id+"')");
            //myStatement.executeUpdate("update item_checkout SET renew = 0 WHERE item_id='"+item_id+"'");
            System.out.print("Request Successful.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
