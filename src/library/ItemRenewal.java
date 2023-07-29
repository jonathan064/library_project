package library;

import java.sql.ResultSet;
import java.util.Scanner;

public class ItemRenewal
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
        System.out.print(item_id);
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
