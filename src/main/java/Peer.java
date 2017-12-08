import org.omg.CORBA.DataOutputStream;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Peer {
    private final static int portBroadcast = 8888 ;
    private final static int port = 6666 ;

    private String id;
    private String pseudonyme;
    private InetAddress host;
    private ArrayList<Peer> onlinePeers;

    private static DatagramSocket datagramSocket = null;

    public Peer(String id, InetAddress host){
        this.id = id;
        this.host = host;
        this.onlinePeers = new ArrayList<Peer>();
    }

    public Peer(String id, String pseudonyme, InetAddress host){
        this.id = id;
        this.pseudonyme = pseudonyme;
        this.host = host;
        this.onlinePeers = new ArrayList<Peer>();
    }

    public void setId(String id){
        this.id = id;
    }

    public void setPseudonyme(String pseudonyme) {this.pseudonyme = pseudonyme;}

    public void setHost(InetAddress host){
        this.host = host;
    }


    public String getId(){
        return this.id;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public InetAddress getHost(){
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
    public void sendBroadcast(String broadcastMessage) {
        try {
            datagramSocket = new DatagramSocket();

            datagramSocket.setBroadcast(true);

            byte[] data = broadcastMessage.getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), portBroadcast);
            datagramSocket.send(packet);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

    }

    //TODO receive
    public void receiveBroadcast() {
        System.out.println("appel receive broadcast");
        try {
            datagramSocket = new DatagramSocket(portBroadcast);

            DatagramPacket receivePacket = new DatagramPacket(new byte[64], 64);
            System.out.println("construct");

            datagramSocket.receive(receivePacket);
            System.out.println("receive");
            String name = new String(receivePacket.getData()).trim();
            System.out.println(name);

            String info = this.pseudonyme;
            byte[] buffer = info.getBytes();
            System.out.println("send ack");

            DatagramPacket ack = new DatagramPacket(buffer, buffer.length, receivePacket.getAddress(), receivePacket.getPort());
            datagramSocket.send(ack);
            System.out.println("ack send ok");
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }

    public void sendMessage(String message, InetAddress ip, int port){
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            pw.println(message);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    public void receiveMessage() {
        Socket socket = null;

        try {
            socket = new Socket(InetAddress.getLoopbackAddress(), port);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            String message = reader.readLine();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void sendFile(File file, InetAddress ip, int port){
        try {
            datagramSocket = new DatagramSocket();
            byte[] sendBuffer = new byte[1024];
            int size = 0;

            DatagramPacket sendDatagram = new DatagramPacket(sendBuffer, 0, ip, port);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

            while ((size = bis.read(sendBuffer)) > 0){

                sendDatagram.setData(sendBuffer, 0, size);
                datagramSocket.send(sendDatagram);

                TimeUnit.MICROSECONDS.sleep(10);
            }

            bis.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (datagramSocket != null){
                datagramSocket.close();
            }
        }
    }

    public void receiveFile() {

        try {
            datagramSocket = new DatagramSocket();
            DatagramPacket receiveDatagram = null;

            String fileName = "";

            FileOutputStream fos = new FileOutputStream(fileName);
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                receiveDatagram = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                datagramSocket.receive(receiveDatagram);
                fos.write(receiveBuffer, 0, receiveDatagram.getLength());
                fos.flush();

                if (receiveDatagram.getLength() == 0){
                    break;
                }

            }

            fos.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (datagramSocket != null) {
                datagramSocket.close();
            }

        }
    }

    public String toString(){
        return "id "+ this.id+ " ("+ this.host+ " : "+ this.port+ ")\n";
    }
}
