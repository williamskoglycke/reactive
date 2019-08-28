package se.tre.reactive.infrastructure;

import reactor.core.publisher.Mono;
import se.tre.reactive.domain.Dog;

import java.util.List;

public class DogService {

    public Mono<Dog> getReactiveDog() {
        return Mono.just(new Dog("Fido"));
    }

    public Mono<List<Dog>> getReactiveDoggies() {
        return Mono.just(List.of(
                new Dog("Doge"),
                new Dog("Douge")
                )
        );
    }

}
