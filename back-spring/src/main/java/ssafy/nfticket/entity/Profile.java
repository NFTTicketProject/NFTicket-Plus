package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 해당 유저 관련 정보
 */
@Entity
@Table(name = "profile")
@Getter
@Setter
@NoArgsConstructor //(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Profile {
    @Id @GeneratedValue
    private Long id;

    private String walletId;
    private String nickname;
    private String description;
    private Integer createdAt;
    private String imageUri;
    private String gallery;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ornament> ornaments = new ArrayList<>();

}
