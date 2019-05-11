package com.allegrasoftware.dynjms.dao;

import com.allegrasoftware.dynjms.domain.Messages;

public interface MessageDao {
    /**
     * Create a new Message
     * @param messages
     * @return
     */
    long save(Messages messages);
}
