package dns.dto;

import java.io.Serializable;

public record RequestDTO(
        RequestType requestType,
        String addr
) implements Serializable {
}
