package dns;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DnsServiceImpl extends UnicastRemoteObject implements DnsService {
    private Storage storage = Storage.getInstance();

    protected DnsServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String getIpAddr(String url) {
        String addr = storage.get(url);
        if (addr == null)
            System.out.println("[DNS] domain not registered");
        else System.out.println("[DNS] ip addr acquired " + addr);
        return addr;
    }

    @Override
    public void setIpAddr(String url, String ipAddr) {
        this.storage.setStorage(url, ipAddr);
    }
}
