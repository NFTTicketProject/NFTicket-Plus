package ssafy.nfticket.repository;

import org.springframework.data.domain.Pageable;
import ssafy.nfticket.dto.request.show.ShowSearchCondition;
import ssafy.nfticket.entity.Shows;

import java.util.List;

public interface ShowRepositoryCustom {
    List<Shows> searchByPageSimpleBoard(ShowSearchCondition showSearchCondition, Pageable pageable);
}
