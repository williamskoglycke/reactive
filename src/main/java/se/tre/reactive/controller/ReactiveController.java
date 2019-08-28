package se.tre.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import se.tre.reactive.domain.owner.Owner;
import se.tre.reactive.infrastructure.DogService;
import se.tre.reactive.infrastructure.PersonService;
import se.tre.reactive.infrastructure.PuppyService;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class ReactiveController {

    private final DogService dogService;
    private final PersonService personService;
    private final PuppyService puppyService;

    public ReactiveController(final PersonService personService,
                              final DogService dogService,
                              final PuppyService puppyService) {
        this.personService = personService;
        this.dogService = dogService;
        this.puppyService = puppyService;
    }

    /**
     * @return Owner with one dog that got one pup (Concurrent)
     */

    @GetMapping("/reactive/owner")
    public Mono<Owner> getOwnerConcurrent() {

        return null;

    }

    /**
     * @return Owner with one dog that got one pup (After each other)
     */

    @GetMapping("/reactive/owner2")
    public Mono<Owner> getOwnerAfterEachOther() {

        return null;

    }

    /**
     * @return Owners with one dog that got one pup (After each other)
     */

    @GetMapping("/reactive/owner3")
    public Mono<List<Owner>> getOwnersAfterEachOther() {

        return null;

    }

    @GetMapping(path = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> flux() {

        final List<Integer> integerList = IntStream.range(1, 100).boxed().collect(Collectors.toList());

        return Flux.fromIterable(integerList).delayElements(Duration.ofMillis(400));

    }

}
