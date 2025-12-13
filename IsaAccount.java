import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class IsaAccount extends UserAccount
{
    private final double interestRate;
    private final double penaltyRate;
    private final LocalDateTime createdTime;

    private final Timer interestTimer;

    // constructor to initialize the ISA account
    public IsaAccount(String username, int pin, double initialBalance, double interestRate, double penaltyRate, int interestIntervalMs)
    {
        // calls the parent constructor to set username, pin, balance, and account type
        super(username, pin, initialBalance, "Lifetime ISA");

        this.interestRate = interestRate;
        this.penaltyRate = penaltyRate;
        this.createdTime = LocalDateTime.now();

        // creates a timer and starts it
        interestTimer = new Timer(true);
        startInterestTimer(interestIntervalMs);
    }

    // individual timer for the account
    private void startInterestTimer(long intervalMs)
    {
        // anonymous inner class for the timer task
        TimerTask interestTask = new TimerTask()
        {
            @Override
            public void run()
            {
                applyInterest();
            }
        };

        interestTimer.scheduleAtFixedRate(interestTask, 30000, intervalMs);
        }

    // method to apply interest to the account balance
    public void applyInterest()
    {
        double interest = getBalance() * (interestRate / 100);
        deposit(interest);
    }

    @Override
    public boolean withdraw(double amount)
    {
        if (amount <= 0) return false;

        // calculates the time since account creation
        Duration timeOpen = Duration.between(createdTime, LocalDateTime.now());
        double totalWithdrawal = amount;

        // applies penalty if withdrawn within 5 minutes of account creation
        if (timeOpen.toMinutes() < 5)
        {
            // calculates and adds the penalty to the total withdrawal amount
            double penalty = amount * (penaltyRate / 100);
            totalWithdrawal += penalty;
            System.out.println("\nEarly withdrawal penalty applied" + " (" + penaltyRate + " %)"+ ": £" + penalty);
        }

        // checks if there are sufficient funds including penalty
        if (getBalance() >= totalWithdrawal)
        {
            return super.withdraw(totalWithdrawal);
        }
        else
        {
            return false;
        }
    }
}
