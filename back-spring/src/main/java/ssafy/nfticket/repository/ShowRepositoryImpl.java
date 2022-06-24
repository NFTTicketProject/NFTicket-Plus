package ssafy.nfticket.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ssafy.nfticket.dto.request.show.ShowSearchCondition;
import ssafy.nfticket.entity.Shows;

import java.util.List;

import static ssafy.nfticket.entity.QShows.*;

@Repository
@RequiredArgsConstructor
public class ShowRepositoryImpl implements ShowRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Shows> searchByPageSimpleBoard(ShowSearchCondition showSearchCondition, Pageable pageable) {

        JPAQuery<Shows> query = jpaQueryFactory
                .selectFrom(shows)
                .where(categoryCondition(showSearchCondition.getCategory_name()),
                        nameCondition(showSearchCondition.getName()),
                        descCondition(showSearchCondition.getDescription()),
                        runningTimeGoe(showSearchCondition.getMin_running_time()),
                        runningTimeLoe(showSearchCondition.getMax_running_time()),
                        ageLimitGoe(showSearchCondition.getMin_age_limit()),
                        ageLimitLoe(showSearchCondition.getMax_age_limit())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Shows> result = query.fetch();
        return result;
    }

    private BooleanExpression categoryCondition(String categoryName) {
        return StringUtils.hasText(categoryName) ? shows.categoryName.contains(categoryName) : null;
    }

    private BooleanExpression nameCondition(String name) {
        return StringUtils.hasText(name) ? shows.name.contains(name) : null;
    }

    private BooleanExpression descCondition(String desc) {
        return StringUtils.hasText(desc) ? shows.description.contains(desc) : null;
    }

    private BooleanExpression runningTimeGoe(Integer time) {
        return time != null ? shows.runningTime.goe(time) : null;
    }

    private BooleanExpression runningTimeLoe(Integer time) {
        return time != null ? shows.runningTime.loe(time) : null;
    }

    private BooleanExpression ageLimitGoe(Integer age) {
        return age != null ? shows.ageLimit.goe(age) : null;
    }

    private BooleanExpression ageLimitLoe(Integer age) {
        return age != null ? shows.ageLimit.loe(age) : null;
    }

}
