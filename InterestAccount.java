public class InterestAccount extends UserAccount
{

    protected final double interestRate;

    public InterestAccount(String username, int pin, double initialBalance, double interestRate)
    {
        super(username, pin, initialBalance, "interest");

        this.interestRate = interestRate;
    }

    public void applyInterest()
    {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }
    
}
