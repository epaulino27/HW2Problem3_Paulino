public class PhoneNumberValidator implements Validator{
    //attributes
    private Validator nextValidator;

    //override validation methods
    @Override
    public void setNextValidator(Validator nextValidator){
        //sets the next validator
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(UserRegistration registration) throws ValidationException{
        //defines phone # to be validated
        String phoneNumber = registration.getPhoneNumber();

        // if the phone # is not null, not empty, and does not have 10 digits throw exception
        if (phoneNumber != null && !phoneNumber.isEmpty() && !phoneNumber.matches("\\d{10}")){
            throw new ValidationException("Phone number must be exactly 10 digits, no other symbols should be included.");
        }
        // else pass to next validator
        else if (nextValidator != null){
               nextValidator.validate(registration);
        }
    }
}
