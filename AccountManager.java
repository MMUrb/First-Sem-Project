import java.util.ArrayList;
import java.text.DecimalFormat;
import java.io.*;

public class AccountManager
{
    DecimalFormat df = new DecimalFormat("#,###.00");
    private ArrayList<UserAccount> accounts;
    private final String FILE_PATH = "C:\\Users\\Rahul\\OneDrive - MMU\\Digital Artefact\\First Sem Project\\AccountInfo.txt";

    public AccountManager()
    {
        accounts = new ArrayList<>();
        loadAccountsFromFile();
    }

    public boolean createAccount(String username, int pin, double initialBalance)
    {
        if (findAccount(username) != null)
        {
            return false;
        }

        accounts.add(new UserAccount(username, pin, initialBalance));
        saveAccountsToFile();
        return true;
    }


    public UserAccount login(String username, int pin)
    {
        UserAccount account = findAccount(username);

        if (account != null && account.getPin() == pin)
        {
            return account;
        }
        return null;
    }

    public boolean deleteAccount(String username, int pin)
    {
        UserAccount account = findAccount(username);

        if (account != null && account.getPin() == pin)
        {
            accounts.remove(account);
            saveAccountsToFile();
            return true;
        }
        return false;
    }

    public void displayAccounts()

    {
        if (accounts.isEmpty())
        {
            System.out.println("\nNo accounts found.");
            return;
        }

        else
        {
            System.out.println("\nYour account has been created!");

            for (UserAccount account :accounts)
            {
                System.out.println("Username: " + account.getUsername() + " | " + 
                                   "PIN: " + account.getPin() + " | " + 
                                   "Balance: £" + df.format(account.getBalance()));
            }
        }

    }

    private UserAccount findAccount(String username)
    {
        for (UserAccount account : accounts)
        {
            if (account.getUsername().equalsIgnoreCase(username))
            {
                return account;
            }
        }
        return null;
    }

    protected void saveAccountsToFile()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH)))
        {
            for (UserAccount account : accounts)
            {
                writer.write(account.getUsername() + "," + account.getPin() + "," + account.getBalance());
                writer.newLine();
            }
        }catch (IOException e)
        {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    private void loadAccountsFromFile()
    {
        File file = new File(FILE_PATH);
        if (!file.exists())
        return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH)))
        {
            String line;

            while((line = reader.readLine()) != null)
            {
                String [] parts = line.split(",");
                if (parts.length == 3)
                {
                    String username = parts[0];
                    int pin = Integer.parseInt(parts[1]);
                    double balance = Double.parseDouble(parts[2]);

                    accounts.add(new UserAccount(username, pin, balance));
                }
            }
            
        }catch (IOException | NumberFormatException e)
        {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }
}    