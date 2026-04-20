package com.vibe.core.persistence;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

/**
 * [Abstraction] QueryDSL 怨듯넻 湲곕컲 ?대옒??
 * 蹂듭옟???섏씠吏?諛?移댁슫??荑쇰━ 理쒖쟻?붾? ?먮룞?뷀빀?덈떎.
 */
@RequiredArgsConstructor
public abstract class QuerydslCustomRepositorySupport {
    protected final JPAQueryFactory queryFactory;

    protected <T> Page<T> applyPagination(Pageable pageable, 
                                          JPAQuery<T> contentQuery, 
                                          JPAQuery<Long> countQuery) {
        List<T> content = contentQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}

