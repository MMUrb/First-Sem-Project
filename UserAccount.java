public class UserAccount
{
    // username for the account
    private String username;

    // pin for the account
    private int pin;
    
    // balance for the account
    private double balance;

    // constructor to initialize the user account
    public UserAccount(String username, int pin, double initialBalance)
    {
        // set the username, pin, and initial balance
        this.username = username;
        this.pin = pin;
        this.balance = initialBalance;
    }

    // getter methods for username
    public String getUsername()
    {
        return username;
    }

    // getter method for pin
    public int getPin()
    {
        return pin;
    }

    // getter method for balance
    public double getBalance()
    {
        return balance;
    }

    // method to deposit funds into the account
    public void deposit(double amount)
    {
        // only allow deposits of positive amounts
        if (amount > 0 )
        {
            // adds the amount entered to the balance
            balance += amount;
        }
    }

    // method to withdraw funds from the account
    public boolean withdraw(double amount)
    {
        // only allow withdrawals if there are sufficient funds and the amount is positive
        if (amount > 0 && amount <= balance)
        {
            // removes the amount entered from the balance
            balance -= amount;
            // shows that the withdrawal was successful
            return true;
        }
        // shows that the withdrawal was unsuccessful
        // prints message shown in BankingApp
        return false;
    }
}