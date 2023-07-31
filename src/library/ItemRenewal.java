package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
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
        System.out.print("Enter the id of the book you would like to renew your loan for: \n");
        Scanner input = new Scanner(System.in);
        setItem_id(input.nextLine());
        requestRenewal(getItem_id());
    }

    public void requestRenewal(String item_id)
    {
        String result_id="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement myStatement = conn.createStatement();
            String get_userId = "SELECT item_id FROM item_request WHERE user_id='"+item_id+"'";
            resultSet = myStatement.executeQuery(get_userId);

            while (resultSet.next())
            {
                result_id = resultSet.getString(1);
            }
            //if object has been requested by a user
            if (Objects.equals(item_id, result_id))
            {
                System.out.print("Loan cannot be renewed because another user has requested it.\n");
            }
            //if object hasn't been requested, get new due date and update renew to 1
            else
            {

                String checkout_date="";
                String get_newCheckoutDate = "SELECT due_date FROM item_checkout WHERE item_id='"+item_id+"'";
                resultSet = myStatement.executeQuery(get_newCheckoutDate);
                while (resultSet.next())
                {
                    checkout_date = resultSet.getString("due_date");
                }
                System.out.print(checkout_date);
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
