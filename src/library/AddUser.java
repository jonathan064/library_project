package library;

import java.util.Objects;
import java.util.Scanner;
import java.sql.*;

public class AddUser
{
    private String name, address, phone_number,is_child;

    public void getInput ()
    {
        boolean redo = true;
        Scanner input = new Scanner(System.in);
        //repeats loop if information isn't correct to prevent conflicts with sql table insertion
        while (redo)
        {
            System.out.print("Enter your name:\n");
            name = input.nextLine();
            System.out.print("Enter your address:\n");
            address = input.nextLine();
            System.out.print("Enter your phone number:\n");
            phone_number = input.nextLine();
            System.out.print("Is the user a child? Y/N :\n");
            is_child = input.nextLine();
            System.out.print("Is this information correct? " + name + ", " + address + ", " + phone_number + ", Child:"+ is_child+ " Y/N ");
            String correct = input.nextLine();
            if (Objects.equals(correct, "Y") || Objects.equals(correct, "y"))
            {
                redo = false;
            }
            if (Objects.equals(is_child, "Y") || Objects.equals(correct, "y"))
            {
                is_child = "1";
            }
            else is_child = "0";
        }
        input.close();
        insertUserIntoTable(name,address,phone_number,is_child);
    }
    //information needed to connect to db through local proxy
    public void insertUserIntoTable(String name, String address, String phone_number, String is_child)
    {
        String url = "jdbc:mysql://localhost:3306/library_system";
        String user = "root";
        String password = "1234";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,user,password);
            Statement myStatement = conn.createStatement();
            //specific formatting needed for sql statement
            myStatement.executeUpdate("insert into library_card" + "(name,address,phone_number,is_child)" + "values('"+name+"','"+address+"','"+phone_number+"','"+is_child+"')");
            System.out.print("User added.\n");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


