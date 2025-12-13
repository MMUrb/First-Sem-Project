public class TestTransactions
{
    public static void main(String[] args)
    {
        System.out.println("=== STARTING TESTS ===\n");

        testUserAccount();
        testInterestAccount();
        testIsaAccount();
        testAccountManager();

        System.out.println("\n=== TESTS FINISHED ===");
    }


    // testing the standard account
    private static void testUserAccount()
    {
        System.out.println("UserAccount tests:");

        UserAccount account = new InterestAccount("testUser", 1234, 100, 0, 1);

        account.deposit(50);
        System.out.println("Deposit £50: Expected £150 | Actual £" + account.getBalance());

        boolean withdrawn = account.withdraw(30);
        System.out.println("Withdraw £30: Expected true | Actual " + withdrawn);
        System.out.println("Balance expected £120 | Actual £" + account.getBalance());

        withdrawn = account.withdraw(500);
        System.out.println("Over withdraw: Expected false | Actual " + withdrawn);
        }

    // testing the interest account
    private static void testInterestAccount()
    {
        System.out.println("\nInterestAccount tests:");

        InterestAccount interestAccount = new InterestAccount("interestUser", 1111, 100, 10, 30000);

        interestAccount.applyInterest();
        System.out.println("Apply 10% interest: Expected £110 | Actual £" + interestAccount.getBalance());
    }


    // testing the ISA account
    private static void testIsaAccount()
    {
        System.out.print("\nIsaAccount tests:");

        IsaAccount isa = new IsaAccount("isaUser", 2222, 200, 5, 10, 30000);

        boolean withdrawn = isa.withdraw(50);
        System.out.println("Early withdraw £50: Expected penalty | Success " + withdrawn);
        System.out.println("Balance after withdrawal: £" + isa.getBalance());
    }

    // testing the account manager
    private static void testAccountManager()
    {
        System.out.println("\nAccountManager tests:");

        AccountManager manager = new AccountManager();

        AccountManager.createAccountResult created =
                manager.createAccount("managerUser", 3333, 0, "no interest");

        System.out.println("Create account: Expected SUCCESS | Actual " + created);

        UserAccount login = manager.login("managerUser", 3333);
        System.out.println("Login: Expected not null | Actual " + (login != null));

        boolean deleted = manager.deleteAccount("managerUser", 3333);
        System.out.println("Delete account: Expected true | Actual " + deleted);

        System.out.println();
    }
}
