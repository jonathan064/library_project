package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class AvailabilityCheck
{
    private String available;
    private String item;

    public boolean isAvailable(String item_id)
    {
        item = item_id;
        String url = "jdbc:mysql://localhost:3306/library_system";
        String user = "root";
        String password = "1234";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet get_availability = myStatement.executeQuery("select available_for_checkout from item_catalog where item_id='"+ item_id+"'");
            //Only gets title if query isn't empty
            while(get_availability.next())
            {
                available = get_availability.getString(1);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (Objects.equals(available, "0"))
        {
            return false;
        }
        else return true;
    }
}
