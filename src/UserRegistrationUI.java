import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationUI extends JFrame{
    private JTextArea outputArea;

    public UserRegistrationUI(){
        setTitle("User Registration Demo using the Chain of Responsibility Design Pattern via JavaSwing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        outputArea = new JTextArea(5, 50);
        outputArea.setEditable(false);
        outputArea.setBorder(BorderFactory.createTitledBorder("Event Output"));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Added a title for the Frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel title = new JLabel("User Registration");
        Font bigFont = new Font("Serif", Font.BOLD, 20);
        title.setFont(bigFont);
        add(title);


        // Add a Username field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Username:"), gbc);
        JTextField usernameField = new JTextField(20);
        gbc.gridx = 1;
        add(usernameField, gbc);

        // Add a Password field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Password:"), gbc);
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Add an Email address field
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Email:"), gbc);
        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Add a Phone number field
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Phone number (optional):"), gbc);
        JTextField phoneNumberField = new JTextField(20);
        gbc.gridx = 1;
        add(phoneNumberField, gbc);

        // Add a Register button field
        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton registerButton = new JButton("Register");
        gbc.gridx = 1;
        add(registerButton, gbc);
        registerButton.addActionListener(new ActionListener() {
            // if the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                // call the registerUser method
                registerUser(usernameField, passwordField,emailField,phoneNumberField);

            }
        });

    }

    // Allows the checks registration
    public void registerUser(JTextField usernameField, JTextField passwordField, JTextField emailField, JTextField phoneNumberField){
        // create a new JFrame
        JFrame valid = new JFrame("Confirmation");
        valid.setSize(400, 300);
        valid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        valid.setLocationRelativeTo(null);

        // create a new user and get their credentials based on the fields they typed in
        UserRegistration user = new UserRegistration(
                usernameField.getText(),
                passwordField.getText(),
                emailField.getText(),
                phoneNumberField.getText()
        );

        // set up the validator chain
        Validator validatorChain = setUpValidatorChain();

        try{
            // start validating the credentials
            validatorChain.validate(user);

            // if everything is fine
            JLabel passed = new JLabel("Your information has been fully validated!");
            Font bigFont = new Font("Serif", Font.BOLD, 20);
            passed.setFont(bigFont);
            valid.add(passed);
            valid.setVisible(true);

        } catch (ValidationException ex) {
            // one or more of the credentials were incorrect so we instead show an error message
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Validator setUpValidatorChain(){
        // creating the validators
        Validator usernameValidator = new UsernameValidator();
        Validator passwordValidator = new PasswordValidator();
        Validator emailValidator = new EmailValidator();
        Validator phoneNumberValidator = new PhoneNumberValidator();

        // setting up the validators
        usernameValidator.setNextValidator(passwordValidator);
        passwordValidator.setNextValidator(emailValidator);
        emailValidator.setNextValidator(phoneNumberValidator);

        // returning the first validator
        return usernameValidator;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            UserRegistrationUI demo = new UserRegistrationUI();
            demo.setVisible(true);
        });
    }
}
