package br.com.TodoAPI.IntegrationTest.Config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = PostgresqlTestContainerConfig.Initializer.class)
public class PostgresqlTestContainerConfig {
    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        static PostgreSQLContainer<?> postgresql = new PostgreSQLContainer<>("postgres:16.1");

        private static void startContainers(){
            Startables.deepStart(Stream.of(postgresql)).join();
        }

        private static Map<String,String> createConnectionConfiguration(){
            return Map.of(
                    "spring.datasource.url", postgresql.getJdbcUrl(),
                    "spring.datasource.username", postgresql.getUsername(),
                    "spring.datasource.password", postgresql.getPassword()
            );
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource(
                    "testcontainers",
                    (Map) createConnectionConfiguration());
            environment.getPropertySources().addFirst(testcontainers);
        }
    }
}
