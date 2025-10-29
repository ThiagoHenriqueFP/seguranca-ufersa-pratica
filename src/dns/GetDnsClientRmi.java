package dns;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GetDnsClientRmi implements Runnable{
    private StringBuilder captor;
    private String domain;

    public GetDnsClientRmi(String domain, StringBuilder captor){
        this.domain = domain;
        this.captor = captor;
    }

    @Override
    public void run() {
        DnsService service;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8080);

            service = (DnsService) registry.lookup("DnsService");

            captor.append(service.getIpAddr(domain));
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
