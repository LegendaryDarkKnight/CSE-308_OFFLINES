import java.util.Scanner;
import java.util.StringTokenizer;

public class Menu {
    private int page;
    private Accounts currAccounts;
    private Employees currEmployees;

    private String[] getTokens(Scanner input) {
        StringTokenizer st1 = new StringTokenizer(input.nextLine(), " ");
        String[] tokens = new String[st1.countTokens()];
        int i = 0;
        while (st1.hasMoreTokens()) {
            tokens[i] = st1.nextToken();
            i++;
        }
        return tokens;
    }

    private void login(String s, Bank b) {
        switch (b.searchName(s)) {
            case 1 -> {
                currAccounts = b.loginAccount(s);
                page = 1;
            }
            case 2 -> {
                currEmployees = b.loginEmployee(s);
                page = 2;
            }
            default -> System.out.println("Invalid Account");
        }
    }

    public void mainMenu(Bank b) {
        Scanner input = new Scanner(System.in);

        String[] tokens = getTokens(input);

        String command = tokens[0];

        switch (command) {
            case "Create" -> {
                String name = tokens[1], type = tokens[2];
                double credit = Double.parseDouble(tokens[3]);
                currAccounts = b.createAccount(name, type, credit);
                if (currAccounts != null)
                    page = 1;
            }
            case "Open" -> {
                String s = tokens[1];
                login(s, b);
            }
            case "INC" -> b.timeSkip();
            case "Exit" -> page = 3;

            default -> System.out.println("Invalid Command");
        }
    }

    public void menu1(Bank b) {
        Scanner input = new Scanner(System.in);
        String[] tokens = getTokens(input);
        String command = tokens[0];
        switch (command) {
            case "Deposit" -> {
                double amount = Double.parseDouble(tokens[1].replaceAll(",",""));
                if (currAccounts.deposit(amount)) {
                    System.out.println(amount + "$ deposited; " + currAccounts.queryDeposit());
                    b.depositInBank(amount);
                } else
                    System.out.println("Invalid Transaction");
            }

            case "Withdraw" -> {
                double amount = Double.parseDouble(tokens[1].replaceAll(",",""));
                if (currAccounts.withdraw(amount)) {
                    System.out.println(amount + "$ withdrawn; " + currAccounts.queryDeposit());
                    b.withdrawFromBank(amount);
                } else
                    System.out.println("Invalid Transaction; " + currAccounts.queryDeposit());
            }

            case "Request" -> {
                double amount = Double.parseDouble(tokens[1]);
                b.requestOfLoan(currAccounts, amount);
            }

            case "Query" -> System.out.println(currAccounts.queryDeposit());

            case "Close" -> {
                System.out.println("Transaction Closed for " + currAccounts.getAccName());
                currAccounts = null;
                page = 0;
            }

            default -> System.out.println("Invalid Command");
        }
    }

    public void menu2(Bank b) {
        Scanner input = new Scanner(System.in);
        String[] tokens = getTokens(input);
        String command = tokens[0], name;
        double newRate;

        switch (command) {
            case "Approve" -> {
                if (!tokens[1].equalsIgnoreCase("Loan"))
                    System.out.println("Invalid Argument");
                else if (!currEmployees.approveLoan(b))
                    System.out.println("You don't have permission for this operation");
            }

            case "Change" -> {
                name = tokens[1];
                newRate = Double.parseDouble(tokens[2]);
                if (currEmployees.changeInterestRate(name, newRate, b))
                    System.out.println(name + "'s interest rate has changed to " + newRate);
                else
                    System.out.println("You don't have permission for this operation");
            }

            case "Lookup" -> {
                name = tokens[1];
                System.out.println(name + "'s " + currEmployees.lookup(name, b));
            }
            case "See" -> System.out.println(currEmployees.seeInternalFund(b));
            case "Close" -> {
                System.out.println("Transaction Closed for " + currEmployees.getEmployeeName());
                page = 0;
            }
            default -> System.out.println("Invalid Command");
        }
    }

    public void runMenu(Bank b) {
        page = 0;
        while (page != 3) {
            switch (page) {
                case 1 -> {
                    System.out.print("Logged in as " + currAccounts.getAccName() + ".Type your command:  ");
                    menu1(b);
                }
                case 2 -> {
                    System.out.print("Logged in as " + currEmployees.getEmployeeName() + ".Type your command:  ");
                    menu2(b);
                }
                default -> {
                    System.out.print("You are on main menu. Type your command: ");
                    mainMenu(b);
                }
            }
        }
    }
}
