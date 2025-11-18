public class UserRegistration {
    //Attributes
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    // Constructor
    public UserRegistration(String username, String password, String email, String phoneNumber){
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // returns the username
    public String getUsername(){
        return username;
    }

    // returns the password
    public String getPassword(){
        return password;
    }

    // returns the email
    public String getEmail(){
        return email;
    }

    // returns the phone number
    public String getPhoneNumber(){
        return phoneNumber;
    }
}