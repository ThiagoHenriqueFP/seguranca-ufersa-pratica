package dns;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DnsService extends Remote {
    String getIpAddr(String url) throws RemoteException;

    void setIpAddr(String url, String ipAddr) throws RemoteException;

    void removeIpAddr(String url) throws RemoteException;
}
