import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<String, Employees> employees;
    private final Map<String, Accounts> accList;
    private Map<String, Double> ratesMap;
    private final List<Accounts> pendingLoans;
    private double internalFund;
    private int year;

    public Bank(int officer, int cashier, double fund) {
        internalFund = fund;
        accList = new HashMap<>();
        pendingLoans = new ArrayList<>();
        generateEmployees(officer, cashier);
        generateRates();
    }

    public static void main(String[] args) {
        Bank bank = new Bank(2, 5, 1_000_000);
        Menu menu = new Menu();
        menu.runMenu(bank);
    }

    private void generateEmployees(int officer, int cashier) {
        employees = new HashMap<>();
        Employees e = new ManagingDirector();
        System.out.print("Bank Created; MD");
        employees.put("MD", e);
        for (int i = 1; i <= officer; i++) {
            e = new Officer("S" + i);
            employees.put("S" + i, e);
            System.out.print(", S"+i);
        }
        for (int i = 1; i <= cashier; i++) {
            e = new Cashier("C" + i);
            employees.put("C" + i, e);
            System.out.print(", C"+i);
        }
        System.out.println(" created");
    }

    private void generateRates() {
        ratesMap = new HashMap<>();
        ratesMap.put("SAVINGS", 10.0);
        ratesMap.put("STUDENT", 5.0);
        ratesMap.put("FIXEDDEPOSIT", 15.0);
    }

    private Accounts accCreation(String name, String type, double credit) {
        if (type.equalsIgnoreCase("SAVINGS")) {
            return new Savings(name, credit);
        } else if (type.equalsIgnoreCase("STUDENT")) {
            return new Student(name, credit);
        } else if (type.equalsIgnoreCase("FIXEDDEPOSIT")) {
            return new FixedDeposit(name, credit);
        }
        return null;
    }

    private boolean accountValidityChecker(String name, String type, double credit) {
        if (accList.containsKey(name))
            System.out.println("Already an Account exists");
        else if (type.equalsIgnoreCase("FIXEDDEPOSIT") && credit < 100_000)
            System.out.println("Not Enough Money");
        else
            return true;
        return false;
    }

    public Accounts createAccount(String name, String type, double credit) {
        if (!accountValidityChecker(name, type, credit))
            return null;
        Accounts currAccounts = accCreation(name, type, credit);
        accList.put(name, currAccounts);
        System.out.println("Student account for " + name + " Created; initial balance " + credit + "$");
        return currAccounts;
    }

    public Accounts loginAccount(String s) {
        return accList.get(s);
    }

    public Employees loginEmployee(String s) {
        return employees.get(s);
    }

    public int searchName(String s) {
        if (accList.containsKey(s))
            return 1;
        else if (employees.containsKey(s))
            return 2;
        return 0;
    }

    public void timeSkip() {
        year += 1;
        for (Map.Entry<String, Accounts> entry : accList.entrySet()) {
            entry.getValue().changeYearlyCredit(ratesMap.get(entry.getValue().getAccType()));
        }
        System.out.println("1 year passed");
    }

    public void depositInBank(double amount) {
        internalFund += amount;
    }

    public void withdrawFromBank(double amount) {
        internalFund -= amount;
    }

    public void requestOfLoan(Accounts currAccounts, double amount) {
        if (currAccounts.requestLoan(amount)) {
            pendingLoans.add(currAccounts);
            System.out.println("Loan request successful, sent for approval");
        } else
            System.out.println("Invalid Request");
    }

    public void pendingLoansApproved() {
        System.out.print("Loan for ");
        for (Accounts a : pendingLoans) {
            a.changeLoan();
            System.out.print(a.getAccName() + ", ");
        }
        System.out.println(" Approved");
        pendingLoans.clear();
    }

    public void deployChangeInInterest(String accType, double rate) {
        ratesMap.put(accType.toUpperCase(), rate);
    }

    public String facilitateLookup(String name) {
        if (!accList.containsKey(name))
            return "No such account";
        return accList.get(name).queryDeposit();
    }

    public double getInternalFund() {
        return internalFund;
    }
}
