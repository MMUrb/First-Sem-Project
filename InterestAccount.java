import java.util.Timer;
import java.util.TimerTask;

public class InterestAccount extends UserAccount
{
    protected final double interestRate;
    private final Timer interestTimer;

    // constructor to initialize the interest account
    public InterestAccount(String username, int pin, double initialBalance, double interestRate, int interestIntervalMs)
    {
        // calls the parent constructor to set username, pin, balance, and account type
        super(username, pin, initialBalance, "High Yield Interest");
        this.interestRate = interestRate;

        // Start the interest timer when the account is created
        interestTimer = new Timer(true);
        startInterestTimer(interestIntervalMs);
    }

    // method to apply interest to the account balance
    public void applyInterest()
    {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
    }

    // individual timer for the account
    private void startInterestTimer(long intervalMs)
    {
        // schedules the task to apply interest at fixed intervals
        interestTimer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                applyInterest();
            }
        // shows how the interest is applied
        // starts when the account is created, and is applied at fixed intervals (can be changed in AccountFactory)
        }, 0, intervalMs);
    }
}
