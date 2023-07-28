package library;

public class ItemCheckoutFacade
{
    private String item;
    private String user;
    AvailabilityCheck available;
    public ItemCheckoutFacade (String item_id,String user_id)
    {
        item = item_id;
        user = user_id;
        available = new AvailabilityCheck();
        if(!available.isAvailable(item_id))
        {
            System.out.print("Sorry that item isn't available for checkout");
        }
    }
}
