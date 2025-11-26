// allows reading and writing to files, using bufferedreader and bufferedwriter
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.*;


public class AccountManager
{
    // create a decimal format object to display the balance with commas and 2 decimal places to show pence
    // so £3890123.5 becomes £3,890,123.50
    DecimalFormat df = new DecimalFormat("#,###.00");

    // arraylist to store multiple user accounts, accounts can also be added or removed from the list
    // used final so accounts cant be changed later in the code
    private final ArrayList<UserAccount> accounts = new ArrayList<>();

    public ArrayList<UserAccount> getAllAccounts()
    {
        return accounts;
    }
    
    // file path to store the account information
    // used final so FILE_PATH cant be changed later in the code
    private final String FILE_PATH = "C:\\Users\\Rahul\\OneDrive - MMU\\Digital Artefact\\First Sem Project\\AccountInfo.txt";

    public AccountManager()
    {
        // load existing accounts from the file
        loadAccountsFromFile();
    }

    public boolean createAccount(String username, int pin, double initialBalance, String accountType)
    {
        // checks if the pin is a 4 digit number
        if (pin < 1000 || pin > 9999)
        {
            return false;
        }
        // checks if an account with the same username and pin already exists
        if (findAccount(username, pin) != null)
        {
            // if so, return false means, the account was not created
            return false;
        }

        UserAccount newAccount = AccountFactory.create(username, pin, initialBalance, accountType);
        // adds a new user account to the accounts arraylist
        accounts.add(newAccount);   
        // saves the new data to the file
        saveAccountsToFile();
        // prints message shown in BankingApp
        return true;
    }

    // method to login to an account
    public UserAccount login(String username, int pin)
    {
        // finds the account with the given username and pin
        UserAccount account = findAccount(username, pin);

        // if the account is found and the pin matches, the user is logged in
        if (account != null && account.getPin() == pin)
        {
            // if they match it returns the account to access the account functions
            return account;
        }
        // if they dont match, it returns a message shown in BankingApp
        return null;
    }

    // method to delete an account
    public boolean deleteAccount(String username, int pin)
    {
        // finds the account with the given username and pin
        UserAccount account = findAccount(username, pin);

        // if the account is found and the pin matches, the account is deleted
        if (account != null && account.getPin() == pin)
        {
            // removes the account from the accounts arraylist
            accounts.remove(account);
            // saves the updated data to the file
            saveAccountsToFile();
            // shows the account was deleted
            return true;
        }
        // if the account is not found, or the pin doesnt match, it prints message shown in BankingApp
        return false;
    }

    // method to display all accounts
    // used for testing only
    public void displayAccounts()

    {
        // checks if there are no accounts to display
        if (accounts.isEmpty())
        {
            // tells the user there are no accounts
            System.out.println("\nNo accounts found.");
            return;
        }

        // it displays any accounts found
        else
        {
            System.out.println("\nHere are the current accounts:");

            // loops through the accounts arraylist and displays each account's information
            for (UserAccount account : accounts)
            {
                // this displays the username, pin, and balance of each account in a clear format
                System.out.println("Username: " + account.getUsername() + " | " + 
                                   "PIN: " + account.getPin() + " | " + 
                                   "Balance: £" + df.format(account.getBalance()) + " | " +
                                   "Account Type: " + account.getAccountType());
            }
        }
        
    }

    // method to find an account by username and pin
    private UserAccount findAccount(String username, int pin)
    {
        // loops through the accounts arraylist to find a matching account
        for (UserAccount account : accounts)
        {
            // checks if the username and pin match
            if (account.getUsername().equalsIgnoreCase(username) && account.getPin() == pin)
            {
                // if they match it returns the account
                return account;
            }
        }
        // if they dont match, it returns nothing
        return null;
    }

    public void interestTimer()
    {
        ScheduledExecutorService scheduler = java.util.concurrent.Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            for (UserAccount account : accounts)
            {
                if (account.getAccountType().equals("high yield interest") ||
                    account.getAccountType().equals("lifetime isa"))
                {
                    ((InterestAccount) account).applyInterest();
                }
            }
        
            saveAccountsToFile();

            System.out.println("Interest applied to eligible accounts.");
        }, 0, 30, TimeUnit.SECONDS);
    }

    // method to save accounts to the file
    protected void saveAccountsToFile()
    {
        // use bufferedwriter to write account information to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH)))
        {
            // loops through the accounts arraylist and writes each account's information to the file
            for (UserAccount account : accounts)
            {
                // writes the username, pin, and balance separated by commas
                writer.write(account.getUsername() + "," + account.getPin() + "," + account.getBalance() +  "," + account.getAccountType());

                // writes a new line afetr each account
                writer.newLine();
            }
        // catch any io exceptions that may occur during file operations
        }catch (IOException e)
        {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    // method to load accounts from the file
    private void loadAccountsFromFile()
    {
        // check if the file exists before trying to read it
        File file = new File(FILE_PATH);
        // starts the method return if the file does not exist
        if (!file.exists())
        return;
            // use bufferedreader to read account information from the file
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH)))
            {
                // variable to store each line read from the file
                String line;

                // reads each line from the file until the end of the file is reached
                while((line = reader.readLine()) != null)
                {
                    // splits the line into parts using comma as the delimiter
                    String [] parts = line.split(",");
                    // checks if the line has the correct number of parts
                    if (parts.length == 4)
                    {
                        // parse the username, pin, and balance from the line
                        // orders the parts into their respective variables using parts[]
                        String username = parts[0];
                        int pin = Integer.parseInt(parts[1]);
                        double balance = Double.parseDouble(parts[2]);
                        String accountType = parts[3];

                        // create a new user account and add it to the accounts arraylist
                        UserAccount newAccount = AccountFactory.create(username, pin, balance, accountType);
                        // adds a new user account to the accounts arraylist
                        accounts.add(newAccount); 
                    }
                }
            // catch any io exceptions or number format exceptions that may occur during file operations
            }catch (IOException | NumberFormatException e)
        {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}    