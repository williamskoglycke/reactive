package se.tre.reactive.infrastructure;

import reactor.core.publisher.Mono;
import se.tre.reactive.domain.Person;

import java.util.List;

public class PersonService {

    public Mono<Person> getReactivePerson() {
        return Mono.just(new Person("Tord"));
    }

    public Mono<List<Person>> getReactivePeople() {
        return Mono.just(List.of(
                new Person("Tord"),
                new Person("Romeo")
                )
        );
    }

}
