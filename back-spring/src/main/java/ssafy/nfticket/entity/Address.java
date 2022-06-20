package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 공연 smartcontract 주소
 */
@Entity
@Table(name = "address")
@Getter @Setter
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {

    @Id @GeneratedValue
    private Long id;

    private String address;
    private Integer showScheduleId;
    private Long showId;


}
