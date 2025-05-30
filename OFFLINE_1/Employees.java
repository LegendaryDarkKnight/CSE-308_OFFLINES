abstract class Employees {
    private final String employeeName;

    public Employees(String name) {
        employeeName = name;
    }

    public String lookup(String name, Bank b) {
        return b.facilitateLookup(name);
    }

    public boolean approveLoan(Bank b) {
        b.pendingLoansApproved();
        return true;
    }

    public boolean changeInterestRate(String accType, double amount, Bank b) {
        return false;
    }

    public String seeInternalFund(Bank b) {
        return "You don't have permission for this operation";
    }

    public String getEmployeeName() {
        return employeeName;
    }
}

class ManagingDirector extends Employees {
    public ManagingDirector() {
        super("MD");
    }

    @Override
    public boolean changeInterestRate(String accType, double rate, Bank b) {
        b.deployChangeInInterest(accType, rate);
        return true;
    }

    @Override
    public String seeInternalFund(Bank b) {
        return "Internal fund of Bank " + b.getInternalFund() + "$";
    }
}

class Officer extends Employees {
    public Officer(String name) {
        super(name);
    }
}

class Cashier extends Employees {
    public Cashier(String name) {
        super(name);
    }

    @Override
    public boolean approveLoan(Bank b) {
        return false;
    }
}