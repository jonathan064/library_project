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
        NonCheckoutItem catalogRef = new NonCheckoutItem();
        BookCheckIn checkIn = new BookCheckIn();
        RequestUnavailableItem unavailableItem = new RequestUnavailableItem();
        CheckFines viewFines = new CheckFines();
        ViewUserInfo viewInfo = new ViewUserInfo();
        String option_select;
        Scanner input = new Scanner(System.in);
        System.out.print("Select an option from below:\n");
        System.out.print("1. Create new library card:\n");
        System.out.print("2. View Item Catalog:\n");
        System.out.print("3. View Reference and Magazine Catalog:\n");
        System.out.print("4. Check-in book:\n");
        System.out.print("5. Checkout book:\n");
        System.out.print("6. View Outstanding Fines:\n");
        System.out.print("7. Request unavailable item:\n");
        System.out.print("8. Request extension on loan:\n");
        System.out.print("9. View Items Checked Out By User:\n");
        System.out.print("10. View User Info:\n");

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
                catalogRef.viewRefCatalog();
                break;
            case "4":
                checkIn.BookReturn();
                break;
            case "5":
                checkoutItem.getInput();
                break;
            case "6":
                viewFines.OutstandingFines();
                break;
            case "7":
                unavailableItem.getInput();
                break;
            case "8":
                new ItemRenewal();
                break;
            case "9":
                ViewCheckedOut view = new ViewCheckedOut();
                view.displayUserCheckedOut();
                break;
            case "10":
                viewInfo.viewUserMenu();
                break;

            default:
                System.out.print("Unknown Selection");
        }
    }

}
