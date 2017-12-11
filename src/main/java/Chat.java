import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class Chat extends JFrame {

    private JTextArea chatWindow;
    private JTextField messageTextField = null;
    private SimpleDateFormat theDate;

    public Chat() {
        super("Chat System");
        messageTextField = new JTextField();
        messageTextField.setEditable(false);

        messageTextField.addActionListener(event -> {
            sendMessage(event.getActionCommand());
            messageTextField.setText("");
        });

        add(messageTextField, BorderLayout.SOUTH);

        chatWindow = new JTextArea();
        add(new JScrollPane(chatWindow));
        setSize(300,250);

        JButton sendPictureButton = new JButton("Send Picture");
        sendPictureButton.addActionListener(e -> onSendPictureButtonClicked());

        JButton sendFileButton = new JButton("Send File");
        sendFileButton.addActionListener(e -> onSendFileButtonClicked());

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> onSendButtonClicked());

        JButton closeDialogueButton = new JButton("Close Dialogue");
        closeDialogueButton.addActionListener(e -> onCloseDialogueButtonClicked());

        JPanel buttonPane = buildJPanelWith(sendPictureButton, sendFileButton, sendButton, closeDialogueButton);
        add(buttonPane, BorderLayout.PAGE_END);

        this.getRootPane().setDefaultButton(sendButton);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        messageTextField.requestFocus();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private static JPanel buildJPanelWith(JButton sendPictureButton, JButton sendFileButton, JButton SendButton,
                                          JButton closeDialogueButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(sendPictureButton);
        buttonPane.add(sendFileButton);
        buttonPane.add(SendButton);
        buttonPane.add(closeDialogueButton);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void sendMessage(String message){
        try{
//            output.writeObject("SERVER-"+message);
//            output.flush();
        }catch(Exception e){
            chatWindow.append("\n error in sending message from server side");
        }
    }

    private void onCloseDialogueButtonClicked() {
    }

    private void onSendButtonClicked() {
    }

    private void onSendFileButtonClicked() {
    }

    private void onSendPictureButtonClicked() {
    }

}