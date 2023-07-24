package library;

import java.util.Objects;
import java.util.Scanner;
public class AddUser
{
    private String name, address, phone_number, correct;

    public void insert ()
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
            System.out.print("Is this information correct? " + name + ", " + address + ", " + phone_number + " Y/N ");
            correct = input.nextLine();
            if (Objects.equals(correct, "Y") || Objects.equals(correct, "y"))
            {
                redo = false;
            }

        }
    }
}


