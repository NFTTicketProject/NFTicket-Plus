package ssafy.nfticket.dto.simple;

import lombok.Data;
import ssafy.nfticket.entity.Profile;

@Data
public class SimpleProfileDto {

    private Long id;

    private String walletId;
    private String nickname;
    private String description;

    private Integer createdAt;
    private String imageUri;
    private String gallery;

    public SimpleProfileDto(Profile profile) {
        this.id = profile.getId();
        this.walletId = profile.getWalletId();
        this.nickname = profile.getNickname();
        this.description = profile.getDescription();
        this.createdAt = profile.getCreatedAt();
        this.imageUri = profile.getImageUri();
        this.gallery = profile.getGallery();
    }
}
