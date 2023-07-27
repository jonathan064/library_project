package library;

import java.util.Objects;
import java.util.Scanner;
import java.sql.*;
import java.time.*;
public class CheckoutBook
{
    private String item_id, user_id, title, checkout_date, due_date;

    public void getInput ()
    {
        boolean redo = true;
        Scanner input = new Scanner(System.in);
        //repeats loop if information isn't correct to prevent conflicts with sql table insertion
        while (redo)
        {
            System.out.print("Enter the ID of the item:\n");
            item_id = input.nextLine();
            System.out.print("Enter the user ID:\n");
            user_id = input.nextLine();
            //get title through query
            System.out.print("Enter the date in the format YYYY-MM-DD \n");
            checkout_date = input.nextLine();
            System.out.print("Is this information correct? " + item_id + ", " + user_id + ", " + checkout_date + " Y/N ");
            String correct = input.nextLine();
            if (Objects.equals(correct, "Y") || Objects.equals(correct, "y"))
            {
                redo = false;
            }
        }
        input.close();
        insertUserIntoTable(item_id, user_id, checkout_date);
    }

    public void insertUserIntoTable(String item_id, String user_id, String checkout_date)
    {
        String url = "jdbc:mysql://localhost:3306/library_system";
        String user = "root";
        String password = "1234";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            /*ResultSet get_title = myStatement.executeQuery("select title from item_catalog WHERE item_id="+item_id);
            title = get_title.getString(1);*/
            LocalDate calculate_due_date = LocalDate.parse(checkout_date);
            calculate_due_date = calculate_due_date.plusWeeks(3);
            due_date = calculate_due_date.toString();
            System.out.print(due_date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
