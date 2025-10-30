package dns;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DnsServerRmi {
    public static void main(String[] args) {
        try {

            Mac shaMac = Mac.getInstance("HmacSHA256");
            SecretKeySpec chaveMAC = new SecretKeySpec("senhatopparanosprotegerdewebataques".getBytes("UTF-8"), "HmacSHA256");
            shaMac.init(chaveMAC);
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128);
            SecretKey aesKey = keygen.generateKey();

            DnsService instance = new DnsServiceImpl();
//            DnsService skeleton = (DnsService) UnicastRemoteObject.exportObject(instance, 8080);
            LocateRegistry.createRegistry(8080);
            Registry registry = LocateRegistry.getRegistry(8080);
            registry.bind("DnsService", instance);

            System.out.println("[DNS] running at port 8080");
        } catch (RemoteException | AlreadyBoundException | NoSuchAlgorithmException | InvalidKeyException |
                 UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
