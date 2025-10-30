package dns;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DnsServiceImpl extends UnicastRemoteObject implements DnsService {
    private Storage storage = Storage.getInstance();

    protected DnsServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String getIpAddr(String url) throws RemoteException {
        String addr = storage.get(url);
        if (addr == null)
            System.out.println("[DNS] domain not registered");
        else System.out.println("[DNS] ip addr acquired " + addr);
        return addr;
    }

    @Override
    public void setIpAddr(String url, String ipAddr) throws RemoteException {
        this.storage.setStorage(url, ipAddr);
        System.out.println("[DNS] ip registered " +  ipAddr + " from " + url);
    }

    @Override
    public void removeIpAddr(String url) throws RemoteException {
        this.storage.removeStorage(url);
    }

    private void logger() {
        try (ScheduledExecutorService executor = Executors.newScheduledThreadPool(1)) {
            executor.schedule(
                    () -> System.out.println(storage.getStorage()),
                    10, TimeUnit.SECONDS
            );
        }
    }
}
