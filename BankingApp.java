// scanner is used to take the users input
import java.util.InputMismatchException;
import java.util.Scanner;



// the main class that runs the banking app
public class BankingApp
{
    public static void main(String[] args)
    {
        // creates an AccountManager object that controls the account functions
        AccountManager user = new AccountManager();

        // scanner object to take the users input
        Scanner scan = new Scanner(System.in);

        // variable to store the users menu choice
        int mainMenu;

        // the main menu loop that keeps running until the user exits
        do
        {
            try
            {
                // display the main menu options
                System.out.println("\nMenu" +
                                "\n----------" + 
                                "\n1 - Login and view your account" + 
                                "\n2 - Create an account" + 
                                "\n3 - Delete your account" + 
                                "\n4 - Exit application" +
                                "\n9 - View all accounts");
                
                // prompt the user for their menu choice
                System.out.print("\nWhat would you like to do? ");
                // reads the users choice
                mainMenu = scan.nextInt();
                // clear the newline character from the input buffer
                scan.nextLine();
            }
            catch (InputMismatchException e)
            {
                // handles invalid input that isnt an integer
                System.out.println("\nPlease enter a valid option.");
                // clear the invalid input
                scan.nextLine();
                // set to an invalid option to continue the loop
                mainMenu = -1;
            }

            // variables to store the username by the user
            String userName;
            // variable to store the pin entered by the user
            int PIN;

            // switch statement to handle the users menu choice
            switch(mainMenu)
            {
                // the user can login to an existing account
                case 1:
                    try
                    {
                        System.out.print("\nEnter your username: ");
                        userName = scan.nextLine();
                        System.out.print("Enter your PIN: ");
                        PIN = scan.nextInt();
                    }
                    catch (InputMismatchException e)
                    {
                        // handles invalid input that isnt an integer
                        System.out.println("\nSpecial characters are not allowed.");
                        // clear the invalid input
                        scan.nextLine();
                        break;
                    }

                    // attempt to login with the provided information
                    UserAccount account = user.login(userName, PIN);

                    // if the login is successful, the user can view their account
                    if (account != null)
                    {
                        System.out.println("\nLogin successful. Welcome, " + account.getUsername() + "!");
                        // variable to store the users account menu choice
                        int firstOption;

                        // second loop for account options
                        do
                        {
                            try
                            {
                                // display the account menu options
                                System.out.println("\nMenu" +
                                                "\n----------" + 
                                                "\n1 - View Balance" + 
                                                "\n2 - Deposit Funds" + 
                                                "\n3 - Withdraw Funds" + 
                                                "\n4 - Logout");
                                                
                                // prompt the user for their account menu choice
                                System.out.print("\nWhat would you like to do? ");
                                // read the users choice
                                firstOption = scan.nextInt();

                                // switch statement to handle the users choice
                                switch(firstOption)
                                {
                                    // allows the user to see their balance
                                    case 1:
                                        System.out.println("Balance: £" + user.getFormatter().format(account.getBalance()));
                                        break;

                                    // allows the user to deposit funds into their account
                                    case 2:
                                        System.out.print("Enter amount to deposit: £");

                                        // stores the amount to deposit, entered by the user
                                        double depositAmount = scan.nextDouble();

                                        // call the deposit method from UserAccount
                                        // adds the amount to the users balance
                                        account.deposit(depositAmount);

                                        // saves the updated data
                                        user.saveAccountsToFile();
                                        System.out.println("Deposit successful. Your new balance is: £" + user.getFormatter().format(account.getBalance()));
                                        break;

                                    // allows the user to withdraw funds from their account
                                    case 3:
                                        System.out.print("Enter amount to withdraw: £");
                                        double withdrawAmount = scan.nextDouble();

                                        // call the withdraw method from UserAccount
                                        // removes the amount from the users balance if there are enough funds
                                        if(account.withdraw(withdrawAmount))
                                        {
                                            System.out.println("Withdrawal successful. Your new balance is: £" + user.getFormatter().format(account.getBalance()));
                                        }
                                        // shows message if there are insufficient funds
                                        else
                                        {
                                            System.out.println("Insufficient funds or invalid amount.");
                                        }
                                        // saves the updated data
                                        user.saveAccountsToFile();
                                        break;
                                    
                                    // allows the user to logout of their account
                                    case 4:
                                        System.out.println("Logging out...");
                                        break;
                                    }
                            }
                            catch (InputMismatchException e)
                            {
                                // handles invalid input that isnt an integer
                                System.out.println("\nPlease choose a valid amount to deposit.");
                                // clear the invalid input
                                scan.nextLine();
                                // set to an invalid option to continue the loop
                                firstOption = -1;
                            }

                        // continues the loop until the user chooses 4 to logout
                        } while(firstOption != 4);
                    }
                    else
                    {
                        System.out.println("\nInvalid username or PIN. Please try again.");
                    }
                    break;

                // allows the user to create a new account
                case 2:
                    try
                    {
                        // asks the user to choose a username for their account
                        System.out.print("Choose a username: ");
                        userName = scan.nextLine();

                        // asks the user to choose a pin for their account
                        System.out.print("Choose a PIN: ");
                        PIN = scan.nextInt();
                        // clear the newline character from the input buffer
                        scan.nextLine();
                    }
                    catch (InputMismatchException e)
                    {
                        // handles invalid input that isnt an integer
                        System.out.println("\nSpecial charatcers are not allowed.");
                        // clear the invalid input
                        scan.nextLine();
                        break;
                    }

                    // variables for account creation
                    int accountOption;
                    String accountType = null;
                    double initialBalance = 0.00;

                    // account type selection loop
                    do
                    {
                        try
                        {
                            System.out.println("\nAccount selection" +
                                            "\n-----------------" +
                                            "\n1 - No Interest Account" +
                                            "\n2 - High Yield Interest Account" +
                                            "\n3 - Lifetime ISA Account" +
                                            "\n4 - Go Back");

                            System.out.print("\nWhich type of account would you like to create? ");

                        
                            // reads the users choice
                            accountOption = scan.nextInt();

                            // handles the users account type choice
                            switch (accountOption)
                            {
                                // standard account type
                                case 1:
                                    System.out.println("Standard Account selected.");
                                    accountType = "No Interest";
                                    break;

                                // high yield interest account type
                                case 2:
                                    System.out.println("Interest Account selected.");
                                    accountType = "High Yield Interest";
                                    break;

                                // lifetime isa account type
                                case 3:
                                    System.out.println("Lifetime ISA Account selected.");
                                    accountType = "Lifetime ISA";
                                    break;

                                // go back to the main menu
                                case 4:
                                    System.out.println("Returning to Main Menu...");
                                    break;
                            }
                        }
                        catch (InputMismatchException e)
                        {
                            // handles invalid input that isnt an integer
                            System.out.println("\nPlease enter a valid account.");
                            // clear the invalid input
                            scan.nextLine();
                            // set to an invalid option to continue the loop
                            accountOption = -1;
                        }      
                    } while(accountOption != 1 && accountOption != 2 && accountOption != 3 && accountOption != 4);

                    if (accountOption == 4)
                        break;

                    // checks if the account has been created with its username, pin, initial balance, and account type

                    AccountManager.createAccountResult result = user.createAccount(userName, PIN, initialBalance, accountType);

                    switch(result)
                    {
                        // displays messages based on the account creation result
                        case success:
                            System.out.println("\nAccount created successfully.");
                            break;

                        // shows specific error message if the account creation failed due to the pin being invalid
                        case invalidPin:
                            System.out.println("\nInvalid PIN. Please ensure your PIN is a 4 digit number.");
                            break;

                        // shows specific error message if the account creation failed due to the username already existing
                        case accountExists:
                            System.out.println("\nAccount already exists.");
                            break;

                        // shows a general error message for any other account creation failure
                        case unknownError:
                            System.out.println("\nAn unknown error occurred. Please try again.");
                            break;
                    }
                    break;

                // allows the user to delete their account
                case 3:
                    try
                    {
                        // asks the user for the username of their account
                        System.out.print("Enter your username: ");
                        userName = scan.nextLine();
                        
                        // asks the user for the pin of their account
                        System.out.print("Enter your PIN: ");
                        // stores the input and saves it to PIN
                        PIN = scan.nextInt();
                    }
                    catch (InputMismatchException e)
                    {
                        // handles invalid input that isnt an integer
                        System.out.println("\nSpecial characters are not allowed.");
                        // clear the invalid input
                        scan.nextLine();
                        break;
                    }
                    
                    // attempt to delete the account with the information the user provided
                    if(user.deleteAccount(userName, PIN))
                    {
                        System.out.println("\nAccount deleted successfully.");
                    }
                    else
                    {
                        System.out.println("\nInvalid details. Please try again.");
                    }
                    break;

                // shows all account details - username, pin, and balance
                // implemented for testing purposes only
                case 9:
                    user.displayAccounts();
                    break;
                
                // exits the application
                case 4:
                    System.out.println("Exiting application...");
                    break;
                }
            // continues the main menu loop until the user chooses 4 to exit
            }while(mainMenu != 4);

            // close the scanner to prevent resource leaks
            scan.close();
    }
}
