package com.allegrasoftware.dynjms.dao;

import com.allegrasoftware.dynjms.domain.Task;

public interface TaskDao {
    /**
     * Save a new Task
     * @param task Task data, id will be ignored
     * @return generated id
     */
    long save(Task task);
}
