package com.allegrasoftware.dynjms.dao;

import com.allegrasoftware.dynjms.domain.MsgServer;

import java.util.List;

public interface MsgServerDao {

    /**
     * Get List of MsgServers
     * @param osaIds array of Ids to select
     * @return
     */
    List<MsgServer> getMsgServers(int[] osaIds);

}
