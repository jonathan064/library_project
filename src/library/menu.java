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
        String option_select;
        Scanner input = new Scanner(System.in);
        System.out.print("Select an option from below:\n");
        System.out.print("1. Create new library card:\n");
        System.out.print("2. Select from Item Catalog:\n");
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
            default:
                System.out.print("Unknown Selection");
        }
    }

}
