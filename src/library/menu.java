package library;


import java.util.Objects;
import java.util.Scanner;

public class menu
{

    //displays options for users related to functions of library
    public menu()
    {
        AddUser new_user = new AddUser();
        CheckoutItem checkoutItem = new CheckoutItem();
        String option_select;
        Scanner input = new Scanner(System.in);
        System.out.print("Select an option from below:\n");
        System.out.print("1. Create new library card:\n");
        System.out.print("4. Checkout book:\n");
        option_select = input.nextLine();
        //switch statement later for more options
        if (Objects.equals(option_select, "1"))
        {
            new_user.getInput();
        }
        switch(option_select){
        case "4":
            checkoutItem.getInput();
            break;

        default:
            System.out.print("Unknown Selection");
    }
    }

}
