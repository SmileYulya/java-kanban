package com.practicum.java_kanban.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    //проверяется, что экземпляры класса Task равны друг другу, если равен их id
    @Test
    void testTaskEqualsIfSameId() {
        Task task1 = new Task("Прочитать книгу", "Описание 1", Status.NEW);
        task1.setId(1);

        Task task2 = new Task("Смотреть фильм", "Описание 2", Status.NEW);
        task2.setId(1);

        assertEquals(task1, task2);
    }

    //проверяется, что экземпляры класса Task не равны друг другу, если не равен их id
    @Test
    void testTaskNotEqualsIfSameId() {
        Task task1 = new Task("Прочитать книгу", "Описание 1", Status.NEW);
        task1.setId(1);

        Task task2 = new Task("Смотреть фильм", "Описание 2", Status.NEW);
        task1.setId(2);

        assertNotEquals(task1, task2);
    }
}