package com.practicum.java_kanban.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    //проверяется, что наследники класса Subtask равны друг другу, если равен их id
    @Test
    void testSubtaskEqualsIfSameId() {
        Subtask subtask1 = new Subtask("собрать вещи", "описание подзадачи 1", 4);
        subtask1.setId(1);

        Subtask subtask2 = new Subtask("упаковать вещи", "описание подзадачи 2", 4);
        subtask2.setId(1);

        assertEquals(subtask1, subtask2);
    }

    //проверяется, что наследники класса Subtask не равны друг другу, если не равен их id
    @Test
    void testSubtaskNotEqualsIfSameId() {
        Subtask subtask1 = new Subtask("собрать вещи", "описание подзадачи 1", 4);
        subtask1.setId(1);

        Subtask subtask2 = new Subtask("упаковать вещи", "описание подзадачи 2", 4);
        subtask2.setId(2);

        assertNotEquals(subtask1, subtask2);
    }

    //проверяется, что объект Subtask нельзя сделать своим же эпиком
    @Test
    void testSubtaskCanNotBeHisEpic() {
        Subtask subtask = new Subtask("Subtask", "Subtask 1", 1);
        subtask.setId(1);
        Epic epic = new Epic("Epic 1", "Epic 1");
        epic.setId(1);
        assertFalse(epic.getSubtaskIds().contains(1));
    }

}