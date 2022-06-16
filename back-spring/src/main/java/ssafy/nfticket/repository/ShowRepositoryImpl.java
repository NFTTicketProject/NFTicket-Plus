package ssafy.nfticket.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ssafy.nfticket.dto.params.ShowSearchCondition;
import ssafy.nfticket.entity.QShow;
import ssafy.nfticket.entity.Show;

import java.util.List;

import static ssafy.nfticket.entity.QShow.*;

@Repository
@RequiredArgsConstructor
public class ShowRepositoryImpl implements ShowRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Show> searchByPageSimpleBoard(ShowSearchCondition showSearchCondition, Pageable pageable) {

        JPAQuery<Show> query = jpaQueryFactory
                .selectFrom(show)
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

        List<Show> result = query.fetch();
        return result;
    }

    private BooleanExpression categoryCondition(String categoryName) {
        return StringUtils.hasText(categoryName) ? show.categoryName.contains(categoryName) : null;
    }

    private BooleanExpression nameCondition(String name) {
        return StringUtils.hasText(name) ? show.name.contains(name) : null;
    }

    private BooleanExpression descCondition(String desc) {
        return StringUtils.hasText(desc) ? show.description.contains(desc) : null;
    }

    private BooleanExpression runningTimeGoe(Integer time) {
        return time != null ? show.runningTime.goe(time) : null;
    }

    private BooleanExpression runningTimeLoe(Integer time) {
        return time != null ? show.runningTime.loe(time) : null;
    }

    private BooleanExpression ageLimitGoe(Integer age) {
        return age != null ? show.ageLimit.goe(age) : null;
    }

    private BooleanExpression ageLimitLoe(Integer age) {
        return age != null ? show.ageLimit.loe(age) : null;
    }

}
