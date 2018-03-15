package com.zzh.web.repository;

import com.zzh.web.domain.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-3-12 16:22
 **/
public interface QuoteMongoReactiveRepository extends ReactiveCrudRepository<Quote, String> {
    @Query("{ id: { $exists: true }}")
    Flux<Quote> retrieveAllQuotesPaged(final Pageable page);
}
