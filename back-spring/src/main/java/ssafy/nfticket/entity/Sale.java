package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 판매 정보
 */
@Entity
@Table(name = "sale")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Sale {
    @Id @GeneratedValue
    private Long id;

    private Integer showScheduleId;
    private String description;
    private Integer startedAt;
    private Integer endedAt;




}
