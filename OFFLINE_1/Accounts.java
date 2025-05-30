abstract class Accounts {
    protected String accName, accType;
    protected double accCredit, loanAmount, pendingLoan;
    protected int year;

    public Accounts(String name, String type, double credit) {
        accName = name;
        accType = type;
        accCredit = credit;
        loanAmount = 0;
        pendingLoan = 0;
        year = 0;
    }

    public boolean deposit(double amount) {
        accCredit += amount;
        return true;
    }

    public abstract boolean withdraw(double amount);

    public abstract boolean requestLoan(double amount);

    public String queryDeposit() {
        return "Current balance " + accCredit + "$" + (loanAmount > 0 ? (", loan " + loanAmount + "$") : "");
    }

    public void changeLoan() {
        loanAmount += pendingLoan;
        accCredit += pendingLoan;
        pendingLoan = 0;
    }

    public String getAccName() {
        return accName;
    }

    public String getAccType() {
        return accType;
    }

    public void changeYearlyCredit(double rate) {
        year += 1;
        accCredit = accCredit + accCredit * rate / 100 - loanAmount * 0.1 - 500;
    }
}

class Savings extends Accounts {
    public Savings(String name, double credit) {
        super(name, "SAVINGS", credit);
    }

    @Override
    public boolean withdraw(double amount) {
        if (accCredit - amount < 1_000)
            return false;
        accCredit -= amount;
        return true;
    }

    @Override
    public boolean requestLoan(double amount) {
        if (10_000 > amount) {
        } else {
            return false;
        }
        pendingLoan += amount;
        return true;
    }
}

class Student extends Accounts {
    public Student(String name, double credit) {
        super(name, "STUDENT", credit);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 10_000 || accCredit < amount)
            return false;
        accCredit -= amount;
        return true;
    }

    @Override
    public boolean requestLoan(double amount) {
        if (amount > 1_000)
            return false;
        pendingLoan += amount;
        return true;
    }

    @Override
    public void changeYearlyCredit(double rate) {
        year += 1;
        accCredit = accCredit + accCredit * rate / 100 - loanAmount * 0.1;
    }
}

class FixedDeposit extends Accounts {
    public FixedDeposit(String name, double credit) {
        super(name, "FIXEDDEPOSIT", credit);
    }

    @Override
    public boolean deposit(double amount) {
        if (amount < 50_000)
            return false;
        accCredit += amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (year < 1 || accCredit < amount)
            return false;
        accCredit -= amount;
        return true;
    }

    @Override
    public boolean requestLoan(double amount) {
        if (amount > 100_000)
            return false;
        pendingLoan += amount;
        return true;
    }
}