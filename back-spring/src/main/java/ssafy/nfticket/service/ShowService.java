package ssafy.nfticket.service;

import org.springframework.stereotype.Service;
import ssafy.nfticket.dto.request.show.*;
import ssafy.nfticket.entity.Shows;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public interface ShowService {
    Shows addShow(ShowRequestDto showRequestDto);
    List<Shows> getAllShow();
    List<Shows> searchShow(ShowSearchCondition showSearchCondition, Pageable pageable);
    List<String> getCategoryNames();
    Shows getShow(Long showId);
    String updateShow(Long showId, ShowRequestDto showRequestDto);
    String getCategoryName(Long showId);
    String updateCategoryName(Long showId, SimpleShowCategoryDto simpleShowCategoryDto);
    String getName(Long showId);
    String updateName(Long showId, SimpleShowNameDto simpleShowNameDto);
    String getDescription(Long showId);
    String updateDescription(Long showId, SimpleShowDescDto simpleShowDescDto);
    Integer getRunningTime(Long showId);
    String updateRunningTime(Long showId, SimpleShowRunTimeDto simpleShowRunTimeDto);
    Integer getAgeLimit(Long showId);
    String updateAgeLimit(Long showId, SimpleShowAgeLimitDto simpleShowAgeLimitDto);
    String getPosterUri(Long showId);
    String updatePosterUri(Long showId, SimpleShowPosterUriDto simpleShowPosterUriDto);
    String getVideoUri(Long showId);
    String updateVideoUri(Long showId, SimpleShowVideoUriDto simpleShowVideoUriDto);
    String getDefaultTicketImageUri(Long showId);
    String updateDefaultTicketImageUri(Long showId, SimpleShowDefaultTicketImgDto simpleShowDefaultTicketImgDto);
    String addShowScheduleAddress(Long showId, SimpleShowAddressScheduleDto simpleShowAddressScheduleDto);
    String getShowStaff(Long showId);
    String updateShowStaff(Long showId, SimpleShowStaffDto simpleShowStaffDto);


}
