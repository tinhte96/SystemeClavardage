
import javax.swing.*;
import java.awt.*;
import static java.awt.BorderLayout.*;

public class Login extends JFrame{

        private static final int DEFAULT_TEXT_FIELD_WIDTH = 10;

        private final JTextField chooseNameTextField = buildInputTextField();

        public Login() throws Exception{
            super("Chat System");

            JLabel chooseNameLabel = new JLabel("Choose a name for you");
            chooseNameLabel.setLabelFor(chooseNameTextField);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> this.onCancelButtonClicked());

            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(e -> this.onConfirmButtonClicked());

            Panel formPanel = new Panel();
            formPanel.add(chooseNameLabel);
            formPanel.add(chooseNameTextField);
            formPanel.add(cancelButton);
            formPanel.add(confirmButton);

            add(formPanel, NORTH);
            add(cancelButton, WEST);
            add(confirmButton, EAST);

            chooseNameTextField.requestFocus();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        }

        public void display() {
            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }

        private void onCancelButtonClicked() {

        }

        private void onConfirmButtonClicked() {

        }

        private static JTextField buildInputTextField() {
            return new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
        }
    }



