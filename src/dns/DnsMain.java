package dns;

public class DnsMain {
    public static void main(String[] args) {
        new SetDnsClientRmi("domain1.com", "10.0.0.1").run();
        new SetDnsClientRmi("domain2.com", "10.0.0.2").run();
        new SetDnsClientRmi("domain3.com", "10.0.0.3").run();
        var builder = new StringBuilder();
        new GetDnsClientRmi("domain1.com", builder).run();

        System.out.println(builder.toString());
    }
}
