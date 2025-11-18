public class UsernameValidator implements Validator{
    //attributes
    private Validator nextValidator;

    //override validator methods
    @Override
    public void setNextValidator(Validator nextValidator){
        // sets the next validator
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(UserRegistration registration) throws ValidationException{
        //define username to validate
        String username = registration.getUsername();

        // if username is null or less than 5 characters long throw error
        if (username == null || username.length() < 5){
            // throw an exception that the username needs to be longer
            throw new ValidationException("Username must be over 5 characters");
        }
        //else pass to next validator
        else if (nextValidator != null) {
            nextValidator.validate(registration);
        }
    }
}
