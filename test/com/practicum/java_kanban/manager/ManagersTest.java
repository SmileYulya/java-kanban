package com.practicum.java_kanban.manager;

import com.practicum.java_kanban.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void testCreateCopyTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        Task task1 = new Task("1", "Task 1", Status.NEW);
        task1.setTitle("2");
        taskManager.updateTask(task1);
        assertEquals("2", task1.getTitle());
    }

    @Test
    void testCreateCopyHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }
}