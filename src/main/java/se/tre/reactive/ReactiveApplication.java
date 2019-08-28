package se.tre.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import se.tre.reactive.config.ApplicationConfiguration;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
@ComponentScan(basePackages = "se.tre.reactive",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)})
public class ReactiveApplication {

    public static void main(String[] args) {

        //Streams (Lazy, Once)

        //Mono 0...1 (CompletableFuture)

        //Flux 0...n

        SpringApplication.run(ReactiveApplication.class, args);

    }


}
