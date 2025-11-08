public class UserAccount
{
    private String username;
    private int pin;
    private double balance;

    public UserAccount(String username, int pin, double initialBalance)
    {
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUsername()
    {
        return username;
    }

    public int getPin()
    {
        return pin;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount)
    {
        if (amount > 0 )
        {
            balance += amount;
        }
    }

    public boolean withdraw(double amount)
    {
        if (amount > 0 && amount <= balance)
        {
            balance -= amount;
            return true;
        }
        return false;
    }
}