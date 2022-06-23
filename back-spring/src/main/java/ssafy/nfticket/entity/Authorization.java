package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * 지갑 소유자 확인
 * E(text, nonce, 비밀키) -> D(cypher, 공개키)일때,
 * D(cypher, 공개키) == text 이면 지갑 소유 증명완료
 * nonce는 리플레이 어택 방지용
 */
@Entity
@Table(name = "authorization")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Authorization {

    @Id @GeneratedValue
    private Long id;

    private Integer nonce;

    @Column(name = "nonce_expired_at")
    private OffsetDateTime nonceExpiredAt;
    private String signature;
    private String jwt;

}
