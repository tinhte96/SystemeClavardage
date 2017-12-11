public class App {

    public static void main (String[] args) throws Exception {

        Node thisNode = new Node();
        Thread bh = new Thread(new BroadcastHandler(thisNode));
        Login ui = new Login();
        ui.display();
        System.out.println("bh start");
        bh.start();

    }
}
