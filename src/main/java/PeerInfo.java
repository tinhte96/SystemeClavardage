import org.omg.CORBA.DataOutputStream;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PeerInfo {

    private String pseudonyme;
    private String host;

    public PeerInfo(String pseudonyme, String host){
        this.pseudonyme = pseudonyme;
        this.host = host;
    }

    public void setPseudonyme(String pseudonyme) {this.pseudonyme = pseudonyme;}

    public void setHost(String host){
        this.host = host;
    }

    public String getPseudonyme() {
        return pseudonyme;
    }

    public String getHost(){
        return this.host;
    }

    public String toString(){
        return "pseudo "+this.pseudonyme+ " ("+ this.host +")\n";
    }
}
