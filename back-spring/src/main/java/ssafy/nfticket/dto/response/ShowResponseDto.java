package ssafy.nfticket.dto.response;

import lombok.Data;
import ssafy.nfticket.entity.Shows;

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
    private String staff;

    public ShowResponseDto(Shows shows) {
        this.category_name = shows.getCategoryName();
        this.name = shows.getName();
        this.description = shows.getDescription();
        this.running_time = shows.getRunningTime();
        this.age_limit = shows.getAgeLimit();
        this.poster_uri = shows.getPosterUri();
        this.video_uri = shows.getVideoUri();
        this.default_ticket_image_uri = shows.getDefaultTicketImageUri();
        this.show_schedule_address = shows.getShowScheduleAddress();
        this.show_schedule_id = shows.getShowScheduleId();
        this.staff = shows.getStaff();
    }
}
