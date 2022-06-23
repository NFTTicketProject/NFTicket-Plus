package ssafy.nfticket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ticket_images")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TicketImages {
    @Id @GeneratedValue
    private Long id;

    private Integer saleId;
    private String uri;


}
