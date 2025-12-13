public abstract class UserAccount
{
    // username for the account
    // made final so username cant be changed later in the code
    private final String username;

    // pin for the account
    // made final so pin cant be changed later in the code
    private final int pin;
    
    // balance for the account
    // does not need to be final as the balance will change with every transaction
    protected double balance;

    private final String accountType;

    // constructor to initialize the user account
    public UserAccount(String username, int pin, double initialBalance, String accountType)
    {
        // set the username, pin, and initial balance
        this.username = username;
        this.pin = pin;
        this.balance = initialBalance;
        this.accountType = accountType;
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

    // getter method for account type
    public String getAccountType()
    {
        return accountType;
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