package com.allegrasoftware.dynjms;

import com.allegrasoftware.dynjms.domain.MsgServer;
import com.allegrasoftware.dynjms.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.integration.config.EnableIntegration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
@EnableIntegration
// @ImportResource("classpath:META-INF/spring/integration/integrationActiveMQ.xml")
public class DynJmsApplication {
    private final static Logger logger = LoggerFactory.getLogger(DynJmsApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DynJmsApplication.class, args);
        context.setId("parent");
        List<ConfigurableApplicationContext> children = new ArrayList<>();


        DataService dataService = context.getBean(DataService.class);
        List<MsgServer> lstMsgServers = dataService.getMsgServers();
        logger.info("Osas configuradas: ");
        for (MsgServer msgServer : lstMsgServers) {
            logger.info("Id: {}, brokerType: {}, host: {}, username: {}, port: {}", msgServer.getId(),
                    msgServer.getBrokerType(), msgServer.getHost(), msgServer.getUsername(), msgServer.getPort());
            String config = null;

            switch (msgServer.getBrokerType()) {
                case "activemq" :    // ActiveMQ
                    config = "classpath:META-INF/spring/integration/integrationActiveMQ.xml";
                    break;
            }

            ConfigurableApplicationContext child = new ClassPathXmlApplicationContext(new String[]{
                    "classpath:META-INF/spring/integration/integrationCommon.xml", config}, false, context);
            child.setId("ctxOsa" + msgServer.getId());
            children.add(child);
            // child.setParent(context);

            StandardEnvironment env = new StandardEnvironment();
            Properties props = new Properties();
            props.setProperty("host", msgServer.getHost());
            props.setProperty("port", msgServer.getPort());
            props.setProperty("topic", msgServer.getPrefix() + "." + dataService.getAdmin());
            props.setProperty("user", msgServer.getUsername());
            props.setProperty("password", msgServer.getPassword());
            props.setProperty("action", Integer.toString(msgServer.getType()));
            props.setProperty("idOsa", Long.toString(msgServer.getId()));

            PropertiesPropertySource pps = new PropertiesPropertySource("jmsProps", props);
            env.getPropertySources().addLast(pps);

            child.setEnvironment(env);
            child.refresh();
        }

    }



}
