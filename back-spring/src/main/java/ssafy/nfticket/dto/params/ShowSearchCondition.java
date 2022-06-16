package ssafy.nfticket.dto.params;

import lombok.Data;

@Data
public class ShowSearchCondition {

    private String category_name;
    private String name;
    private String description;
    private Integer min_running_time;
    private Integer max_running_time;
    private Integer min_age_limit;
    private Integer max_age_limit;
    private String sort_by;
    private String order_by;
}
