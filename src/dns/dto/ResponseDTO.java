package dns.dto;

import java.io.Serializable;

public record ResponseDTO(
        Object data
) implements Serializable {
}
