public class IsaAccount extends UserAccount
{
    public IsaAccount(String username, int pin, double initialBalance, double interestRate)
    {
        super(username, pin, initialBalance, "isa");
    }

    
}