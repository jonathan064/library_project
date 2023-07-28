package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class BestSellerCheck
{
    private String bestSeller;

    private int weeksToAdd;

    public int isBestSeller(String item_id)
    {
        String url = "jdbc:mysql://localhost:3306/library_system";
        String user = "root";
        String password = "1234";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet get_bestSeller = myStatement.executeQuery("select best_seller from item_catalog where item_id='"+ item_id+"'");
            //Only gets title if query isn't empty
            while(get_bestSeller.next())
            {
                bestSeller = get_bestSeller.getString(1);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //best sellers can only be checked out for 2 weeks
        if (Objects.equals(bestSeller, "0"))
        {
            setWeeksToAdd(3);
        }
        else
        {
            setWeeksToAdd(2);
        }
        return getWeeksToAdd();
    }
    public int getWeeksToAdd()
    {
        return weeksToAdd;
    }

    public void setWeeksToAdd(int number)
    {
        weeksToAdd = number;
    }
}
