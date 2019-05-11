package com.allegrasoftware.dynjms.service;

import com.allegrasoftware.dynjms.dao.MessageDao;
import com.allegrasoftware.dynjms.dao.MsgServerDao;
import com.allegrasoftware.dynjms.dao.TaskDao;
import com.allegrasoftware.dynjms.domain.Messages;
import com.allegrasoftware.dynjms.domain.MsgServer;
import com.allegrasoftware.dynjms.domain.Task;
import com.allegrasoftware.dynjms.util.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Component("dataService")
public class DataService {

    private final static Logger logger = LoggerFactory.getLogger(DataService.class);
    private final static int STATUS_INICIAL = 0;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private MsgServerDao msgServerDao;

    @Value("${dynjms.msgServer}")
    private String msgServers;

    @Value("${dynjms.administradora}")
    private String admin;


    @Transactional
    public void saveMessage(byte[] payload,
                            @Header int action,
                            @Header int idOsa) {

        Task task = new Task();
        task.setSource(idOsa);
        task.setTarget(Target.INTEROPFLEX.getValue());
        task.setStatus(STATUS_INICIAL);

        long taskId = taskDao.save(task);

        Messages message = new Messages();
        message.setAction(action);
        message.setMessage(payload);
        message.setTaskId(taskId);

        messageDao.save(message);
    }

    public List<MsgServer> getMsgServers() {
        int[] configOsas = Arrays.stream(msgServers.split(",")).mapToInt(Integer::parseInt).toArray();

        return msgServerDao.getMsgServers(configOsas);
    }

    public String getAdmin() {
        return admin;
    }
}
