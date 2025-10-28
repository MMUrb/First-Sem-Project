import java.util.Scanner;
import java.text.DecimalFormat;

public class BankingApp
{
    public static void main(String[] args)
    {

        Scanner scan = new Scanner(System.in);
        int mainMenu;

        do
        {
            System.out.println("\nMenu" +
                               "\n----------" + 
                               "\n1 - Login and view your account" + 
                               "\n2 - Create an account" + 
                               "\n3 - Delete your account" + 
                               "\n4 - Exit application" +
                               "\n9 - View all accounts");
            
            System.out.print("\nWhat would you like to do?");
            mainMenu = scan.nextInt();
            scan.nextLine();

            String userName;
            int PIN;

            switch(mainMenu)
            {
                case 1:
                    System.out.print("\nEnter your username: ");
                    userName = scan.nextLine();
                    System.out.print("\nEnter your PIN: ");
                    PIN = scan.nextInt();
                    break;

                case 2:
                    System.out.print("Choose a username: ");
                    userName = scan.nextLine();
                    System.out.print("Choose a PIN: ");
                    PIN = scan.nextInt();
                    break;

                case 3:
                    System.out.print("Choose a username: ");
                    userName = scan.nextLine();
                    System.out.print("Choose a PIN: ");
                    PIN = scan.nextInt();break;

                case 4:
                System.out.println("Exiting application...");

                case 9:

                default:
                    System.out.println("Invalid input, try again.");
            }
        }while(mainMenu != 4);
    }
}