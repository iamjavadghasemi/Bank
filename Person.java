public abstract class Person {
    private long userID;
    private String firstName;
    private String lastName;
    private long password;
    public Person(long userID) {
        this.userID = userID;
    }
    public Person(long userID, String firstName, String lastName, long password) {
        this(userID);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    public long getUserID() {
        return userID;
    }
    public String getName() {
        return firstName+" "+lastName;
    }
    public void setPassword(long password) {
        this.password = password;
    }
    public long getPassword() {
        return password;
    }
    public String getInformation() {
        return "UserID: "+userID+" Name: "+getName()+" Password: "+password;
    }
    @Override
    public String toString() {
        return userID+"/"+firstName+"/"+lastName+"/"+password;
    }
    @Override
    public boolean equals(Object object) {
        Person person = (Person) object;
        return this.userID == person.userID;
    }
    public void showMenu() {
        System.out.println("5. Number of customers");
        System.out.println("6. Information of customers");
        System.out.println("7. Number of accounts");
        System.out.println("8. Information of accounts");
        System.out.println("9. Change your password");
        System.out.println("10. Your information");
        System.out.println("11. Back");
    }
}