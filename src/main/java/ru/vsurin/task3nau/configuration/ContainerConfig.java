package ru.vsurin.task3nau.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.vsurin.task3nau.domain.Task;

/**
 * Имитация базы данных
 */
@Configuration
public class ContainerConfig {

    /**
     * Создаёт контейнер для хранения задач
     */
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Task> taskContainer() {
        return new ArrayList<>();
    }
}
