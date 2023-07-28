package library;

import java.util.Objects;
import java.util.Scanner;
import java.sql.*;
import java.time.*;
public class CheckoutItem
{
    private String item_id, user_id, title, checkout_date, due_date;
    private int weeksToAdd = 0;
    ItemCheckoutFacade conditionsCheck;
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
        conditionsCheck = new ItemCheckoutFacade(item_id,user_id, checkout_date);

        //facade calls this if conditions met
        //insertUserIntoTable(item_id, user_id, checkout_date);
    }

    public void insertUserIntoTable(String item_id, String user_id, String checkout_date)
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
            ResultSet get_title = myStatement.executeQuery("select title from item_catalog where item_id='"+ item_id+"'");
            //Only gets title if query isn't empty
            while(get_title.next())
            {
                title = get_title.getString(1);
            }
            //parses date entered and adds 3 weeks, change based on best seller
            LocalDate calculate_due_date = LocalDate.parse(checkout_date);
            calculate_due_date = calculate_due_date.plusWeeks(getWeeksToAdd());
            due_date = calculate_due_date.toString();
            System.out.print(due_date);

            myStatement.executeUpdate("insert into item_checkout" + "(item_id,user_id,title,renew,checkout_date,due_date)" + "values('"+item_id+"','"+user_id+"','"+title+"','"+renew+"','"+checkout_date+" 00:00:01','"+due_date+" 00:00:01')");
            myStatement.executeUpdate("update item_catalog SET available_for_checkout = 0 WHERE item_id='"+item_id+"'");
            System.out.print("Successfully checked out.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String getCheckout_date()
    {
        return checkout_date;
    }

    public void setWeeksToAdd(int weeks)
    {
        weeksToAdd = weeks;
    }
    public int getWeeksToAdd()
    {
        return weeksToAdd;
    }

}
