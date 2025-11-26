// decimalformat is used to format the balance display
// scanner is used to take the users input
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// the main class that runs the banking app
public class BankingApp
{
    public static void main(String[] args)
    {
        // create a decimal format object to display the balance with commas and 2 decimal places to show pence
        // so £3890123.5 becomes £3,890,123.50
        DecimalFormat df = new DecimalFormat("#,###.00");
        // creates an AccountManager object that controls the account functions
        AccountManager user = new AccountManager();

        Timer timer = new Timer(true);

        timer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                for (UserAccount account : user.getAllAccounts())
                {
                    if (account instanceof InterestAccount interestAccount)
                    {
                        interestAccount.applyInterest();
                        user.saveAccountsToFile();
                    }
                    else if (account instanceof IsaAccount isaAccount)
                    {
                        isaAccount.applyInterest();
                        user.saveAccountsToFile();
                    }
                }
            }
            // interest is applied every 10 seconds to see the effect quickly
            // delay of 0 means interest is applied immediately on starting the app
        },0, 10000);


        // scanner object to take the users input
        Scanner scan = new Scanner(System.in);

        // variable to store the users menu choice
        int mainMenu;

        // the main menu loop that keeps running until the user exits
        do
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

            // variables to store the username by the user
            String userName;
            // variable to store the pin entered by the user
            int PIN;

            // switch statement to handle the users menu choice
            switch(mainMenu)
            {
                // the user can login to an existing account
                case 1:
                    System.out.print("\nEnter your username: ");
                    userName = scan.nextLine();
                    System.out.print("Enter your PIN: ");
                    PIN = scan.nextInt();

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
                                    System.out.println("Your balance is: £" + df.format(account.getBalance()));
                                    break;

                                // allows the user to deposit funds into their account
                                case 2:
                                    System.out.print("Enter amount to deposit: £");
                                    double depositAmount = scan.nextDouble();
                                    // call the deposit method from UserAccount
                                    // adds the amount to the users balance
                                    account.deposit(depositAmount);
                                    // saves the updated data
                                    user.saveAccountsToFile();
                                    System.out.println("Deposit successful. New balance: £" + df.format(account.getBalance()));
                                    break;

                                // allows the user to withdraw funds from their account
                                case 3:
                                    System.out.print("Enter amount to withdraw: £");
                                    double withdrawAmount = scan.nextDouble();

                                    // call the withdraw method from UserAccount
                                    // removes the amount from the users balance if there are enough funds
                                    if(account.withdraw(withdrawAmount))
                                    {
                                        System.out.println("Withdrawal successful. New balance: £" + df.format(account.getBalance()));
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

                                // handles invalid menu choices
                                default:
                                    System.out.println("Invalid input, try again.");
                                    break;
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
                    // asks the user to choose a username for their account
                    System.out.print("Choose a username: ");
                    userName = scan.nextLine();

                    // asks the user to choose a pin for their account
                    System.out.print("Choose a PIN: ");
                    PIN = scan.nextInt();
                    // clear the newline character from the input buffer
                    scan.nextLine();

                    // variables for account creation
                    int accountOption;
                    String accountType = null;
                    double initialBalance = 0.00;

                    // account type selection loop
                    do
                    {
                        System.out.println("\nAccount selection" +
                                        "\n-----------------" +
                                        "\n1 - Standard Account" +
                                        "\n2 - Interest Account" +
                                        "\n3 - ISA Account" +
                                        "\n4 - Go Back");

                        System.out.print("\nWhich type of account would you like to create? ");
                        // reads the users choice
                        accountOption = scan.nextInt();

                        // handles the users account type choice
                        switch (accountOption)
                        {
                            case 1:
                                System.out.println("Standard Account selected.");
                                accountType = "Standard";
                                break;

                            case 2:
                                System.out.println("Interest Account selected.");
                                accountType = "High Yield Interest";
                                break;

                            case 3:
                                System.out.println("ISA Account selected.");
                                accountType = "Lifetime ISA";
                                break;

                            case 4:
                                System.out.println("Returning to Main Menu...");
                                break;

                            default:
                                System.out.println("Invalid input, try again.");
                                break;
                        }
                            
                    } while(accountOption != 1 && accountOption != 2 && accountOption != 3 && accountOption != 4);

                    if (accountOption == 4)
                        break;

                    // checks if the account has been created with its username, pin, initial balance, and account type
                    if (user.createAccount(userName, PIN, initialBalance, accountType))
                        {
                            System.out.println("\nAccount created successfully.");
                        }
                    // if the account could not be created, shows an error message
                    else
                        {
                            System.out.println("\nAccount already exists.");
                        }
                        break;

                // allows the user to delete their account
                case 3:
                    // asks the user for the username of their account
                    System.out.print("Enter your username: ");
                    userName = scan.nextLine();
                    
                    // asks the user for the pin of their account
                    System.out.print("Enter your PIN: ");
                    // stores the input and saves it to PIN
                    PIN = scan.nextInt();

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
                
                // handles invalid menu choices
                default:
                    System.out.println("Invalid input, try again.");
                    break;
                }

            // continues the main menu loop until the user chooses 4 to exit
            }while(mainMenu != 4);

            // close the scanner to prevent resource leaks
            scan.close();
    }
}
