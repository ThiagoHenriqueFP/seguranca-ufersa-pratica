package dns;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; // 1. Import this

public class Storage {

    private static final Map<String, String> initialStorage = new HashMap<>() {
        {
            put("domain.com.br", "10.10.10.10");
        }
    };

    // Eager, thread-safe singleton initialization
    public static final Storage instance = new Storage(initialStorage);

    // 2. Use a thread-safe Map implementation
    private final Map<String, String> storage;

    // 3. Make the timestamp 'volatile'
    // This ensures changes made by one thread are visible to all others.
    private volatile LocalDateTime updatedAt;

    private Storage(Map<String, String> storage) {
        // 4. Initialize with a ConcurrentHashMap
        this.storage = new ConcurrentHashMap<>(storage);
        this.updatedAt = LocalDateTime.now();
    }

    public static Storage getInstance() {
        return instance;
    }

    /**
     * Returns an unmodifiable view. This is still crucial!
     * It prevents callers from bypassing your methods.
     */
    public Map<String, String> getStorage() {
        return Collections.unmodifiableMap(this.storage);
    }

    public String get(String key) {
        // .get() on ConcurrentHashMap is thread-safe
        return storage.get(key);
    }

    /**
     * This method is now thread-safe.
     * .put() on ConcurrentHashMap is an atomic operation.
     */
    public void setStorage(String key, String value) {
        this.storage.put(key, value);
        this.updatedAt = LocalDateTime.now(); // 'volatile' handles this write
    }

    /**
     * This method is also thread-safe.
     */
    public void removeStorage(String key) {
        this.storage.remove(key);
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}