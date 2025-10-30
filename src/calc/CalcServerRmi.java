package calc;

import dns.SetDnsClientRmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

public class CalcServerRmi {
    public static void main(String[] args) {
        try {
            CalcServiceImpl instance = new CalcServiceImpl();
            Registry registry = LocateRegistry.createRegistry(8000);
            registry.bind("CalcService", instance);

            System.out.println("[DNS] running at port 8000");
        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }
    }
}
