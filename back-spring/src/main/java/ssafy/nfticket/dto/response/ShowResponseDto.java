package ssafy.nfticket.dto.response;

import lombok.Data;
import ssafy.nfticket.entity.Show;

@Data
public class ShowResponseDto {

    private String category_name;
    private String name;
    private String description;
    private Integer running_time;
    private Integer age_limit;
    private String poster_uri;
    private String video_uri;
    private String default_ticket_image_uri;
    private String show_schedule_address;
    private Integer show_schedule_id;

    public ShowResponseDto(Show show) {
        this.category_name = show.getCategoryName();
        this.name = show.getName();
        this.description = show.getDescription();
        this.running_time = show.getRunningTime();
        this.age_limit = show.getAgeLimit();
        this.poster_uri = show.getPosterUri();
        this.video_uri = show.getVideoUri();
        this.default_ticket_image_uri = show.getDefaultTicketImageUri();
        this.show_schedule_address = show.getAddress();
        this.show_schedule_id = show.getShowScheduleId();
    }
}
