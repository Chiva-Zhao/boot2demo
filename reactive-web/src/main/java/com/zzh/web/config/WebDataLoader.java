package com.zzh.web.config;

import com.zzh.web.domain.Quote;
import com.zzh.web.repository.QuoteMongoReactiveRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.LongSupplier;

/**
 * @author zhaozh
 * @version 1.0
 * @date 2018-3-13 9:53
 **/
@Component
public class WebDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(WebDataLoader.class);
    private QuoteMongoReactiveRepository quoteMongoReactiveRepository;

    WebDataLoader(final QuoteMongoReactiveRepository quoteMongoReactiveRepository) {
        this.quoteMongoReactiveRepository = quoteMongoReactiveRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (quoteMongoReactiveRepository.count().block() == 0l) {
            final LongSupplier longSupplier = new LongSupplier() {
                Long l = 0l;

                @Override
                public long getAsLong() {
                    return l++;
                }
            };
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(getClass().getClassLoader().getResourceAsStream("pg2000.txt")));
            Flux.fromStream(bufferedReader.lines().filter(l -> !l.trim().isEmpty())
                    .map(l -> quoteMongoReactiveRepository.save(new Quote(String.valueOf(longSupplier.getAsLong()), "my book", l)))
            ).subscribe(m -> log.info("New quote loaded: {}", m.block()));
            log.info("Repository contains now {} entries.", quoteMongoReactiveRepository.count().block());
        }
    }
}
