package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class ItemRenewal extends BestSellerCheck
{
    ViewCheckedOut display;
    private ResultSet resultSet;
    private final String url = "jdbc:mysql://localhost:3306/library_system";
    private final String user = "root";
    private final String password = "1234";
    private String item_id;

    public ItemRenewal()
    {
        display = new ViewCheckedOut();
        display.displayAllCheckedOut();
        System.out.print("\n\nEnter the id of the book you would like to renew your loan for: \n");
        Scanner input = new Scanner(System.in);
        setItem_id(input.nextLine());
        requestRenewal(getItem_id());
    }

    public void requestRenewal(String item_id)
    {
        String result_id="";
        String renew = "0";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String get_requested = "SELECT item_id FROM item_request WHERE item_id='"+item_id+"'";
            resultSet = myStatement.executeQuery(get_requested);

            while (resultSet.next())
            {
                result_id = resultSet.getString(1);
            }
            String get_renewed = "SELECT renew FROM item_checkout WHERE item_id='"+item_id+"'";
            resultSet = myStatement.executeQuery(get_renewed);
            while (resultSet.next())
            {
                renew = resultSet.getString(1);
            }
            //if object has been requested by a user or already renewed
            if (Objects.equals(item_id, result_id) || Objects.equals(renew, "1") )
            {
                System.out.print("Loan cannot be renewed because another user has requested it or has already been renewed once.\n");
            }
            //if object hasn't been requested, get new due date and update renew to 1
            else
            {
                String due_date="";
                String checkout_date="";
                String get_newCheckoutDate = "SELECT due_date FROM item_checkout WHERE item_id='"+item_id+"'";
                resultSet = myStatement.executeQuery(get_newCheckoutDate);
                while (resultSet.next())
                {
                    checkout_date = resultSet.getString("due_date");
                    checkout_date = checkout_date.substring(0,10);
                }
                //formats date to work with parsing
                LocalDate calculate_due_date = LocalDate.parse(checkout_date);
                isBestSeller(item_id);
                calculate_due_date = calculate_due_date.plusWeeks(getWeeksToAdd());
                due_date = calculate_due_date.toString();
                myStatement.executeUpdate("update item_checkout SET renew = 1, checkout_date = '" +checkout_date+" 00:00:01', due_date = '" +due_date+ " 00:00:01' WHERE item_id='"+item_id+"'");
                System.out.print("Successfully renewed loan, your new due date is :"+ due_date+"\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setItem_id(String id)
    {
        item_id = id;
    }

    public String getItem_id()
    {
        return item_id;
    }

}
