public class PeerInfo {
    private String id;
    private String pseudonyme;
    private int host;
    private int port;

    public PeerInfo(String id, int host, int port){
        this.id = id;
        this.host = host;
        this.port = port;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setHost(int host){
        this.host = host;
    }

    public void setPort(int port){
        this.port = port;
    }

    public String getId(){
        return this.id;
    }

    public int getHost(){
        return this.host;
    }

    public int getPort(){
        return this.port;
    }

    public String toString(){
        return "id "+ this.id+ " ("+ this.host+ " : "+ this.port+ ")\n";
    }
}
