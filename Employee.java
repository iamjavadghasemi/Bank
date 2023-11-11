public class Employee extends Person {
    public Employee(long userID) {
        super(userID);
    }
    public Employee(long userID, String firstName, String lastName, long password) {
        super(userID, firstName, lastName, password);
    }
    @Override
    public void showMenu() {
        System.out.println("("+getName()+", Employee)");
        System.out.println("Employee menu:");
        System.out.println("1. Add customer");
        System.out.println("2. Delete customer");
        System.out.println("3. Add account");
        System.out.println("4. Delete account");
        super.showMenu();
    }
}