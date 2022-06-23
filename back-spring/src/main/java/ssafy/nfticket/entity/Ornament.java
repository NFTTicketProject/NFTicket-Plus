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
    private float angle;
    private float xPos;
    private float yPos;
    private float zPos;
    private String ipfsURL;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

}
