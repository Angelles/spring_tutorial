package MyProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProjectRepository projectRepository, OrderRepository orderRepository) {

        return args -> {
            projectRepository.save(new Project("Saturday Shawl", Craft.SHAWL));
            projectRepository.save(new Project("Mariner's Hat", Craft.HAT));

            projectRepository.findAll().forEach(project -> log.info("Preloaded " + project));


            orderRepository.save(new Order("Silk lace knit shawl", Status.COMPLETED));
            orderRepository.save(new Order("Wool knit hat", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });

        };
    }
}