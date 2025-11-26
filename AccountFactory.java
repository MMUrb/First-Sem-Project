public class AccountFactory
{
    // accountType should be something like "regular", "interest", or "isa"
    public static UserAccount create(String username, int pin, double initialBalance, String accountType)
    {
        if (accountType == null) throw new IllegalArgumentException("accountType cannot be null");

        switch (accountType.toLowerCase())
        {
            case "regular":
                return new RegularAccount(username, pin, initialBalance);

            case "interest":
                return new InterestAccount(username, pin, initialBalance, 2.0);

            case "isa":
                return new IsaAccount(username, pin, initialBalance, 5.0);

            default:
                throw new IllegalArgumentException("Unknown account type: " + accountType);
        }
    }
}
