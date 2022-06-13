package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 랜덤 명사, 무작위 프로필명 만들 때 사용
 */
@Entity
@Table(name = "random_noun")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RandomNoun {
    @Id @GeneratedValue
    private Long id;

    private String noun;
}
