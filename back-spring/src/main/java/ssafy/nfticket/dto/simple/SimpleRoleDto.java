package ssafy.nfticket.dto.simple;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleRoleDto {
    private String occupation;
    private int staffId;
    private int showId;
}
