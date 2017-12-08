import java.io.IOException;
import java.net.Socket;

public abstract class SocketFactory {
    private static SocketFactory currentFactory = new NormalSocketFactory();

    public static SocketFactory getSocketFactory(){
        return currentFactory;
    }

    public static void setCurrentFactory(SocketFactory sf){
        if (sf == null){
            throw new NullPointerException("Attempting to set null socket factory");
        }
        currentFactory = sf;
    }

    public abstract SocketInterface makeSocket(String host, int port) throws IOException;
    public abstract SocketInterface makeSocket(Socket socket) throws IOException;
}
