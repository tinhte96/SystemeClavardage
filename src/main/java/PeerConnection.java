import java.io.IOException;

public class PeerConnection {

    private Peer pd;
    private NormalSocket s;

    public PeerConnection (Peer peer) throws IOException {
        pd = peer;
        s = new NormalSocket(peer.getHost(), peer.getPort());
    }
}
