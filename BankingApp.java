import java.util.Scanner;
import java.text.DecimalFormat;

public class BankingApp
{

    private static final Object firstOption = null;
    public static void main(String[] args)
    {
        DecimalFormat df = new DecimalFormat("#.00");
        AccountManager user = new AccountManager();
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

                    UserAccount account = user.login(userName, PIN);

                    if (account != null)
                    {
                        int firstOption;
                        do
                        {
                            System.out.println("Menu" +
                                               "\n----------" + 
                                               "\n1 - View Balance" + 
                                               "\n2 - Deposit Funds" + 
                                               "\n3 - Withdraw Funds" + 
                                               "\n4 - Logout");
                            System.out.print("What would you like to do?");
                            firstOption = scan.nextInt();

                            switch(firstOption)
                            {
                                case 1:
                                    System.out.println("Your balance is: $" + df.format(account.getBalance()));
                                    break;

                                case 2:
                                    System.out.print("Enter amount to deposit: $");
                                    double depositAmount = scan.nextDouble();
                                    account.deposit(depositAmount);
                                    System.out.println("Deposit successful. New balance: $" + df.format(account.getBalance()));
                                    break;

                                case 3:
                                    System.out.print("Enter amount to withdraw: $");
                                    double withdrawAmount = scan.nextDouble();
                                    if(account.withdraw(withdrawAmount))
                                    {
                                        System.out.println("Withdrawal successful. New balance: $" + df.format(account.getBalance()));
                                    }
                                    else
                                    {
                                        System.out.println("Insufficient funds or invalid amount.");
                                    }
                                    break;

                                case 4:
                                    System.out.println("Logging out...");
                                    break;

                            }

                        } while(firstOption != 4);

                    }
                    break;

                case 2:
                    System.out.print("Choose a username: ");
                    userName = scan.nextLine();
                    System.out.print("Choose a PIN: ");
                    PIN = scan.nextInt();
                    double initialBalance = 0.00;

                    if(user.createAccount(userName, PIN, initialBalance))
                    {
                        System.out.println("Account created successfully.");
                    }
                    else
                    {
                        System.out.println("Account already exists. Please try again.");
                    }

                    break;

                case 3:
                    System.out.print("Enter your username: ");
                    userName = scan.nextLine();
                    System.out.print("Enter your PIN: ");
                    PIN = scan.nextInt();

                    if(user.deleteAccount(userName, PIN))
                    {
                        System.out.println("Account deleted successfully.");
                    }
                    else
                    {
                        System.out.println("Invalid details. Please try again.");
                    }

                    break;

                case 9:
                    user.displayAccounts();
                    break;
                
                case 4:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid input, try again.");
            }
        }while(mainMenu != 4);

        scan.close();
    }
}