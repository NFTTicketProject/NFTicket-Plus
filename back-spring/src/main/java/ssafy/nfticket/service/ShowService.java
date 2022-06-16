package ssafy.nfticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ssafy.nfticket.dto.params.ShowSearchCondition;
import ssafy.nfticket.dto.request.ShowRequestDto;
import ssafy.nfticket.entity.Show;
import ssafy.nfticket.repository.ShowRepository;
import ssafy.nfticket.repository.ShowRepositoryCustom;
import ssafy.nfticket.repository.ShowRepositoryImpl;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;

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
}
