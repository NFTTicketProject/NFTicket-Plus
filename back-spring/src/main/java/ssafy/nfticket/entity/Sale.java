package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 판매 정보
 */
@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Sale {
    @Id @GeneratedValue
    private Long id;

    private Integer showScheduleId;
    private String description;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;




}
