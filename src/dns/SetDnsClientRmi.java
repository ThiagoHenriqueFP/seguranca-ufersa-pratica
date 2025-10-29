package dns;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SetDnsClientRmi implements Runnable{
    private String domain;
    private String addr;

    public SetDnsClientRmi(String domain, String addr){
        this.domain = domain;
        this.addr = addr;
    }

    @Override
    public void run() {
        DnsService service;
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 8080);

            service = (DnsService) registry.lookup("DnsService");

            service.setIpAddr(this.addr, this.domain);
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
