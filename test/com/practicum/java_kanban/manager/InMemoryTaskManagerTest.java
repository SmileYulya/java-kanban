package com.practicum.java_kanban.manager;

import com.practicum.java_kanban.model.Epic;
import com.practicum.java_kanban.model.Status;
import com.practicum.java_kanban.model.Subtask;
import com.practicum.java_kanban.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private static TaskManager taskManager;
    private static HistoryManager inMemoryHistoryManager;
    private static Task task1;
    private static Task task2;
    private static Epic epic1;
    private static Epic epic2;
    private static Subtask subtask1;
    private static Subtask subtask2;
    private static Subtask subtask3;
    private static Subtask subtask4;

    @BeforeEach
    void beforeEach() {
        taskManager = Managers.getDefault();
        inMemoryHistoryManager = Managers.getDefaultHistory();
        task1 = new Task("1", "Task 1");
        task2 = new Task("2", "Task 2");
        epic1 = new Epic("3", "Epic 1");
        epic2 = new Epic("4", "Epic 2");
        subtask1 = new Subtask("5", "Subtask 1",  3);
        subtask2 = new Subtask("6", "Subtask 2",  3);
        subtask3 = new Subtask("7", "Subtask 3",  4);
        subtask4 = new Subtask("8", "Subtask 3",  4);
    }

    //проверяется, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id
    @Test
    void testTaskManagerAddsTasksOfDifferentsTypeAndFind() {
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addSubTask(subtask1);
        taskManager.addSubTask(subtask2);
        taskManager.addSubTask(subtask3);
        taskManager.addSubTask(subtask4);
        assertEquals(taskManager.getTaskById(1), task1);
        assertEquals(taskManager.getEpicById(3), epic1);
        assertEquals(taskManager.getSubtaskById(5), subtask1);
        assertEquals(taskManager.getSubtaskById(8), subtask4);
    }

    //тест, в котором проверяется неизменность задачи (по всем полям) при добавлении задачи в менеджер
    @Test
    void testImmutabilityOfTasks() {
        taskManager.addTask(task1);
        assertEquals(taskManager.getTaskById(1).getTitle(), "1");
        assertEquals(taskManager.getTaskById(1).getDescription(), "Task 1");
        assertEquals(taskManager.getTaskById(1).getStatus(), Status.NEW);
    }

    //проверяется, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера
    @Test
    void testTaskNoConflictWithSpecifidIdAndGeneratedId() {
        task1.setId(1);
        task2.setId(1);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        assertNotEquals(task2, taskManager.getTaskById(1));
    }

    //задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных
    @Test
    void testAddHistoryManager() {
        List<Task> historyTest = new ArrayList<>();

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addEpic(epic1);

        taskManager.getEpicById(3);
        historyTest.add(epic1);

        taskManager.getTaskById(1);
        historyTest.add(task1);

        taskManager.getTaskById(2);
        historyTest.add(task2);

        assertEquals(historyTest, taskManager.getHistory());
    }
}
