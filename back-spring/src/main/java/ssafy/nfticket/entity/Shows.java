package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 공연 정보
 */
@Entity
@Getter
@Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Shows {

    @Id @GeneratedValue
    private Long id;

    private String categoryName;
    private String name;
    private String description;
    private Integer runningTime;
    private Integer ageLimit;
    private String posterUri;
    private String videoUri; // X
    private String defaultTicketImageUri; // X
//    private String stageSeller; // Dto에서 profile의 닉네임
    private String showScheduleAddress;
    private Integer showScheduleId;
    private String staff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(mappedBy = "shows", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketList = new ArrayList<>();

    @OneToOne(mappedBy = "shows")
    private Sale sale;


}
