package ssafy.nfticket.dto.profile;

import lombok.Data;

@Data
public class SimpleProfileImageUriDto {

//    private Long id;
//
//    private String walletId;
//    private String nickname;
//    private String description;
//
//    private Integer createdAt;
    private String imageUri;
//    private String gallery;


    public SimpleProfileImageUriDto(String imageUri) {
        this.imageUri = imageUri;
    }
}
