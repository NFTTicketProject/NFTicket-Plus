package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.common.error.CustomException;
import ssafy.nfticket.common.error.ErrorCode;
import ssafy.nfticket.dto.params.ShowSearchCondition;
import ssafy.nfticket.dto.request.ShowRequestDto;
import ssafy.nfticket.dto.show.*;
import ssafy.nfticket.entity.Address;
import ssafy.nfticket.entity.Show;
import ssafy.nfticket.repository.AddressRepository;
import ssafy.nfticket.repository.ShowRepository;
import ssafy.nfticket.repository.ShowRepositoryCustom;
import ssafy.nfticket.repository.ShowRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public Show addShow(ShowRequestDto showRequestDto) {

        Show show = new Show();
        show.setCategoryName(showRequestDto.getCategory_name());
        show.setName(showRequestDto.getName());
        show.setDescription(showRequestDto.getDescription());
        show.setRunningTime(showRequestDto.getRunning_time());
        show.setAgeLimit(showRequestDto.getAge_limit());
        show.setPosterUri(showRequestDto.getPoster_uri());
        show.setVideoUri(showRequestDto.getVideo_uri());
        show.setDefaultTicketImageUri(showRequestDto.getDefault_ticket_image_uri());

        showRepository.save(show);

        return show;
    }


    public List<Show> getAllShow() {
        return showRepository.findAll();
    }


    public List<Show> searchShow(ShowSearchCondition showSearchCondition, Pageable pageable) {
        return showRepository.searchByPageSimpleBoard(showSearchCondition, pageable);
    }

    public List<String> getCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("SF");
        return categoryNames;
    }

    public Show getShow(Long showId) {
        return showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
    }

    @Transactional
    public String updateShow(Long showId, ShowRequestDto showRequestDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setCategoryName(showRequestDto.getCategory_name());
        show.setName(showRequestDto.getName());
        show.setDescription(showRequestDto.getDescription());
        show.setRunningTime(showRequestDto.getRunning_time());
        show.setAgeLimit(showRequestDto.getAge_limit());
        show.setPosterUri(showRequestDto.getPoster_uri());
        show.setVideoUri(showRequestDto.getVideo_uri());
        show.setDefaultTicketImageUri(showRequestDto.getDefault_ticket_image_uri());
        showRepository.save(show);
        return "성공";
    }

    public String getCategoryName(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getCategoryName();
    }

    @Transactional
    public String updateCategoryName(Long showId, SimpleShowCategoryDto simpleShowCategoryDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setCategoryName(simpleShowCategoryDto.getCategoryName());
        showRepository.save(show);
        return "성공";
    }

    public String getName(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getName();
    }

    @Transactional
    public String updateName(Long showId, SimpleShowNameDto simpleShowNameDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setName(simpleShowNameDto.getName());
        showRepository.save(show);
        return "성공";
    }

    public String getDescription(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getDescription();
    }

    @Transactional
    public String updateDescription(Long showId, SimpleShowDescDto simpleShowDescDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setDescription(simpleShowDescDto.getDescription());
        showRepository.save(show);
        return "성공";
    }

    public Integer getRunningTime(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getRunningTime();
    }

    @Transactional
    public String updateRunningTime(Long showId, SimpleShowRunTimeDto simpleShowRunTimeDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setRunningTime(simpleShowRunTimeDto.getRunningTime());
        showRepository.save(show);
        return "성공";
    }

    public Integer getAgeLimit(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getAgeLimit();
    }

    @Transactional
    public String updateAgeLimit(Long showId, SimpleShowAgeLimitDto simpleShowAgeLimitDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setAgeLimit(simpleShowAgeLimitDto.getAgeLimit());
        showRepository.save(show);
        return "성공";
    }

    public String getPosterUri(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getPosterUri();
    }

    @Transactional
    public String updatePosterUri(Long showId, SimpleShowPosterUriDto simpleShowPosterUriDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setPosterUri(simpleShowPosterUriDto.getPosterUri());
        showRepository.save(show);
        return "성공";
    }

    public String getVideoUri(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getVideoUri();
    }

    @Transactional
    public String updateVideoUri(Long showId, SimpleShowVideoUriDto simpleShowVideoUriDto){
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setVideoUri(simpleShowVideoUriDto.getVideoUri());
        showRepository.save(show);
        return "성공";
    }

    public String getDefaultTicketImageUri(Long showId) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        return show.getDefaultTicketImageUri();
    }

    @Transactional
    public String updateDefaultTicketImageUri(Long showId, SimpleShowDefaultTicketImgDto simpleShowDefaultTicketImgDto) {
        Show show = showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
        show.setDefaultTicketImageUri(simpleShowDefaultTicketImgDto.getDefaultTicketImageUri());
        showRepository.save(show);
        return "성공";
    }

    public List<String> getSchedule(Long showId) {
        List<Address> addressList = addressRepository.findAddressByShowId(showId);
        List<String> addressStringList = new ArrayList<>();

        for (Address address:addressList) {
            addressStringList.add(address.getAddress());
        }
        return addressStringList;
    }

    @Transactional
    public String addShowScheduleAddress(Long showId, SimpleShowAddressDto simpleShowAddressDto) {
        Address address = new Address();
        address.setAddress(simpleShowAddressDto.getAddress());
        address.setShowScheduleId(simpleShowAddressDto.getShowScheduleId());
        address.setShowId(showId);
        addressRepository.save(address);
        return "성공";
    }






}
