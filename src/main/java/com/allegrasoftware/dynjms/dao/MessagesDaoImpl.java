package com.allegrasoftware.dynjms.dao;

import com.allegrasoftware.dynjms.domain.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class MessagesDaoImpl implements MessageDao {

    private final static Logger logger = LoggerFactory.getLogger(MessagesDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public MessagesDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public long save(Messages messages) {
        long idMsg = this.jdbcTemplate.queryForObject("SELECT nextval('messages_seq')", Long.class);

        LobHandler lobHandler = new DefaultLobHandler();

        this.jdbcTemplate.update("insert into Messages (id, action, message, task_id) values (?, ?, ?, ?)",
                new AbstractLobCreatingPreparedStatementCallback(lobHandler) {
                    @Override
                    protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
                        ps.setLong(1, idMsg);
                        ps.setInt(2, messages.getAction());
                        lobCreator.setBlobAsBytes(ps, 3, messages.getMessage());
                        ps.setLong(4, messages.getTaskId());
                    }
                });

        return idMsg;
    }
}

