package com.allegrasoftware.dynjms.dao;

import com.allegrasoftware.dynjms.domain.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class TaskDaoImpl implements TaskDao {
    private final static Logger logger = LoggerFactory.getLogger(TaskDaoImpl.class);


    private final JdbcTemplate jdbcTemplate;

    public TaskDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Task task) {
        long idTask = this.jdbcTemplate.queryForObject("SELECT nextval('task_seq')", Long.class);

        this.jdbcTemplate.update("insert into task (id, date_time_created, source, target, status) values (?, ?, ?, ?, ?)",
            idTask,
            new Timestamp(System.currentTimeMillis()),
            task.getSource(),
            task.getTarget(),
            task.getStatus()
        );

        return idTask;
    }
}
