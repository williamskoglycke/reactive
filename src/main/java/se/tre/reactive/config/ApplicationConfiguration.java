package se.tre.reactive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.tre.reactive.infrastructure.DogService;
import se.tre.reactive.infrastructure.ExampleRemoteService;
import se.tre.reactive.infrastructure.PersonService;
import se.tre.reactive.infrastructure.PuppyService;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PersonService personService() {
        return new PersonService();
    }

    @Bean
    public DogService dogService() {
        return new DogService();
    }

    @Bean
    public PuppyService puppyService() {
        return new PuppyService();
    }

    @Bean
    public ExampleRemoteService exampleRemoteService() {
        return new ExampleRemoteService();
    }

}
