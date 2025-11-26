public class IsaAccount extends UserAccount
{

    protected final double interestRate;

    public IsaAccount(String username, int pin, double initialBalance, double interestRate)
    {
        super(username, pin, initialBalance, "Lifetime ISA");

        this.interestRate = interestRate;
    }

    public void applyInterest()
    {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }
}