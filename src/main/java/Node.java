import java.util.ArrayList;
import java.util.Hashtable;

public class Node {

    private PeerInfo myPeer;
    private Hashtable<String, PeerInfo> onlinePeerInfos;
    private Hashtable<String, HandlerInterface> handlers;


    public void setMyPeer(PeerInfo peerInfo){
        myPeer = peerInfo;
    }

    @Override
    public String toString() {
        return "this node "+myPeer.toString();
    }
}
