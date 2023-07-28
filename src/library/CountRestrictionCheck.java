package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class CountRestrictionCheck
{
    private String isChild;
    private int itemCount;
    private boolean validCheckout = true;

    public boolean validCheckout(String user_id)
    {
        String url = "jdbc:mysql://localhost:3306/library_system";
        String user = "root";
        String password = "1234";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            ResultSet get_isChild = myStatement.executeQuery("select is_child from library_card where user_id='"+ user_id+"'");
            //Only gets title if query isn't empty
            while(get_isChild.next())
            {
                isChild = get_isChild.getString(1);
            }
            if (Objects.equals(isChild, "1"))
            {
                ResultSet get_itemCount = myStatement.executeQuery("select COUNT(checkout_id) from item_checkout WHERE user_id='"+ user_id+"'");
                while(get_itemCount.next())
                {
                    setItemCount(Integer.parseInt(get_itemCount.getString(1)));
                }
                if (getItemCount() >= 5)
                {
                    setValidCheckout(false);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (Objects.equals(isChild, "0"))
        {
            setValidCheckout(true);
        }
        return getValidCheckout();
    }

    public void setValidCheckout(boolean statement)
    {
        validCheckout = statement;
    }
    public boolean getValidCheckout()
    {
        return validCheckout;
    }

    public void setItemCount(int num)
    {
        itemCount = num;
    }

    public int getItemCount()
    {
        return itemCount;
    }
}
