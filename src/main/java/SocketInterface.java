import java.io.IOException;

public interface SocketInterface {

    public void write(byte[] b) throws IOException;
    public void read() throws IOException;
    public void read(byte[] b) throws IOException;
    public void close() throws IOException;
}
