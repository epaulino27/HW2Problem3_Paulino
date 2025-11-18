public class EmailValidator implements Validator{
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
        //define email to be validated
        String email = registration.getEmail();

        // if the email is null, or it does not follow username@domain.com format
        if (email == null || !email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")){
            //breakdown of regex check:
            // ^ = beginning of string
            // [\w._%+-] = is there an email name?
            // @ = literally make sure there's an @ seperating domain and email name
            // [\w.-] = check if there is a domain name, undefined scope
            // . = literally make sure there's an . seperating domain name and domain extension
            // [a-zA-Z]{2,6} = check it has a domain extension, all letters, between 2 and 6 characters
            // $ = end of string

            // throw a new exception that the email is invalid
            throw new ValidationException("Invalid email format.\nShould be in username@domain.com format.");
        }
        // else pass to the next validator
        else if (nextValidator != null){
                nextValidator.validate(registration);
            }
    }
}
