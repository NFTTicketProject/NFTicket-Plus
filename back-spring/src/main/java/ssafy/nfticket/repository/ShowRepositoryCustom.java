package ssafy.nfticket.repository;

import org.springframework.data.domain.Pageable;
import ssafy.nfticket.dto.params.ShowSearchCondition;
import ssafy.nfticket.entity.Show;

import java.util.List;

public interface ShowRepositoryCustom {
    List<Show> searchByPageSimpleBoard(ShowSearchCondition showSearchCondition, Pageable pageable);
}
