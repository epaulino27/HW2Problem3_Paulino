public class PasswordValidator implements Validator{
    //attributes
    private Validator nextValidator;

    //overide validator methods
    @Override
    public void setNextValidator(Validator nextValidator){
        //set next validator
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(UserRegistration registration) throws ValidationException{
        //define password to be validated
        String password = registration.getPassword();

        //if password is not valid (null, less than 8 characters, doesn't have: 1 upper, 1 lower, 1 #) throw error
        if (password.length() <8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*[0-9].*")) {
            throw new ValidationException("Password must be 8 characters, including:\n1 Uppercase\n1 Lowercase\n1 Digit");
        }
        //else pass to the next validator
        else if (nextValidator != null){
            nextValidator.validate(registration);
        }
    }
}
