import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File folder = new File("D:\\Bank Database");
        File fileManager = new File("D:\\Bank Database\\Manager.txt");
        File fileEmployee = new File("D:\\Bank Database\\Employee.txt");
        File fileCustomer = new File("D:\\Bank Database\\Customer.txt");
        File fileAccount = new File("D:\\Bank Database\\Account.txt");
        try {
            folder.mkdir();
            fileManager.createNewFile();
            fileEmployee.createNewFile();
            fileCustomer.createNewFile();
            fileAccount.createNewFile();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        LinkedList<Manager> managers = new LinkedList<>();
        LinkedList<Employee> employees = new LinkedList<>();
        LinkedList<Customer> customers = new LinkedList<>();
        LinkedList<Account> accounts = new LinkedList<>();
        managers.load(fileManager, customers);
        employees.load(fileEmployee, customers);
        customers.load(fileCustomer, customers);
        accounts.load(fileAccount, customers);
        System.out.println("Welcome!");
        if(managers.getLength() == 0) {
            System.out.println("Create manager:");
            System.out.println("Enter manager's first name>>>");
            String firstName = input.next();
            System.out.println("Enter manager's last name>>>");
            String lastName = input.next();
            System.out.println("Enter a number for manager's password>>>");
            long password = scanLong();
            Manager manager = new Manager(10000000, firstName, lastName, password);
            managers.insertToLast(manager);
            managers.save(fileManager);
            System.out.println("Manager added.");
            System.out.println("Manager's userID: "+manager.getUserID());
        }
        int numberMainMenu;
        do {
            showMainMenu();
            System.out.println("Enter a number>>");
            numberMainMenu = scanInt();
            switch(numberMainMenu) {
                case 1: {
                    try {
                        System.out.println("Enter your userID>>>");
                        long userID = scanLong();
                        System.out.println("Enter your password>>>");
                        long password = scanLong();
                        int state;
                        try {
                            if(managers.find(new Manager(userID)) != null && managers.find(new Manager(userID)).getPassword() == password) {
                                state = 1;
                            } else {
                                throw new Exception();
                            }
                        } catch(Exception exception1) {
                            try {
                                if(employees.find(new Employee(userID)) != null && employees.find(new Employee(userID)).getPassword() == password) {
                                    state = 2;
                                } else {
                                    throw new Exception();
                                }
                            } catch(Exception exception2) {
                                if(customers.find(new Customer(userID)) != null && customers.find(new Customer(userID)).getPassword() == password) {
                                    state = 3;
                                } else {
                                    throw new Exception();
                                }
                            }
                        }
                        switch(state) {
                            case 1: {
                                int numberManagerMenu;
                                do {
                                    Manager manager = managers.find(new Manager(userID));
                                    manager.showMenu();
                                    System.out.println("Enter a number>>>");
                                    numberManagerMenu = scanInt();
                                    switch(numberManagerMenu) {
                                        case 1: {
                                            System.out.println("Enter employee's first name>>>");
                                            String firstName = input.next();
                                            System.out.println("Enter employee's last name>>>");
                                            String lastName = input.next();
                                            System.out.println("Enter a number for employee's password>>>");
                                            long passWord = scanLong();
                                            Employee employee = new Employee(11000000+employees.getCounter(), firstName, lastName, passWord);
                                            employees.insertToLast(employee);
                                            employees.save(fileEmployee);
                                            System.out.println("Employee added.");
                                            System.out.println("Employee's userID: "+employee.getUserID());
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Enter employee's userID>>>");
                                            try {
                                                employees.remove(new Employee(scanLong()));
                                                employees.save(fileEmployee);
                                                System.out.println("Employee deleted.");
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 3: {
                                            System.out.println("Number of employees: "+employees.getLength());
                                            break;
                                        }
                                        case 4: {
                                            try {
                                                employees.print();
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 5: {
                                            System.out.println("Number of customers: "+customers.getLength());
                                            break;
                                        }
                                        case 6: {
                                            try {
                                                customers.print();
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 7: {
                                            System.out.println("Number of accounts: "+accounts.getLength());
                                            break;
                                        }
                                        case 8: {
                                            try {
                                                accounts.print();
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 9: {
                                            System.out.println("Enter your old password>>>");
                                            long oldPassword = scanLong();
                                            if(oldPassword == manager.getPassword()) {
                                                System.out.println("Enter your new password>>>");
                                                long newPassword = scanLong();
                                                manager.setPassword(newPassword);
                                                System.out.println("Password changed.");
                                            } else {
                                                System.out.println("Password is incorrect.");
                                            }
                                            break;
                                        }
                                        case 10: {
                                            System.out.println(manager.getInformation());
                                            break;
                                        }
                                        case 11: {
                                            System.out.println("Goodbye!");
                                            break;
                                        }
                                        default: {
                                            System.out.println("Number is invalid.");
                                        }
                                    }
                                } while(numberManagerMenu != 11);
                                break;
                            }
                            case 2: {
                                int numberEmployeeMenu;
                                do {
                                    Employee employee = employees.find(new Employee(userID));
                                    employee.showMenu();
                                    System.out.println("Enter a number>>>");
                                    numberEmployeeMenu = scanInt();
                                    switch(numberEmployeeMenu) {
                                        case 1: {
                                            System.out.println("Enter customer's first name>>>");
                                            String firstName = input.next();
                                            System.out.println("Enter customer's last name>>>");
                                            String lastName = input.next();
                                            System.out.println("Enter a number for customer's password>>>");
                                            long passWord = scanLong();
                                            System.out.println("Enter customer's telephone>>>");
                                            long telephone = scanLong();
                                            Customer customer = new Customer(12000000+customers.getCounter(), firstName, lastName, passWord, telephone);
                                            customers.insertToLast(customer);
                                            customers.save(fileCustomer);
                                            System.out.println("Customer added.");
                                            System.out.println("Customer's userID: "+customer.getUserID());
                                            break;
                                        }
                                        case 2: {
                                            try {
                                                System.out.println("Enter customer's userID>>>");
                                                Customer customer = new Customer(scanLong());
                                                customer.removeAll(accounts);
                                                customers.remove(customer);
                                                accounts.save(fileAccount);
                                                customers.save(fileCustomer);
                                                System.out.println("Customer deleted.");
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 3: {
                                            try {
                                                System.out.println("Enter balance of account>>>");
                                                long balance = scanLong();
                                                System.out.println("Enter owner's userID of account>>>");
                                                Account account = new Account(98000000+accounts.getCounter(), balance, customers.find(new Customer(scanLong())));
                                                accounts.insertToLast(account);
                                                accounts.save(fileAccount);
                                                System.out.println("Account added.");
                                                System.out.println("accountID: "+account.getAccountID());
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Enter accountID>>>");
                                            try {
                                                accounts.remove(new Account(scanLong()));
                                                accounts.save(fileAccount);
                                                System.out.println("Account deleted.");
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 5: {
                                            System.out.println("Number of customers: "+customers.getLength());
                                            break;
                                        }
                                        case 6: {
                                            try {
                                                customers.print();
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 7: {
                                            System.out.println("Number of accounts: "+accounts.getLength());
                                            break;
                                        }
                                        case 8: {
                                            try {
                                                accounts.print();
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 9: {
                                            System.out.println("Enter your old password>>>");
                                            long oldPassword = scanLong();
                                            if(oldPassword == employee.getPassword()) {
                                                System.out.println("Enter your new password>>>");
                                                long newPassword = scanLong();
                                                employee.setPassword(newPassword);
                                                System.out.println("Password changed.");
                                            } else {
                                                System.out.println("Password is incorrect.");
                                            }
                                            break;
                                        }
                                        case 10: {
                                            System.out.println(employee.getInformation());
                                            break;
                                        }
                                        case 11: {
                                            System.out.println("Goodbye!");
                                            break;
                                        }
                                        default: {
                                            System.out.println("Number is invalid.");
                                        }
                                    }
                                } while(numberEmployeeMenu != 11);
                                break;
                            }
                            case 3: {
                                int numberCustomerMenu;
                                do {
                                    Customer customer = customers.find(new Customer(userID));
                                    customer.showMenu();
                                    System.out.println("Enter a number>>>");
                                    numberCustomerMenu = scanInt();
                                    switch(numberCustomerMenu) {
                                        case 1: {
                                            try {
                                                System.out.println("Enter accountID of your account>>>");
                                                long accountID = scanLong();
                                                if(customer.checkOwner(accounts.find(new Account(accountID)), accounts)) {
                                                    int numberAccountMenu;
                                                    do {
                                                        Account account = accounts.find(new Account(accountID));
                                                        account.showMenu();
                                                        System.out.println("Enter a number>>>");
                                                        numberAccountMenu = scanInt();
                                                        switch(numberAccountMenu) {
                                                            case 1: {
                                                                System.out.println("Balance: "+account.getBalance());
                                                                break;
                                                            }
                                                            case 2: {
                                                                System.out.println("Enter amount of deposit>>>");
                                                                long amount = scanLong();
                                                                account.deposit(amount);
                                                                account.setHistory("Deposit: +"+amount);
                                                                accounts.save(fileAccount);
                                                                break;
                                                            }
                                                            case 3: {
                                                                try {
                                                                    System.out.println("Enter amount of receive>>>");
                                                                    long amount = scanLong();
                                                                    account.receive(amount);
                                                                    account.setHistory("Receive: -"+amount);
                                                                    accounts.save(fileAccount);
                                                                } catch(Exception exception) {
                                                                    System.out.println(exception.getMessage());
                                                                }
                                                                break;
                                                            }
                                                            case 4: {
                                                                try {
                                                                    System.out.println("Enter amount of transfer>>>");
                                                                    long amount = scanLong();
                                                                    System.out.println("Enter accountID of destination account>>>");
                                                                    long accID = scanLong();
                                                                    Account destination = accounts.find(new Account(accID));
                                                                    if(destination != null) {
                                                                        account.transfer(destination, amount);
                                                                        account.setHistory("Transfer: -"+amount);
                                                                        destination.setHistory("Transfer: +"+amount);
                                                                        accounts.save(fileAccount);
                                                                    } else {
                                                                        System.out.println("AccountID of destination account is invalid.");
                                                                    }
                                                                } catch(Exception exception) {
                                                                    System.out.println(exception.getMessage());
                                                                }
                                                                break;
                                                            }
                                                            case 5: {
                                                                if(account.getHistory() != "") {
                                                                    System.out.println(account.getHistory());
                                                                } else {
                                                                    System.out.println("There is no history.");
                                                                }
                                                                break;
                                                            }
                                                            case 6: {
                                                                System.out.println(account.getInformation());
                                                                break;
                                                            }
                                                            case 7: {
                                                                System.out.println("Loading...");
                                                                break;
                                                            }
                                                            default: {
                                                                System.out.println("Number is invalid.");
                                                            }
                                                        }
                                                    } while(numberAccountMenu != 7);
                                                } else {
                                                    System.out.println("There is no such member.");
                                                }
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("Number of your accounts: "+customer.numberAll(accounts));
                                            break;
                                        }
                                        case 3: {
                                            try {
                                                customer.printAll(accounts);
                                            } catch(Exception exception) {
                                                System.out.println(exception.getMessage());
                                            }
                                            break;
                                        }
                                        case 4: {
                                            System.out.println("Enter your old password>>>");
                                            long oldPassword = scanLong();
                                            if(oldPassword == customer.getPassword()) {
                                                System.out.println("Enter your new password>>>");
                                                long newPassword = scanLong();
                                                customer.setPassword(newPassword);
                                                System.out.println("Password changed.");
                                            } else {
                                                System.out.println("Password is incorrect.");
                                            }
                                            break;
                                        }
                                        case 5: {
                                            System.out.println(customer.getInformation());
                                            break;
                                        }
                                        case 6: {
                                            System.out.println("Goodbye!");
                                            break;
                                        }
                                        default: {
                                            System.out.println("Number is invalid.");
                                        }
                                    }
                                } while(numberCustomerMenu != 6);
                                break;
                            }
                        }
                    } catch(Exception exception) {
                        System.out.println("UserID or password is invalid.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Good luck!");
                    break;
                }
                default: {
                    System.out.println("Number is invalid.");
                }
            }
        } while(numberMainMenu != 2);
    }
    public static int scanInt() {
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                return input.nextInt();
            } catch(InputMismatchException exception) {
                System.out.println("Input is invalid.");
                System.out.println("Re-enter>>>");
                input.nextLine();
            }
        }
    }
    public static long scanLong() {
        Scanner input = new Scanner(System.in);
        while(true) {
            try {
                return input.nextLong();
            } catch(InputMismatchException exception) {
                System.out.println("Input is invalid.");
                System.out.println("Re-enter>>>");
                input.nextLine();
            }
        }
    }
    public static void showMainMenu() {
        System.out.println("Main menu:");
        System.out.println("1. Login");
        System.out.println("2. Exit");
    }
}