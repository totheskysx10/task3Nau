package ru.vsurin.task3nau.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация приложения
 */
@Configuration
public class AppConfig {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    /**
     * Возвращает название приложения
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Возвращает версию приложения
     */
    public String getAppVersion() {
        return appVersion;
    }
}
