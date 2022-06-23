package ssafy.nfticket.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.nfticket.dto.params.ShowSearchCondition;
import ssafy.nfticket.dto.request.ShowRequestDto;
import ssafy.nfticket.dto.response.ShowResponseDto;
import ssafy.nfticket.dto.show.*;
import ssafy.nfticket.entity.Show;
import ssafy.nfticket.service.ShowService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/show")
public class ShowApiController {

    private final ShowService showService;

    /**
     * 공연 추가하기
     * @param showRequestDto
     * @return
     */
    @PostMapping()
    public ResponseEntity<SimpleShowIdDto> addShow(@RequestBody ShowRequestDto showRequestDto) {

        Show newShow = showService.addShow(showRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleShowIdDto(newShow.getId()));
    }

    /**
     * 모든 공연 정보 반환 (함수 및 API 이름 수정 여지 있음)
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<ShowResponseDto>> getAllShow() {

        List<Show> show = showService.getAllShow();
        List<ShowResponseDto> result = show.stream().map(x -> new ShowResponseDto(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ShowResponseDto>> searchShow(@RequestParam(value = "category_name", required = false) String category_name,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "min_running_time", required = false) Integer min_running_time,
                                     @RequestParam(value = "max_running_time", required = false) Integer max_running_time,
                                     @RequestParam(value = "min_age_limit", required = false) Integer min_age_limit,
                                     @RequestParam(value = "max_age_limit", required = false) Integer max_age_limit,
                                     @RequestParam(value = "sort_by", required = false) String sort_by,
                                     @RequestParam(value = "order_by", required = false) String order_by,
                                     @PageableDefault(size = 1000) Pageable pageable) {

        ShowSearchCondition showSearchCondition = new ShowSearchCondition();
        showSearchCondition.setCategory_name(category_name);
        showSearchCondition.setName(name);
        showSearchCondition.setDescription(description);
        showSearchCondition.setMin_running_time(min_running_time);
        showSearchCondition.setMax_running_time(max_running_time);
        showSearchCondition.setMin_age_limit(min_age_limit);
        showSearchCondition.setMax_age_limit(max_age_limit);

        List<Show> showSearch = showService.searchShow(showSearchCondition, pageable);
        List<ShowResponseDto> result = showSearch.stream().map(ShowResponseDto::new).collect(Collectors.toList());


        return ResponseEntity.ok().body(result);
    }

    /*
    * 공연 카데고리 목록 반환
    * */
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategoryNames() {
        return ResponseEntity.ok().body(showService.getCategoryNames());
    }

    /*
    * 공연 정보 반환
    * */
    @GetMapping("/{showId}")
    public ResponseEntity<ShowResponseDto> getShow(@PathVariable("showId")Long showId) {
        return ResponseEntity.ok().body(new ShowResponseDto(showService.getShow(showId)));
    }

    /*
    * 공연 정보 수정
    * */
    @PatchMapping("/{showId}")
    public ResponseEntity<String> updateShow(@PathVariable("showId") Long showId,
                                     @Valid @RequestBody ShowRequestDto showRequestDto) {
        return ResponseEntity.ok().body(showService.updateShow(showId, showRequestDto));
    }


    /*
    * 공연 카데고리 명 반환
    * */
    @GetMapping("/{showId}/category-name")
    public ResponseEntity<SimpleShowCategoryDto> getShowCategoryName(@PathVariable("showId")Long showId){
        return ResponseEntity.ok().body(new SimpleShowCategoryDto(showService.getCategoryName(showId)));
    }

    /*
    * 공연 카데고리 명 수정
    * */
    @PatchMapping("/{showId}/category-name")
    public ResponseEntity<String> updateShowCategoryName(@PathVariable("showId") Long showId,
                                                         @Valid @RequestBody SimpleShowCategoryDto simpleShowCategoryDto) {
        return ResponseEntity.ok().body(showService.updateCategoryName(showId, simpleShowCategoryDto));
    }


    /*
    * 공연 명 반환
    * */
    @GetMapping("/{showId}/name")
    public ResponseEntity<SimpleShowNameDto> getShowName(@PathVariable("showId") Long showId) {
        return ResponseEntity.ok().body(new SimpleShowNameDto(showService.getName(showId)));
    }

    /*
    * 공연 명 수정
    * */
    @PatchMapping("/{showId}/name")
    public ResponseEntity<String> updateShowName(@PathVariable("showId") Long showId,
                                                 @Valid @RequestBody SimpleShowNameDto simpleShowNameDto) {
        return ResponseEntity.ok().body(showService.updateName(showId, simpleShowNameDto));
    }

    /*
    * 공연 설명 반환
    * */
    @GetMapping("/{showId}/description")
    public ResponseEntity<SimpleShowDescDto> getShowDescription(@PathVariable("showId") Long showId) {
        return ResponseEntity.ok().body(new SimpleShowDescDto(showService.getDescription(showId)));
    }

    /*
    * 공연 설명 수정
    * */
    @PatchMapping("/{showId}/description")
    public ResponseEntity<String> updateShowDescription(@PathVariable("showId") Long showId,
                                                        @Valid @RequestBody SimpleShowDescDto simpleShowDescDto) {
        return ResponseEntity.ok().body(showService.updateDescription(showId, simpleShowDescDto));
    }

