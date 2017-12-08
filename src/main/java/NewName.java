 import javax.swing.*;
 import java.awt.*;
 import static java.awt.BorderLayout.EAST;
 import static java.awt.BorderLayout.NORTH;
 import static java.awt.BorderLayout.WEST;

public class NewName extends JFrame {

    private static final int DEFAULT_TEXT_FIELD_WIDTH = 10;

    private final JTextField chooseNameTextField = buildInputTextField();

    public NewName() throws Exception{
        super("Chat System");

        JLabel chooseNameLabel = new JLabel("Write new name for you");
        chooseNameLabel.setLabelFor(chooseNameTextField);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> onCancelButtonClicked());

        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(e -> onConfirmButtonClicked());

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

