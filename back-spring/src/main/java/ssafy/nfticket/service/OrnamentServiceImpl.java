package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.dto.request.ornament.OrnamentDto;
import ssafy.nfticket.dto.request.ornament.OrnamentRequestDto;
import ssafy.nfticket.repository.OrnamentRepository;
import ssafy.nfticket.repository.ProfileRepository;
import ssafy.nfticket.entity.Ornament;
import ssafy.nfticket.entity.Profile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrnamentServiceImpl implements OrnamentService {

    private final ProfileRepository profileRepository;
    private final OrnamentRepository ornamentRepository;

    @Override
    public List<OrnamentDto> getOrnamentList(String walletAddress) {
        Profile profile = profileRepository.findTop1ByWalletId(walletAddress);
        List<OrnamentDto> ornamentList = new ArrayList<>();
        for (Ornament o : profile.getOrnamentList()) {
            ornamentList.add(new OrnamentDto(walletAddress, o.getExhibitType(),
                    o.getXPos(), o.getYPos(), o.getZPos(), o.getImageUri()));
        }
        return ornamentList;
    }

    @Transactional
    @Override
    public String register(String walletAddress, OrnamentRequestDto ornamentRequestDto) {
        Profile profile = profileRepository.findTop1ByWalletId(walletAddress);
        Ornament ornament = Ornament.builder().exhibitType(ornamentRequestDto.getExhibitType())
                .angle(ornamentRequestDto.getAngle()).xPos(ornamentRequestDto.getXpos()).yPos(ornamentRequestDto.getYpos())
                .zPos(ornamentRequestDto.getZpos()).imageUri(ornamentRequestDto.getIpfsURL()).profile(profile).build();
        Ornament dbOrnament = ornamentRepository.save(ornament);
        ornamentRepository.flush();
        return dbOrnament.getProfile().getWalletId();
    }
}
