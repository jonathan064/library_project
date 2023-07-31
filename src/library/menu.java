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
        ViewItemCatalog catalog = new ViewItemCatalog();
        BookCheckIn checkIn = new BookCheckIn();
        request_unavailable_item
        RequestUnavailableItem unavailableItem = new RequestUnavailableItem();
        CheckFines viewFines = new CheckFines();
        String option_select;
        Scanner input = new Scanner(System.in);
        System.out.print("Select an option from below:\n");
        System.out.print("1. Create new library card:\n");
        System.out.print("2. View Catalog:\n");
        System.out.print("3. Check-in book:\n");
        System.out.print("4. Checkout book:\n");
        System.out.print("5. View Outstanding Fines:\n");
        System.out.print("6. Request unavailable item:\n");

        option_select = input.nextLine();
        //displays different options
        switch(option_select)
        {
            case "1":
                new_user.getInput();
                break;
            case "2":
                catalog.viewCatalog();
                break;
            case "3":
                checkIn.BookReturn();
                break;
            case "4":
                checkoutItem.getInput();
                break;
            case "5":
                viewFines.OutstandingFines();
                break;
            case "6":
                request_unavailable_item
                unavailableItem.getInput();
                break;

            default:
                System.out.print("Unknown Selection");
        }
    }

}
