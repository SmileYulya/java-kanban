package com.practicum.java_kanban.manager;

import com.practicum.java_kanban.model.Epic;
import com.practicum.java_kanban.model.Subtask;
import com.practicum.java_kanban.model.Task;
import com.practicum.java_kanban.model.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private int nextId = 1;
    HistoryManager inMemoryHistoryManager = Managers.getDefaultHistory();

    //добавление задачи
    @Override
    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    //добавление эпика
    @Override
    public void addEpic(Epic epic) {
        epic.setId(nextId++);
        epics.put(epic.getId(), epic);
    }

    //добавление подзадачи
    @Override
    public void addSubTask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);
        Epic ep = epics.get(subtask.getEpicId());
        ep.getSubtaskIds().add(subtask.getId());
        updateStatus(subtask.getEpicId());
    }

    //обновление задачи
    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    //обновление эпика
    @Override
    public void updateEpic(Epic epic) {
        final Epic oldEpic = epics.get(epic.getId());
        oldEpic.setTitle(epic.getTitle());
        oldEpic.setDescription(epic.getDescription());
    }

    //обновление подзадачи
    @Override
    public void updateSubTask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateStatus(subtask.getEpicId());
    }

    //получение списка всех подзадач
    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    //получение списка всех задач
    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    //получение списка всех эпиков
    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    //удаление задачи по id
    @Override
    public void deleteTaskById(int nextId) {
        tasks.remove(nextId);
    }

    //удаление эпика по id
    @Override
    public void deleteEpic(int nextId) {
        final Epic epic = epics.remove(nextId);
        for (Integer subtaskId : epic.getSubtaskIds()) {
            subtasks.remove(subtaskId);
        }
    }

    //удаление подзадачи по id
    @Override
    public void deleteSubtask(int nextId) {
        Subtask subtask = subtasks.remove(nextId);
        Epic epic = epics.get(subtask.getEpicId());
        epic.getSubtaskIds().remove((Integer) subtask.getId());
        updateStatus(epic.getId());
    }

    //удаление всех задач
    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    //удаление всех эпиков
    @Override
    public void deleteAllTEpics() {
        subtasks.clear();
        epics.clear();
    }

    //удаление всех подзадач
    @Override
    public void deleteAllSubtask() {
        subtasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubtaskIds().clear();
            updateStatus(epic.getId());
        }
    }

    //получение задачи по id
    @Override
    public Task getTaskById(int nextId) {
        if (tasks.get(nextId) != null) {
            inMemoryHistoryManager.add(tasks.get(nextId));
        }
        return tasks.get(nextId);
    }

    //получение эпика по id
    @Override
    public Epic getEpicById(int nextId) {
        if (epics.get(nextId) != null) {
            inMemoryHistoryManager.add(epics.get(nextId));
        }
        return epics.get(nextId);
    }

    //получение подзадачи по id
    @Override
    public Subtask getSubtaskById(int nextId) {
        if (subtasks.get(nextId) != null) {
            inMemoryHistoryManager.add(subtasks.get(nextId));
        }
        return subtasks.get(nextId);
    }

    //получение подзадачи по epicId
    @Override
    public ArrayList<Subtask> getSubtaskByEpic(int epicId) {
        ArrayList<Subtask> subtasksNew = new ArrayList<>();
        Epic epic = epics.get(epicId);
        for (Integer subtaskId : epic.getSubtaskIds()) {
            subtasksNew.add(subtasks.get(subtaskId));
        }
        return subtasksNew;
    }

    @Override
    public List<Task> getHistory(){
        return inMemoryHistoryManager.getHistory();
    }

    //вспомогательный метод обновления статуса эпика
    private void updateStatus(int epicId) {
        int countDone = 0;
        int countNew = 0;
        Epic epicGetEpicId = epics.get(epicId);
        ArrayList<Integer> subtaskIds = epicGetEpicId.getSubtaskIds();

        for (Integer subtaskId : subtaskIds) {
            Subtask anSubtask = subtasks.get(subtaskId);
            if (anSubtask.getStatus().equals(Status.DONE)) {
                countDone++;
            } else if (anSubtask.getStatus().equals(Status.NEW)) {
                countNew++;
            } else if (anSubtask.getStatus().equals(Status.IN_PROGRESS)) {
                epicGetEpicId.setStatus(Status.IN_PROGRESS);
                return;
            }
        }
        if (countNew == subtaskIds.size()) {
            epicGetEpicId.setStatus(Status.NEW);
        } else if (countDone == subtaskIds.size()) {
            epicGetEpicId.setStatus(Status.DONE);
        } else {
            epicGetEpicId.setStatus(Status.IN_PROGRESS);
        }
    }
}








