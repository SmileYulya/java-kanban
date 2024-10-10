package com.practicum.java_kanban.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    //проверяется, что наследники класса Epic равны друг другу, если равен их id
    @Test
    void testEpicEqualsIfSameId() {
        Epic ep1 = new Epic("Переезд", "Описание эпика 1");
        ep1.setId(1);

        Epic ep2 = new Epic("Переезд2", "Описание эпика 2");
        ep2.setId(1);

        assertEquals(ep1, ep2);
    }

    //проверяется, что наследники класса Epic не равны друг другу, если не равен их id
    @Test
    void testEpicNotEqualsIfSameId() {
        Epic ep1 = new Epic("Переезд", "Описание эпика 1");
        ep1.setId(1);

        Epic ep2 = new Epic("Переезд2", "Описание эпика 2");
        ep2.setId(2);

        assertNotEquals(ep1, ep2);
    }

    //проверяется, что объект Epic нельзя добавить в самого себя в виде подзадачи;
    @Test
    void testEpicCanNotBeHisSubtask() {
        Epic epic = new Epic("Epic 1", "Epic 1");
        epic.setId(1);

        Subtask subtask = new Subtask("Subtask 1", "Subtask 1", Status.NEW,1);
        subtask.setId(1);

        assertFalse(epic.getSubtaskIds().contains(1));
    }
}
