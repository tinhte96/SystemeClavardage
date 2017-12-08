
import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.awt.BorderLayout.*;

public class Login extends JFrame{

        private static final int DEFAULT_TEXT_FIELD_WIDTH = 10;

        private final JTextField nameTextField = buildInputTextField();

        public Login() throws Exception{
            super("Chat System");

            JLabel chooseNameLabel = new JLabel("Choose a name for you");
            chooseNameLabel.setLabelFor(nameTextField);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> this.onCancelButtonClicked());

            JButton confirmButton = new JButton("Confirm");

            confirmButton.addActionListener(e -> {
                try {
                    this.onConfirmButtonClicked(this.nameTextField.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            Panel formPanel = new Panel();
            formPanel.add(chooseNameLabel);
            formPanel.add(nameTextField);
            formPanel.add(cancelButton);
            formPanel.add(confirmButton);

            add(formPanel, NORTH);
            add(cancelButton, WEST);
            add(confirmButton, EAST);

            nameTextField.requestFocus();
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        }

        public void display() {
            this.pack();
            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }

        private void onCancelButtonClicked() {

        }

        private void onConfirmButtonClicked(String name) throws Exception {
            Peer user = new Peer(name, name, InetAddress.getByName("127.0.0.1"));
            System.out.println("Test construct");

            user.sendBroadcast(user.getPseudonyme());
            System.out.println("Test broadcast");

            user.receiveBroadcast();
            System.out.println("Test receive");

            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        private static JTextField buildInputTextField() {
            return new JTextField(DEFAULT_TEXT_FIELD_WIDTH);
        }
    }



