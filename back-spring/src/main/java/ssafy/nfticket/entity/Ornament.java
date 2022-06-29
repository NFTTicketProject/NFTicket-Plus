package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ornament {
    @Id @GeneratedValue
    private Long id;

    private String exhibitType;
    private Float angle;
    private Float xPos;
    private Float yPos;
    private Float zPos;
    private String imageUri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
