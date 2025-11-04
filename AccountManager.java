import java.util.ArrayList;
import java.io.*;



public class AccountManager
{
    private ArrayList<UserAccount> accounts;
    private final String FILE_PATH = "accounts.txt";

    public AccountManager()
    {
        accounts = new ArrayList<>();
        loadAccountsFromFile();
    }

}