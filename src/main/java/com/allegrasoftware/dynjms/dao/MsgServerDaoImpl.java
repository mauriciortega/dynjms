package com.allegrasoftware.dynjms.dao;

import com.allegrasoftware.dynjms.domain.MsgServer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MsgServerDaoImpl implements MsgServerDao {
    private final static Logger logger = LoggerFactory.getLogger(MsgServerDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public MsgServerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MsgServer> getMsgServers(int[] osaIds) {

        StringBuilder sb = new StringBuilder();
        sb.append("select inter.code as code, inter.jms_server_type as broker_type, inter.dsip as host, ");
        sb.append("  inter.pass as password, inter.ds_port as port, inter.username as username, topic.prefix as prefix, ");
        sb.append("  topic.type as type ");
        sb.append("  from intermediator as inter left join topic_prefix as topic on inter.id=topic.intermediador_id where inter.code in (");
        sb.append(StringUtils.join(osaIds, ','));
        sb.append(")");

        return this.jdbcTemplate.query(sb.toString(),
                new RowMapper<MsgServer>() {
                    @Override
                    public MsgServer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        MsgServer msgServer = new MsgServer();
                        msgServer.setId(rs.getLong("code"));
                        msgServer.setBrokerType(rs.getString("broker_type"));
                        msgServer.setHost(rs.getString("host"));
                        msgServer.setPassword(rs.getString("password"));
                        msgServer.setPort(rs.getString("port"));
                        msgServer.setUsername(rs.getString("username"));
                        msgServer.setPrefix(rs.getString("prefix"));
                        msgServer.setType(rs.getInt("type"));

                        return msgServer;
                    }
                });
    }
}
