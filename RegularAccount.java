public class RegularAccount extends UserAccount
{
    public RegularAccount(String username, int pin, double initialBalance)
    {
        super(username, pin, initialBalance, "Standard");
    }
}
