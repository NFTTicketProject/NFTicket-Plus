package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 무언가 막판 API 추가용 블록해쉬
 */
@Entity
@Table(name = "block_hash")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BlockHash {
    @Id @GeneratedValue
    private Long id;

    private Integer ticketId;
    private String blockHash;
}
