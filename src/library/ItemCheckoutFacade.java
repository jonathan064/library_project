package library;

public class ItemCheckoutFacade
{
    AvailabilityCheck available;
    BestSellerCheck bestseller;
    CheckoutItem checkoutItem;
    CountRestrictionCheck itemLimit;
    public ItemCheckoutFacade (String item_id,String user_id, String checkout_date)
    {

        available = new AvailabilityCheck();
        bestseller = new BestSellerCheck();
        checkoutItem = new CheckoutItem();
        itemLimit = new CountRestrictionCheck();
        //sets due date based on bestseller status
        checkoutItem.setWeeksToAdd(bestseller.isBestSeller(item_id));
        //if all conditions met then insert into checkout table
        if(available.isAvailable(item_id) && itemLimit.validCheckout(user_id))
        {
            checkoutItem.insertUserIntoTable(item_id,user_id,checkout_date);
        }
        else if(!available.isAvailable(item_id))
        {
            System.out.print("Sorry that item isn't available for checkout");

        }
        else if(!itemLimit.validCheckout(user_id))
        {
            System.out.print("Children can only check out up to 5 items.");
        }
    }
}
