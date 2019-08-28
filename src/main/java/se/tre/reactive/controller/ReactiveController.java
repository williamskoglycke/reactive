package se.tre.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import se.tre.reactive.domain.owner.Owner;
import se.tre.reactive.infrastructure.DogService;
import se.tre.reactive.infrastructure.ExampleRemoteService;
import se.tre.reactive.infrastructure.PersonService;
import se.tre.reactive.infrastructure.PuppyService;

import java.util.List;

@RestController
public class ReactiveController {

    private final DogService dogService;
    private final PersonService personService;
    private final PuppyService puppyService;
    private final ExampleRemoteService exampleRemoteService;

    public ReactiveController(final PersonService personService,
                              final DogService dogService,
                              final PuppyService puppyService,
                              final ExampleRemoteService exampleRemoteService) {
        this.personService = personService;
        this.dogService = dogService;
        this.puppyService = puppyService;
        this.exampleRemoteService = exampleRemoteService;
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


    @GetMapping("/test")
    public Mono<String> test() {
        return exampleRemoteService.getName();
    }

}
