package com.practicum.java_kanban.manager;

import com.practicum.java_kanban.model.Epic;
import com.practicum.java_kanban.model.Subtask;
import com.practicum.java_kanban.model.Task;

import java.util.ArrayList;

public interface TaskManager {
    void addTask(Task task);

    void addEpic(Epic epic);

    void addSubTask(Subtask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic, int id);

    void updateSubTask(Subtask subtask);

    ArrayList<Subtask> getAllSubtasks();

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    void deleteTaskById(int nextId);

    void deleteEpic(int nextId);

    void deleteSubtask(int nextId);

    void deleteAllTasks();

    void deleteAllTEpics();

    void deleteAllSubtask();

    Task getTaskById(int nextId);

    Epic getEpicById(int nextId);

    Subtask getSubtaskById(int nextId);

    ArrayList<Subtask> getSubtaskByEpic(int epicId);

}
