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
import ssafy.nfticket.dto.show.SimpleShowIdDto;
import ssafy.nfticket.entity.Show;
import ssafy.nfticket.service.ShowService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ShowApiController {

    private final ShowService showService;

    /**
     * 공연 추가하기
     * @param showRequestDto
     * @return
     */
    @PostMapping("/show")
    public ResponseEntity addShow(@RequestBody ShowRequestDto showRequestDto) {

        Show newShow = showService.addShow(showRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SimpleShowIdDto(newShow.getId()));
    }

    /**
     * 모든 공연 정보 반환 (함수 및 API 이름 수정 여지 있음)
     * @return
     */
    @GetMapping("/show")
    public ResponseEntity getAllShow() {

        List<Show> show = showService.getAllShow();
        List<ShowResponseDto> result = show.stream().map(x -> new ShowResponseDto(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/show/search")
    public ResponseEntity searchShow(@RequestParam(value = "category_name", required = false) String category_name,
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


        return ResponseEntity.ok().body(showSearch);
    }



}
