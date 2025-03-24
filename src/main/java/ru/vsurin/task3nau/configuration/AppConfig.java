package ru.vsurin.task3nau.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Конфигурация приложения
 */
@Configuration
public class AppConfig {

    /**
     * Создаёт бин с информацией о приложении
     */
    @Bean
    public AppInfo appInfo() {
        String appName = getManifestAttribute("Implementation-Title", "TaskManager");
        String appVersion = getManifestAttribute("Implementation-Version", "unknown");
        return new AppInfo(appName, appVersion);
    }

    /**
     * Получает значение атрибута из файла манифеста JAR-файла.
     *
     * @param attributeName название атрибута
     * @param defaultValue  значение по умолчанию, если атрибут отсутствует
     */
    private String getManifestAttribute(String attributeName, String defaultValue) {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF"));
            return properties.getProperty(attributeName, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Обёртка для хранения информации о приложении.
     *
     * @param name    название приложения
     * @param version версия приложения
     */
    public record AppInfo(String name, String version) {
    }
}
