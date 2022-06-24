package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 판매 정보
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id @GeneratedValue
    private Long id;

    private Integer showScheduleId;
    private String description;
    private Integer startedAt;
    private Integer endedAt;

    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @OneToOne
    @JoinColumn(name = "shows_id")
    private Shows shows;

}
