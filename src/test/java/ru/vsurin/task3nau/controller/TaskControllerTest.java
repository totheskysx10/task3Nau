package ru.vsurin.task3nau.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vsurin.task3nau.response.TaskDto;

import java.util.List;

/**
 * Тесты контроллера задач
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TaskControllerTest {

    private final String URL = "http://localhost:8080";

    private final String ADMIN_USERNAME = "lada";

    private final String ADMIN_PASSWORD = "vesta";

    /**
     * Тест создания задачи
     */
    @Test
    void createTask() {
        RestAssured.given()
                .auth().form(ADMIN_USERNAME, ADMIN_PASSWORD)
                .contentType(ContentType.JSON)
                .body("""
                            {
                              "title": "title12",
                              "project": "/projects/1",
                              "assignedUser": "/users/1",
                              "priority": "1",
                              "status": "PLANNED"
                            }
                        """)
                .post(URL + "/tasks")
                .then()
                .statusCode(201);
    }

    /**
     * Тест поиска задачи по проекту и части названия
     */
    @Test
    void testFindByProjectAndTitleContaining() {
        List<TaskDto> result = RestAssured.given()
                .auth().form(ADMIN_USERNAME, ADMIN_PASSWORD)
                .contentType(ContentType.JSON)
                .get(URL + "/custom/tasks/search?titleFragment=title&project=1")
                .then()
                .statusCode(200)
                .extract().jsonPath().getList(".", TaskDto.class);

        Assertions.assertEquals("title12", result.getFirst().getTitle());
    }

    /**
     * Тест поиска задачи по проекту и части названия, когда такой задачи нет
     */
    @Test
    void testFindByProjectAndTitleContainingNotFound() {
        RestAssured.given()
                .auth().form(ADMIN_USERNAME, ADMIN_PASSWORD)
                .contentType(ContentType.JSON)
                .get(URL + "/custom/tasks/search?titleFragment=no&project=1")
                .then()
                .statusCode(204);
    }

    /**
     * Тест удаления задачи
     */
    @Test
    void testDeleteTaskWithComments() {
        RestAssured.given()
                .auth().form(ADMIN_USERNAME, ADMIN_PASSWORD)
                .contentType(ContentType.JSON)
                .delete(URL + "/custom/tasks/del-task-with-comments?id=19")
                .then()
                .statusCode(200);
    }
}