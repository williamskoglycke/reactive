package se.tre.reactive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;
import se.tre.reactive.domain.Dog;
import se.tre.reactive.domain.Person;
import se.tre.reactive.domain.Puppy;
import se.tre.reactive.domain.owner.Owner;
import se.tre.reactive.domain.owner.PuppyWithMother;
import se.tre.reactive.domain.owner.MotherDog;
import se.tre.reactive.infrastructure.DogService;
import se.tre.reactive.infrastructure.PersonService;
import se.tre.reactive.infrastructure.PuppyService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogTest {

    @Autowired
    PuppyService puppyService;

    @Autowired
    PersonService personService;

    @Autowired
    DogService dogService;

    @Test
    public void forOwner1() {

        final Owner ownerBlock = getOwner();


        final Owner reactiveBlock = personService.getReactivePerson().flatMap(
                person -> dogService.getReactiveDog().flatMap(
                        dog -> puppyService.getReactivePuppy().map(
                                puppy -> new Owner(person.getName(), new MotherDog(dog.getName(), new PuppyWithMother(puppy.getName())))))).block();

        Assert.assertEquals(ownerBlock, reactiveBlock);
    }

    @Test
    public void forOwner2() {

        final Owner ownerBlock = getOwner();


        final Mono<Person> reactivePerson = personService.getReactivePerson();
        final Mono<Dog> reactiveDog = dogService.getReactiveDog();
        final Mono<Puppy> reactivePuppy = puppyService.getReactivePuppy();

        final Mono<Tuple3<Person, Dog, Puppy>> zip = Mono.zip(reactivePerson, reactiveDog, reactivePuppy);

        final Owner owner = zip.map(tuple3 -> new Owner(
                        tuple3.getT1().getName(),
                        new MotherDog(
                                tuple3.getT2().getName(),
                                new PuppyWithMother(tuple3.getT3().getName())
                        )
                )
        ).block();

        Assert.assertEquals(owner, ownerBlock);

    }

    @Test
    public void forOwner3() {

        final List<Owner> owners = List.of(getOwner(), getOwner());


        final List<Owner> ownersBlock = personService.getReactivePeople().flatMap(
                people -> Flux.fromIterable(people).flatMap(
                        person -> dogService.getReactiveDog().flatMap(
                                dog -> puppyService.getReactivePuppy().map(
                                        puppy -> new Owner(person.getName(), new MotherDog(dog.getName(), new PuppyWithMother(puppy.getName())))
                                )
                        )
                ).collectList()
        ).block();


        Assert.assertEquals(owners, ownersBlock);

    }

    private Owner getOwner() {

        final Puppy puppyBlock = puppyService.getReactivePuppy().block();
        final Person personBlock = personService.getReactivePerson().block();
        final Dog dogBlock = dogService.getReactiveDog().block();

        final Owner ownerBlock = new Owner(personBlock.getName(), new MotherDog(dogBlock.getName(), new PuppyWithMother(puppyBlock.getName())));

        return ownerBlock;

    }

}