    /*
    * 공연 길이 반환
    * */
    @GetMapping("/{showId}/running-time")
    public ResponseEntity<SimpleShowRunTimeDto> getShowRunningTime(@PathVariable("showId") Long showId) {
        return ResponseEntity.ok().body(new SimpleShowRunTimeDto(showService.getRunningTime(showId)));
    }

    /*
    * 공연 길이 수정
    * */
    @PatchMapping("/{showId}/running-time")
    public ResponseEntity<String> updateShowRunningTime(@PathVariable("showId") Long showId,
                                                        @Valid @RequestBody SimpleShowRunTimeDto simpleShowRunTimeDto) {
        return ResponseEntity.ok().body(showService.updateRunningTime(showId, simpleShowRunTimeDto));
    }

    /*
    * 공연 연령 제한 반환
    * */
    @GetMapping("/{showId}/age-limit")
    public ResponseEntity<SimpleShowAgeLimitDto> getShowAgeLimit(@PathVariable("showId") Long showId) {
        return ResponseEntity.ok().body(new SimpleShowAgeLimitDto(showService.getAgeLimit(showId)));
    }

    /*
    * 공연 연령 제한 수정
    * */
    @PatchMapping("/{showId}/age-limit")
    public ResponseEntity<String> updateShowAgeLimit(@PathVariable("showId") Long showId,
                                                     @Valid @RequestBody SimpleShowAgeLimitDto simpleShowAgeLimitDto) {
        return ResponseEntity.ok().body(showService.updateAgeLimit(showId, simpleShowAgeLimitDto));
    }

    /*
    * 공연 포스터 URI 반환
    * */
    @GetMapping("/{showId}/poster-uri")
    public ResponseEntity<SimpleShowPosterUriDto> getShowPosterUri(@PathVariable("showId") Long showId) {
        return ResponseEntity.ok().body(new SimpleShowPosterUriDto(showService.getPosterUri(showId)));
    }

    /*
    * 공연 포스터 URI 수정
    * */
    @PatchMapping("/{showId}/poster-uri")
    public ResponseEntity<String> updateShowPosterUri(@PathVariable("showId") Long showId,
                                                      @Valid @RequestBody SimpleShowPosterUriDto simpleShowPosterUriDto) {
        return ResponseEntity.ok().body(showService.updatePosterUri(showId, simpleShowPosterUriDto));
    }

    /*
    * 공연 예고편 URI 반환
    * */
    @GetMapping("/{showId}/video-uri")
    public ResponseEntity<SimpleShowVideoUriDto> getShowVideoUri(@PathVariable("showId") Long showId) {
        return ResponseEntity.ok().body(new SimpleShowVideoUriDto(showService.getVideoUri(showId)));
    }

    /*
    * 공연 예고편 URI 수정
    * */
    @PatchMapping("/{showId}/video-uri")
    public ResponseEntity<String> updateShowVideoUri(@PathVariable("showId") Long showId,
                                                     @Valid @RequestBody SimpleShowVideoUriDto simpleShowVideoUriDto){
        return ResponseEntity.ok().body(showService.updateVideoUri(showId, simpleShowVideoUriDto));
    }


    /*
    * 공연 기본 티켓 이미지 URI 반환
    * */
    @GetMapping("/{showId}/default-ticket-image-uri")
    public ResponseEntity<SimpleShowDefaultTicketImgDto> getShowDefaultTicketImageUri(@PathVariable("showId") Long showId) {
        return ResponseEntity.ok().body(new SimpleShowDefaultTicketImgDto(showService.getDefaultTicketImageUri(showId)));
    }

    /*
    * 공연 기본 티켓 이미지 URI 수정
    * */
    @PatchMapping("/{showId}/default-ticket-image-uri")
    public ResponseEntity<String> updateShowDefaultTicketImageUri(@PathVariable("showId") Long showId,
                                                                  @Valid @RequestBody SimpleShowDefaultTicketImgDto simpleShowDefaultTicketImgDto) {
        return ResponseEntity.ok().body(showService.updateDefaultTicketImageUri(showId, simpleShowDefaultTicketImgDto));
    }

    /*
    * 공연 스케줄 CA 목록 반환 -> Address + Id
    * */
    @GetMapping("/{showId}/show-schedule")
    public ResponseEntity<SimpleShowAddressScheduleDto> getShowSchedule(@PathVariable("showId") Long showId) {
        Show show = showService.getShow(showId);
        return ResponseEntity.ok().body(new SimpleShowAddressScheduleDto(show.getShowScheduleId(), show.getAddress()));
    }

    /*
    * 공연 스케줄 정보 추가 -> Address에 추가
    * */
    @PutMapping("/{showId}/show-schedule")
    public ResponseEntity<String> updateShowSchedule(@PathVariable("showId") Long showId,
                                                     @Valid @RequestBody SimpleShowAddressScheduleDto simpleShowAddressScheduleDto) {
        return ResponseEntity.ok().body(showService.addShowScheduleAddress(showId, simpleShowAddressScheduleDto));
    }

}
