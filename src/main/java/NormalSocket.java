import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NormalSocket implements SocketInterface {

    private Socket s;
    private InputStream is;
    private OutputStream os;

    public NormalSocket (InetAddress host, int port) throws IOException {
        this(new Socket(host,port));
    }

    public NormalSocket(Socket socket) throws IOException {
        s = socket;
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public void write(byte[] b) throws IOException {
        os.write(b);
        os.flush();
    }

    public int read() throws IOException {
        return is.read();
    }

    public int read(byte[] b) throws IOException {
        return is.read(b);
    }

    public void close() throws IOException {
        s.close();
        is.close();
        os.close();
    }
}
