package ssafy.nfticket.dto.request;

import lombok.Data;
import ssafy.nfticket.entity.Show;

@Data
public class ShowRequestDto {

    private String category_name;
    private String name;
    private String description;
    private Integer running_time;
    private Integer age_limit;
    private String poster_uri;
    private String video_uri;
    private String default_ticket_image_uri;
}
