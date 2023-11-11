public class Manager extends Person {
    public Manager(long userID) {
        super(userID);
    }
    public Manager(long userID, String firstName, String lastName, long password) {
        super(userID, firstName, lastName, password);
    }
    @Override
    public void showMenu() {
        System.out.println("("+getName()+", Manager)");
        System.out.println("Manager menu:");
        System.out.println("1. Add employee");
        System.out.println("2. Delete employee");
        System.out.println("3. Number of employees");
        System.out.println("4. Information of employees");
        super.showMenu();
    }
}