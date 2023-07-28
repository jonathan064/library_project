package library;


import java.util.Objects;
import java.util.Scanner;

public class menu
{

    //displays options for users related to functions of library
    public menu()
    {
        AddUser new_user = new AddUser();
        ViewItemCatalog item_catalog = new ViewItemCatalog();
        BookCheckIn item_checkIn = new BookCheckIn();
        String option_select;
        Scanner input = new Scanner(System.in);
        System.out.print("Select an option from below:\n");
        System.out.print("1. Create new library card:\n");
        System.out.print("2. Select from Item Catalog:\n");
        System.out.print("3. Check In Book:\n");
        option_select = input.nextLine();
        //switch statement later for more options
        if (Objects.equals(option_select, "1"))
        {
            new_user.getInput();
        }
        switch(option_select){
            case "1":
                new_user.getInput();
                break;
            case "2":
                item_catalog.viewCatalog();
                break;
            case "3":
                item_checkIn.BookCheckIn();
                break;

            default:
                System.out.print("Unknown Selection");
        }
    }

}
