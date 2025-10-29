package dns;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class DnsServerRmi {
    public static void main(String[] args) {
        try {
            DnsServiceImpl instance = new DnsServiceImpl();
            Registry registry = LocateRegistry.getRegistry(8080);
            registry.bind("DnsService", instance);

            System.out.println("[DNS] running at port 8080");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }

    }
}
