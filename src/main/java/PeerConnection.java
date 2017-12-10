import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class PeerConnection {

    private final static int PORT_BROADCAST = 8888 ;
    private final static int PORT_P2P = 6666 ;

    private PeerInfo pd;
    private NormalSocket normalSocket;

    private static DatagramSocket datagramSocket = null;

    public PeerConnection(PeerInfo peerInfo) throws IOException{
        this.pd = peerInfo;
    }


    public void sendBroadcast(String broadcastMessage) {
        try {
            datagramSocket = new DatagramSocket();

            datagramSocket.setBroadcast(true);

            byte[] data = broadcastMessage.getBytes();

            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), PORT_BROADCAST);
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
            datagramSocket = new DatagramSocket(PORT_BROADCAST);

            DatagramPacket receivePacket = new DatagramPacket(new byte[64], 64);
            System.out.println("construct");

            datagramSocket.receive(receivePacket);
            System.out.println("receive");
            String name = new String(receivePacket.getData()).trim();
            System.out.println(name);

            String info = pd.getPseudonyme();
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
            socket = new Socket(InetAddress.getLoopbackAddress(), PORT_P2P);
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


}
