import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Peer {
    private final static int portBroadcast = 8888 ;

    private String id;
    private String pseudonyme;
    private String host;
    private int port ;
    private ArrayList<Peer> onlinePeers;

    private static DatagramSocket datagramSocket = null;


    public Peer(String id, String host, int port){
        this.id = id;
        this.host = host;
        this.port = port;
        this.onlinePeers = new ArrayList<Peer>();
    }

    public Peer(String id, String pseudonyme, String host, int port){
        this.id = id;
        this.pseudonyme = pseudonyme;
        this.host = host;
        this.port = port;
        this.onlinePeers = new ArrayList<Peer>();
    }



    public void setId(String id){
        this.id = id;
    }

    public void setPseudonyme(String pseudonyme) {this.pseudonyme = pseudonyme;}

    public void setHost(String host){
        this.host = host;
    }

    public void setPort(int port){
        this.port = port;
    }

    public String getId(){
        return this.id;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public String getHost(){
        return this.host;
    }

    public int getPort(){
        return this.port;
    }

    public ArrayList<Peer> getOnlinePeers() {
        return onlinePeers;
    }

    public void addPeer(Peer peer) {
        this.onlinePeers.add(peer);
    }

    //TODO broadcast messages
    public void sendBroadcast(String broadcastMessage) throws Exception{
        datagramSocket = new DatagramSocket();
        datagramSocket.setBroadcast(true);

        byte[] data = broadcastMessage.getBytes();

        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), 4455);
        datagramSocket.send(packet);

        datagramSocket.close();
    }

    //TODO receive
    public void receiveBroadcast() throws Exception {
        datagramSocket = new DatagramSocket();

        DatagramPacket receivePacket = null;
        datagramSocket.receive(receivePacket);

        String info = "User";

        byte[] buffer = info.getBytes();

        DatagramPacket sendPacket = new DatagramPacket(buffer, 0, receivePacket.getAddress(), receivePacket.getPort());
        datagramSocket.send(sendPacket);

        datagramSocket.close();
    }


    public String toString(){
        return "id "+ this.id+ " ("+ this.host+ " : "+ this.port+ ")\n";
    }
}
