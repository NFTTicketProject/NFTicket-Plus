package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 해당 유저 관련 정보
 */
@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Profile {
    @Id @GeneratedValue
    private Long id;

    private String walletId;
    private String nickname;
    private String description;
    private Integer createdAt;
    private String imageUri;
    private String gallery;

}
