package se.tre.reactive.infrastructure;

import reactor.core.publisher.Mono;
import se.tre.reactive.domain.Puppy;

import java.util.List;

public class PuppyService {

    public Mono<Puppy> getReactivePuppy() {
        return Mono.just(new Puppy("rawr"));
    }

    public Mono<List<Puppy>> getReactivePuppies() {
        return Mono.just(List.of(
                new Puppy("pip"),
                new Puppy("poop")
                )
        );
    }

}
