package com.practicum.java_kanban.manager;

import com.practicum.java_kanban.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final static int NUMBER_OF_VIEWS = 10; //количество просмотров
    private final static List<Task> browsingHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (browsingHistory.size() == NUMBER_OF_VIEWS) {
            browsingHistory.removeFirst();
        }
        browsingHistory.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(browsingHistory);
    }
}
