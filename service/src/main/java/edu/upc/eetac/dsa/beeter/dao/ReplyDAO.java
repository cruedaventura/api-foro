package edu.upc.eetac.dsa.beeter.dao;

import edu.upc.eetac.dsa.beeter.entity.Reply;

import java.sql.SQLException;

public interface ReplyDAO
{
    public Reply createReply(String id, String stingid, String content) throws SQLException;
    public Reply getReplyById(String id) throws SQLException;
    //public StingCollection getStings(long timestamp, boolean before) throws SQLException;
    public Reply updateSting(String id, String subject, String content) throws SQLException;
    public boolean deleteSting(String id) throws SQLException;
}
