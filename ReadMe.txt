Banking Application – ReadMe

-Run the application using the BankingApp class-

This project is a console-based Banking Application.
Using this application, you can create and delete accounts, choose different account types, and make deposits and withdrawals. 
All changes made within the application are automatically saved, even after the console is closed.

Interest Rates and Account Configuration:
If you would like to modify the interest rates or the intervals at which interest is applied for the InterestAccount and the IsaAccount, you can do so in the AccountFactory class.

The High Yield Interest Account has an interest rate of 10%, which is applied every 20,000 ms (20 seconds). This configuration can be found on line 20.

The Lifetime ISA has an interest rate of 20%, which is applied every 60,000 ms (60 seconds). This configuration can be found on line 25.

Withdrawal Penalty:
The Lifetime ISA also includes a withdrawal penalty. A 50% penalty is applied if a withdrawal is made within 5 minutes of creating the account.
The duration of this penalty timer can be adjusted within the IsaAccount class on line 62.