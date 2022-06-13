package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 스태프 정보
 */
@Entity
@Table(name = "staff")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Staff {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String imageUri;
    private String occupation;

}
