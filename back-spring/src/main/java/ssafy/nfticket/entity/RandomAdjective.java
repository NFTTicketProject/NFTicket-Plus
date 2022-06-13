package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 랜덤 프로필 작명법
 */
@Entity
@Table(name = "random_adjective")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RandomAdjective {
    @Id @GeneratedValue
    private Long id;

    private String adjective;
}
