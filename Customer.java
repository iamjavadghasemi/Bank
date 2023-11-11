public class Customer extends Person {
    private long telephone;
    public Customer(long userID) {
        super(userID);
    }
    public Customer(long userID, String firstName, String lastName, long password, long telephone) {
        super(userID, firstName, lastName, password);
        this.telephone = telephone;
    }
    public boolean checkOwner(Account account, LinkedList<Account> accounts) {
        Node<Account> node = accounts.getFirst();
        while(node != null) {
            if(node.getElement().equals(account) && node.getElement().getOwner().equals(this)) {
                break;
            }
            node = node.getNext();
        }
        if(node != null) {
            return true;
        } else {
            return false;
        }
    }
    public void removeAll(LinkedList<Account> accounts) throws Exception {
        Node<Account> node = accounts.getFirst();
        while(node != null) {
            if(node.getElement().getOwner().equals(this)) {
                accounts.remove(node.getElement());
            }
            node = node.getNext();
        }
    }
    public void printAll(LinkedList<Account> accounts) throws Exception {
        Node<Account> node = accounts.getFirst();
        int count = 0;
        while(node != null) {
            if(node.getElement().getOwner().equals(this)) {
                count++;
                System.out.println(count+". "+node.getElement().getInformation());
            }
            node = node.getNext();
        }
        if(count == 0) {
            throw new Exception("There is no element.");
        }
    }
    public int numberAll(LinkedList<Account> accounts) {
        Node<Account> node = accounts.getFirst();
        int count = 0;
        while(node != null) {
            if(node.getElement().getOwner().equals(this)) {
                count++;
            }
            node = node.getNext();
        }
        return count;
    }
    @Override
    public String getInformation() {
        return super.getInformation()+" Telephone: "+telephone;
    }
    @Override
    public String toString() {
        return super.toString()+"/"+telephone;
    }
    @Override
    public void showMenu() {
        System.out.println("("+getName()+", Customer)");
        System.out.println("Customer menu:");
        System.out.println("1. Manage your account");
        System.out.println("2. Number of your accounts");
        System.out.println("3. Information of your accounts");
        System.out.println("4. Change your password");
        System.out.println("5. Your information");
        System.out.println("6. Back");
    }
}