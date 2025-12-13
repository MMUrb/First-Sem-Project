public class RegularAccount extends UserAccount
{
    // constructor to initialize the regular account
    public RegularAccount(String username, int pin, double initialBalance)
    {
        // calls the parent constructor to set username, pin, balance, and account type
        super(username, pin, initialBalance, "No Interest");
    }
}
