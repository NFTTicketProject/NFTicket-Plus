package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.common.error.CustomException;
import ssafy.nfticket.common.error.ErrorCode;
import ssafy.nfticket.dto.request.show.ShowSearchCondition;
import ssafy.nfticket.dto.request.show.ShowRequestDto;
import ssafy.nfticket.dto.request.show.*;
import ssafy.nfticket.entity.Shows;
import ssafy.nfticket.repository.ShowRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    @Transactional
    @Override
    public Shows addShow(ShowRequestDto showRequestDto) {

        Shows shows = new Shows();
        shows.setCategoryName(showRequestDto.getCategory_name());
        shows.setName(showRequestDto.getName());
        shows.setDescription(showRequestDto.getDescription());
        shows.setRunningTime(showRequestDto.getRunning_time());
        shows.setAgeLimit(showRequestDto.getAge_limit());
        shows.setPosterUri(showRequestDto.getPoster_uri());
        shows.setVideoUri(showRequestDto.getVideo_uri());
        shows.setDefaultTicketImageUri(showRequestDto.getDefault_ticket_image_uri());
        shows.setShowScheduleAddress(null);
        shows.setShowScheduleId(null);
        shows.setStaff(showRequestDto.getStaff());

        showRepository.save(shows);

        return shows;
    }

    @Override
    public List<Shows> getAllShow() {
        return showRepository.findAll();
    }


    @Override
    public List<Shows> searchShow(ShowSearchCondition showSearchCondition, Pageable pageable) {
        return showRepository.searchByPageSimpleBoard(showSearchCondition, pageable);
    }

    @Override
    public List<String> getCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("SF");
        return categoryNames;
    }

    @Override
    public Shows getShow(Long showId) {
        return showRepository.findById(showId).orElseThrow(() ->
                new CustomException(ErrorCode.DATA_NOT_FOUND));
    }

    @Transactional
    @Override
    public String updateShow(Long showId, ShowRequestDto showRequestDto) {
        Shows shows = getShow(showId);
        shows.setCategoryName(showRequestDto.getCategory_name());
        shows.setName(showRequestDto.getName());
        shows.setDescription(showRequestDto.getDescription());
        shows.setRunningTime(showRequestDto.getRunning_time());
        shows.setAgeLimit(showRequestDto.getAge_limit());
        shows.setPosterUri(showRequestDto.getPoster_uri());
        shows.setVideoUri(showRequestDto.getVideo_uri());
        shows.setDefaultTicketImageUri(showRequestDto.getDefault_ticket_image_uri());
        shows.setStaff(showRequestDto.getStaff());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public String getCategoryName(Long showId) {
        Shows shows = getShow(showId);
        return shows.getCategoryName();
    }

    @Transactional
    @Override
    public String updateCategoryName(Long showId, SimpleShowCategoryDto simpleShowCategoryDto) {
        Shows shows = getShow(showId);
        shows.setCategoryName(simpleShowCategoryDto.getCategoryName());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public String getName(Long showId) {
        Shows shows = getShow(showId);
        return shows.getName();
    }

    @Transactional
    @Override
    public String updateName(Long showId, SimpleShowNameDto simpleShowNameDto) {
        Shows shows = getShow(showId);
        shows.setName(simpleShowNameDto.getName());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public String getDescription(Long showId) {
        Shows shows = getShow(showId);
        return shows.getDescription();
    }

    @Transactional
    @Override
    public String updateDescription(Long showId, SimpleShowDescDto simpleShowDescDto) {
        Shows shows = getShow(showId);
        shows.setDescription(simpleShowDescDto.getDescription());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public Integer getRunningTime(Long showId) {
        Shows shows = getShow(showId);
        return shows.getRunningTime();
    }

    @Transactional
    @Override
    public String updateRunningTime(Long showId, SimpleShowRunTimeDto simpleShowRunTimeDto) {
        Shows shows = getShow(showId);
        shows.setRunningTime(simpleShowRunTimeDto.getRunningTime());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public Integer getAgeLimit(Long showId) {
        Shows shows = getShow(showId);
        return shows.getAgeLimit();
    }

    @Transactional
    @Override
    public String updateAgeLimit(Long showId, SimpleShowAgeLimitDto simpleShowAgeLimitDto) {
        Shows shows = getShow(showId);
        shows.setAgeLimit(simpleShowAgeLimitDto.getAgeLimit());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public String getPosterUri(Long showId) {
        Shows shows = getShow(showId);
        return shows.getPosterUri();
    }

    @Transactional
    @Override
    public String updatePosterUri(Long showId, SimpleShowPosterUriDto simpleShowPosterUriDto) {
        Shows shows = getShow(showId);
        shows.setPosterUri(simpleShowPosterUriDto.getPosterUri());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public String getVideoUri(Long showId) {
        Shows shows = getShow(showId);
        return shows.getVideoUri();
    }

    @Transactional
    @Override
    public String updateVideoUri(Long showId, SimpleShowVideoUriDto simpleShowVideoUriDto){
        Shows shows = getShow(showId);
        shows.setVideoUri(simpleShowVideoUriDto.getVideoUri());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public String getDefaultTicketImageUri(Long showId) {
        Shows shows = getShow(showId);
        return shows.getDefaultTicketImageUri();
    }

    @Transactional
    @Override
    public String updateDefaultTicketImageUri(Long showId, SimpleShowDefaultTicketImgDto simpleShowDefaultTicketImgDto) {
        Shows shows = getShow(showId);
        shows.setDefaultTicketImageUri(simpleShowDefaultTicketImgDto.getDefaultTicketImageUri());
        showRepository.save(shows);
        return "성공";
    }

    @Transactional
    @Override
    public String addShowScheduleAddress(Long showId, SimpleShowAddressScheduleDto simpleShowAddressScheduleDto) {
        Shows shows = getShow(showId);
        shows.setShowScheduleAddress(simpleShowAddressScheduleDto.getAddress());
        shows.setShowScheduleId(simpleShowAddressScheduleDto.getShowScheduleId());
        showRepository.save(shows);
        return "성공";
    }

    @Override
    public String getShowStaff(Long showId) {
        Shows shows = getShow(showId);
        return shows.getStaff();
    }

    @Transactional
    @Override
    public String updateShowStaff(Long showId, SimpleShowStaffDto simpleShowStaffDto) {
        Shows shows = getShow(showId);
        shows.setStaff(simpleShowStaffDto.getStaff());
        showRepository.save(shows);
        return "성공";
    }




}
