public class TestTransactions
{
    private static class Accountmanager
    {

        public Accountmanager()
        {

        }
    }

    public static void main(String[] args)
    {
        Accountmanager test = new Accountmanager();
        UserAccount account = new UserAccount(userName, Pin);
        account.deposit(100.0);
        
    }
}