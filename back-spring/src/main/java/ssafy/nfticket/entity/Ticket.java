package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Ticket {
    @Id @GeneratedValue
    private Long id;

    private Integer saleId;
    private String imageUri;
    private String blockHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shows_id")
    private Shows shows;

    @OneToOne(mappedBy = "ticket")
    private Sale sale;

}