package dns;

import java.time.LocalDateTime;
import java.util.Map;

public class Storage {
    private static Map<String, String> initialStorage = Map.of("domain.com.br", "10.10.10.10");
    public static Storage instance;
    private Map<String, String> storage;
    private LocalDateTime updatedAt;

    public Storage(Map<String, String> storage) {
        this.storage = storage;
        this.updatedAt = LocalDateTime.now();
    }

    public static Storage getInstance() {
        if (instance == null)
            instance = new Storage(initialStorage);
        return instance;
    }

    public Map<String, String> getStorage() {
        return storage;
    }

    public String get(String key) {
        return storage.get(key);
    }

    public void setStorage(String key, String value) {
        this.storage.put(key, value);
        this.updatedAt = LocalDateTime.now();
    }
}
