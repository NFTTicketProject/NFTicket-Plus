package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 공연 정보
 */
@Entity
@Table(name = "shows")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Show {

    @Id @GeneratedValue
    private Long id;

    private String categoryName;
    private String name;
    private String description;
    private Integer runningTime;
    private Integer ageLimit;
    private String posterUri;
    private String videoUri;
    private String defaultTicketImageUri;
    private String stageSeller;

}
