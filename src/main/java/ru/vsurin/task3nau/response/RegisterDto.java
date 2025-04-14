package ru.vsurin.task3nau.response;

/**
 * DTO регистрации
 */
public class RegisterDto {
    private String name;
    private String password;

    public RegisterDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public RegisterDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
