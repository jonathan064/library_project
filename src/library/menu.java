package library;


import java.util.Objects;
import java.util.Scanner;

public class menu
{

    //displays options for users related to functions of library
    public menu()
    {
        AddUser new_user = new AddUser();
        String option_select;
        Scanner input = new Scanner(System.in);
        System.out.print("Select an option from below:\n");
        System.out.print("1. Create new library card:\n");
        option_select = input.nextLine();
        //switch statement later for more options
        if (Objects.equals(option_select, "1"))
        {
            new_user.getInput();
        }
    }

}
