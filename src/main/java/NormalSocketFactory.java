import java.io.IOException;
import java.net.Socket;

public class NormalSocketFactory extends SocketFactory {

    public SocketInterface makeSocket(String host, int port) throws IOException {
        return new NormalSocket(host,port);
    }

    public SocketInterface makeSocket(Socket socket) throws IOException {
        return new NormalSocket(socket);
    }
}
