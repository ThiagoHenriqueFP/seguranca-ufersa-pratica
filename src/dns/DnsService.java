package dns;

public interface DnsService {
    String getIpAddr(String url);

    void setIpAddr(String url, String ipAddr);
}
