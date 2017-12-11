import java.io.*;
import java.net.*;

public class BroadcastHandler implements Runnable {

    private Node thisNode;
    private DatagramSocket datagramSocket;
    private DatagramPacket receivePacket;


    public BroadcastHandler(Node thisNode) throws SocketException {
        this.thisNode = thisNode;
        this.datagramSocket = new DatagramSocket();
        this.receivePacket = new DatagramPacket(new byte[64],64);
    }


    public void run(){
        try {
            System.out.printf("try run");
            datagramSocket = new DatagramSocket(PeerConnection.PORT_BROADCAST);
            datagramSocket.receive(receivePacket);
            System.out.println("stocker dans variable data");
            String psedonyme = new String(receivePacket.getData()).trim();
            PeerInfo pI = new PeerInfo (psedonyme,"localhost");
            thisNode.setMyPeer(pI);
            System.out.printf(thisNode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
