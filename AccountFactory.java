public class AccountFactory
{
    // accountType should be something like "regular", "interest", or "isa"
    public static UserAccount create(String username, int pin, double initialBalance, String accountType)
    {
        // validates the users input
        if (accountType == null) throw new IllegalArgumentException("accountType cannot be null");

        // decides which type of account to create based on the accountType parameter
        switch (accountType.toLowerCase())
        {
            // checks for standard account type
            case "no interest":
                // creates and returns a new regular account
                return new RegularAccount(username, pin, initialBalance);

            // checks for high yield interest account type
            case "high yield interest":
                // creates and returns a new interest account with a 10% interest rate, applied every 20 seconds
                return new InterestAccount(username, pin, initialBalance, 10.0, 20000);

            // checks for lifetime isa account type
            case "lifetime isa":
                // creates and returns a new isa account with a 20% interest rate, and a 50% penalty rate, interest applied every 60 seconds
                return new IsaAccount(username, pin, initialBalance, 20.0, 50.0, 60000);

            // if the account type doesnt match any known types
            default:
                // throws an exception for unknown account types
                throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
    }
}
