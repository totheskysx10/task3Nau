//package ru.vsurin.task3nau.configuration;
//
//import jakarta.annotation.PostConstruct;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import ru.vsurin.task3nau.handler.CommandHandler;
//
//import java.util.Scanner;
//
///**
// * Конфигурация для взаимодействия с приложением
// */
//@Configuration
//public class ConsoleConfig {
//
//    private final CommandHandler commandHandler;
//    private final AppConfig appConfig;
//
//    private static final String COMMANDS_MENU = String.join("\n",
//            "Введите команду:",
//            "'/create {name} {status}'        - создать задачу",
//            "'/get {id}'                      - найти задачу",
//            "'/del {id}'                      - удалить задачу",
//            "'/get-all'                       - найти все задачи",
//            "'/update-status {id} {status}'   - обновить статус задачи",
//            "'/exit'                          - выход"
//    );
//
//    public ConsoleConfig(CommandHandler commandHandler, AppConfig appConfig) {
//        this.commandHandler = commandHandler;
//        this.appConfig = appConfig;
//    }
//
//    /**
//     * Выводит в консоль данные о приложении
//     */
//    @PostConstruct
//    void printAppInfo() {
//        String appName = appConfig.appInfo().name();
//        String appVersion = appConfig.appInfo().version();
//
//        System.out.println("AppName: " + appName + ", AppVersion: " + appVersion);
//    }
//
//    /**
//     * Запускает консольный ввод команд
//     */
//    @Bean
//    public CommandLineRunner commandScanner() {
//        return args -> {
//            try (Scanner scanner = new Scanner(System.in)) {
//                System.out.println(COMMANDS_MENU);
//                while (true) {
//                    System.out.print("> ");
//                    String input = scanner.nextLine();
//
//                    if ("/exit".equalsIgnoreCase(input.trim())) {
//                        System.out.println("Выход из программы...");
//                        System.exit(0);
//                    }
//
//                    commandHandler.handleCommand(input);
//                }
//            }
//        };
//    }
//}
