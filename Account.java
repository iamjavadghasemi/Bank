public class Account {
    private long accountID;
    private long balance;
    private String[] history = new String[10];
    private int indexHistory = 0;
    private Customer owner;
    public Account(long accountID) {
        this.accountID = accountID;
    }
    public Account(long accountID, long balance, Customer owner) throws Exception {
        if(balance >= 0) {
            this.accountID = accountID;
            this.balance = balance;
            this.owner = owner;
        } else {
            throw new Exception("Balance is invalid.");
        }
    }
    public long getAccountID() {
        return accountID;
    }
    public long getBalance() {
        return balance;
    }
    public void deposit(long amount) {
        balance+=amount;
        System.out.println("Process is successfully!");
    }
    public void receive(long amount) throws Exception {
        if(amount <= 500) {
            if(balance-amount >= 0) {
                balance-=amount;
                System.out.println("Process is successfully!");
            } else {
                throw new Exception("Balance is not enough.");
            }
        } else {
            throw new Exception("Amount must be less than 500.");
        }
    }
    public void transfer(Account account, long amount) throws Exception {
        if(this.balance-amount >= 0) {
            this.balance-=amount;
            account.balance+=amount;
            System.out.println("Process is successfully!");
        } else {
            throw new Exception("Balance is not enough.");
        }
    }
    public void setHistory(String history) {
        if(history.length() > 0 && history != "There is no transfer.") {
            this.history[indexHistory] = history;
        }
        if(indexHistory == 9) {
            indexHistory = 0;
        } else {
            indexHistory++;
        }
    }
    public String getHistory() {
        String history = "";
        for(int i = indexHistory; i >= 0; i--) {
            if(this.history[i] != null && this.history[i] != "There is no transfer.") {
                history+=this.history[i]+", ";
            }
        }
        if(indexHistory != 9 && this.history[9] != null) {
            for(int i = 9; i > indexHistory; i--) {
                if(this.history[i] != null && this.history[i] != "There is no transfer.") {
                    history+=this.history[i]+", ";
                }
            }
        }
        return history;
    }
    public void setIndexHistory(int indexHistory) {
        this.indexHistory = indexHistory;
    }
    public Customer getOwner() {
        return owner;
    }
    public String getInformation() {
        return "AccountID: "+accountID+" Balance: "+balance+" History: "+getHistory()+" Owner's userID: "+owner.getUserID();
    }
    @Override
    public String toString() {
        return accountID+"/"+balance+"/"+getHistory()+"/"+indexHistory+"/"+owner.getUserID();
    }
    @Override
    public boolean equals(Object object) {
        Account account = (Account) object;
        return this.accountID == account.accountID;
    }
    public void showMenu() {
        System.out.println("("+accountID+", Account)");
        System.out.println("Account menu:");
        System.out.println("1. Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Receive");
        System.out.println("4. Transfer");
        System.out.println("5. History");
        System.out.println("6. Information of account");
        System.out.println("7. Back");
    }
}