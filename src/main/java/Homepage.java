import javax.swing.*;
import java.awt.*;

public class Homepage extends JFrame{

    private final JList<String> list;
    private final DefaultListModel<String> listModel;
    private Node thisNode;

    public Homepage(Node node) {
        super("Chat System");
        thisNode = node;


        this.list = buildJList();
        this.listModel = new DefaultListModel<>();
        list.setModel(listModel);

        for (int i = 0; i < node.onlinePeerInfos.size(); i++) {
            listModel.addElement("User1");
        }
        list.addListSelectionListener(e -> updateOnLineButtonState());
        JScrollPane listScrollPane = new JScrollPane(this.list);

        JButton startChatButton = new JButton("Start Chat");
        startChatButton.addActionListener(e -> onStartChatButtonClicked());

        JButton changeNameButton = new JButton("Change Name");
        changeNameButton.addActionListener(e -> onChangeNameButtonClicked());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> onLogoutButtonClicked());

        JPanel buttonPane = buildJPanelWith(startChatButton, changeNameButton, logoutButton);

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void updateOnLineButtonState() {
    }

    private static JList<String> buildJList() {
        JList<String> list = new JList<>();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(5);
        return list;
    }

    private static JPanel buildJPanelWith(JButton startChatButton, JButton changeNameButton, JButton LogoutButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

        buttonPane.add(startChatButton);
        buttonPane.add(changeNameButton);
        buttonPane.add(LogoutButton);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return buttonPane;
    }

    public void display() {
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void onLogoutButtonClicked() {

    }

    private void onChangeNameButtonClicked() {

        try {
            NewName newName = new NewName();
            newName.display();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void onStartChatButtonClicked() {

    }


}